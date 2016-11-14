package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBAircraftIdentificationMessage extends ADSBMessage implements ADSBAircraftIdentificationMessageInterface {


    //TODO: implement methods
    @Override
    public int getSurveillanceStatus() {
        return 0;
    }

    @Override
    public int getNiceSupplement() {
        return 0;
    }

    @Override
    public int getAltitude() {
        return 0;
    }

    @Override
    public int getTimeFlag() {
        return 0;
    }

    @Override
    public int getCprFormat() {
        return 0;
    }

    @Override
    public int getCprLongtitude() {
        return 0;
    }

    @Override
    public int getCprLatitude() {
        return 0;
    }
}
