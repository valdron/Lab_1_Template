package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBAirbonePositionMessage extends ADSBMessage implements ADSBAirbonePositionMessageInterface {

    //TODO: Implement interface
    @Override
    public int getEmitterCategory() {
        return 0;
    }

    @Override
    public int getAircraftId() {
        return 0;
    }
}
