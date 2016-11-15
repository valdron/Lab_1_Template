package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public interface ADSBAirbonePositionMessageInterface {

    int getSurveillanceStatus();
    int getNiceSupplement();
    int getAltitude();
    int getTimeFlag();
    int getCprFormat();
    int getCprLongtitude();
    int getCprLatitude();
}
