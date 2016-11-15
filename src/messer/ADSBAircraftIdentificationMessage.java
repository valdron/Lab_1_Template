package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBAircraftIdentificationMessage extends ADSBMessage implements ADSBAircraftIdentificationMessageInterface {

    public ADSBAircraftIdentificationMessage(String icao, int type_int, int df_int, int ca_int, String payload, String timestamp) {
        super(icao, type_int, df_int, ca_int, payload, timestamp);
    }

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
