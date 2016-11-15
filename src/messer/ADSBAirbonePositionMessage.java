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
