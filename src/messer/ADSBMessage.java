package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBMessage implements ADSBMessageInterface {

    //TODO implement interface
    @Override
    public ADSBMessage Constructor(String icao, String type, String df, String payload) {
        return null;
    }

    @Override
    public String getTimestamp() {
        return null;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public String getIcao() {
        return null;
    }

    @Override
    public int getDownlinkFormat() {
        return 0;
    }

    @Override
    public int getCapability() {
        return 0;
    }
}
