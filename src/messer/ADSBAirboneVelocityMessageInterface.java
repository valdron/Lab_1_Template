package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public interface ADSBAirboneVelocityMessageInterface {
    int getSubtype();
    int getIntentChange();
    int getReservedA();
    int getNavigationAccuracy();
    int getSpeed();
    int getHeading();
    int getVerticalRateSource();
    int getVerticalSpeed();
}
