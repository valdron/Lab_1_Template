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

        //TODO Speed + Heading


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
