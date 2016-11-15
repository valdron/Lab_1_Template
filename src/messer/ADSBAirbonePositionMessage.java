package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBAirbonePositionMessage extends ADSBMessage implements ADSBAirbonePositionMessageInterface {
    private int surveillanceStatus,
                niceSupplement,
                altitude,
                timeFlag,
                cprFormat,
                cprLongtitude,
                cprLatitude;

    public ADSBAirbonePositionMessage(String icao, int type_int, int df_int, int ca_int, String payload, String timestamp) {
        super(icao, type_int, df_int, ca_int, payload, timestamp);
        String payload_binary = ADSBMessageFactory.hexToBinaryString(payload);
        surveillanceStatus = Integer.parseInt(payload_binary.substring(5,7),2);
        niceSupplement = Integer.parseInt(payload_binary.substring(7,8),2);
        altitude = Integer.parseInt(payload_binary.substring(8,20),2);
        timeFlag = Integer.parseInt(payload_binary.substring(20,21),2);
        cprFormat = Integer.parseInt(payload_binary.substring(21,22),2);
        cprLatitude = Integer.parseInt(payload_binary.substring(22,39),2);
        cprLongtitude = Integer.parseInt(payload_binary.substring(39,56),2);
    }

    @Override
    public String toString(){
        String format;
        if(cprFormat == 0)
            format = "Even";
        else
            format = "Odd";
        return getIcao() + "\tAirborne Position Message \n" +
                "\t\tType:\t " + getType() + "\n" +
                "\t\tAlti:\t " + altitude + "\n" +
                "\t\tLatlon:\t " + cprLatitude + " : " + cprLongtitude  + "\n" +
                "\t\tFormat:\t " + format + "\n" ;
    }

    //TODO: implement methods
    @Override
    public int getSurveillanceStatus() {
        return surveillanceStatus;
    }

    @Override
    public int getNiceSupplement() {
        return niceSupplement;
    }

    @Override
    public int getAltitude() {
        return altitude;
    }

    @Override
    public int getTimeFlag() {
        return timeFlag;
    }

    @Override
    public int getCprFormat() {
        return cprFormat;
    }

    @Override
    public int getCprLongtitude() {
        return cprLongtitude;
    }

    @Override
    public int getCprLatitude() {
        return cprLatitude;
    }
}
