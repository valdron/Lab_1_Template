package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBAirboneVelocityMessage extends ADSBMessage implements ADSBAirboneVelocityMessageInterface{

    private int subType,
                intentChange,
                reserveredA,
                navigationAccuracy,
                speed,
                heading,
                verticalRateSource,
                verticalSpeed;

    public ADSBAirboneVelocityMessage(String icao, int type_int, int df_int, int ca_int, String payload, String timestamp) {
        super(icao, type_int, df_int, ca_int, payload, timestamp);
    }

    //TODO implement Interface;
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
