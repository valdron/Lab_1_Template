package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBAirboneVelocityMessage extends ADSBMessage implements ADSBAirboneVelocityMessageInterface{


    //TODO implement Interface;
    @Override
    public int getSubtype() {
        return 0;
    }

    @Override
    public int getIntentChange() {
        return 0;
    }

    @Override
    public int getReservedA() {
        return 0;
    }

    @Override
    public int getNavigationAccuracy() {
        return 0;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public int getHeading() {
        return 0;
    }

    @Override
    public int getVerticalRateSource() {
        return 0;
    }

    @Override
    public int getVerticalSpeed() {
        return 0;
    }
}
