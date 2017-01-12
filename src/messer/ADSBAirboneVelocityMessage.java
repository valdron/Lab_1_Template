package messer;


public class ADSBAirboneVelocityMessage extends ADSBMessage implements ADSBAirboneVelocityMessageInterface{

    private int subType,
                intentChange,
                reserveredA,
                //describes accuarcy e 0: >= 10, 1: < 10 2: < 3 3: <0.3  m/s
                navigationAccuracy,
                speed,
                heading,
                verticalRateSource,
                verticalSpeed;

    public ADSBAirboneVelocityMessage(String icao, int type_int, int df_int, int ca_int, String payload, String timestamp) {
        super(icao, type_int, df_int, ca_int, payload, timestamp);

        //Get Payload as binary String
        String payload_binary = ADSBMessageFactory.hexToBinaryString(payload);

        // Split Binary String and parse to Integers
        subType = Integer.parseInt(payload_binary.substring(5,8),2);
        intentChange = Integer.parseInt(payload_binary.substring(8,9),2);
        reserveredA = Integer.parseInt(payload_binary.substring(9,10),2);
        navigationAccuracy = Integer.parseInt(payload_binary.substring(10,13),2);
        verticalRateSource = Integer.parseInt(payload_binary.substring(35,36),2);
        verticalSpeed = Integer.parseInt(payload_binary.substring(37,46),2);

        //swap verticalspeed if sign is 1
        int verticalRateSign = Integer.parseInt(payload_binary.substring(36,37),2);
        if(verticalRateSign == 1)
            verticalSpeed *= -1;

        if(subType <= 2) { // east-west and north-south velocity
            int east_west_speed_nr  = Integer.parseInt(payload_binary.substring(14,24),2);
            int north_south_speed_nr = Integer.parseInt(payload_binary.substring(25,35),2);

            if (east_west_speed_nr != 0)
                east_west_speed_nr--;
            if (north_south_speed_nr !=0)
                north_south_speed_nr--;

            //  compute speed with pythagoras +1 to match other coding
            speed = (int) Math.sqrt(east_west_speed_nr * east_west_speed_nr + north_south_speed_nr * north_south_speed_nr) + 1;



            double heading_radians= Math.atan(((double) east_west_speed_nr)/north_south_speed_nr);

            int east_or_west = Integer.parseInt(payload_binary.substring(13,14),2);
            int north_or_south = Integer.parseInt(payload_binary.substring(24,25),2);

            if(east_or_west == 1 && north_or_south == 1)
                heading = (int) (Math.toDegrees(heading_radians + Math.PI));
            else if(east_or_west == 0 && north_or_south == 1)
                heading = (int) (Math.toDegrees(Math.PI - heading_radians));
            else if (east_or_west == 1 && north_or_south == 0)
                heading = (int) (Math.toDegrees(2 * Math.PI - heading_radians));
            else
                heading = (int) Math.toDegrees(heading_radians);

        } else { // with heading 0 = 0° (north) 1 = 360/1024 ° ... 1023 * 360 / 1024

            int heading_status_bit = Integer.parseInt(payload_binary.substring(13,14),2);
            
            //check if heading data is available
            if(heading_status_bit == 1){
                heading = (int) ( (double) Integer.parseInt(payload_binary.substring(14,24),2) / 360.0 * 1024.0);
            }
            speed = Integer.parseInt(payload_binary.substring(25,35),2);
        }


    }

    @Override
    public String toString() {
        return getIcao() + "\tAirborne Velocity Message \n" +
                "\t\tSpeed:\t" + speed + "\n" +
                "\t\tHeadng:\t" + heading + "\n" +
                "\t\tVertic:\t" + verticalSpeed + "\n";
    }

    @Override
    public int getSubtype() {
        return subType;
    }

    @Override
    public int getIntentChange() {
        return intentChange;
    }

    @Override
    public int getReservedA() {
        return reserveredA;
    }

    @Override
    public int getNavigationAccuracy() {
        return navigationAccuracy;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getHeading() {
        return heading;
    }

    @Override
    public int getVerticalRateSource() {
        return verticalRateSource;
    }

    @Override
    public int getVerticalSpeed() {
        return verticalSpeed;
    }
}
