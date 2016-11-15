package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBAircraftIdentificationMessage extends ADSBMessage implements ADSBAircraftIdentificationMessageInterface {

    private String aircraftId;
    private int emitterCategory;

    public ADSBAircraftIdentificationMessage(String icao, int type_int, int df_int, int ca_int, String payload, String timestamp) {
        super(icao, type_int, df_int, ca_int, payload, timestamp);
        String payload_binary = ADSBMessageFactory.hexToBinaryString(payload);
        String aircraftId_binary = payload_binary.substring(8,56);
        emitterCategory = Integer.parseInt(payload_binary.substring(5,8),2);
        aircraftId = payload.substring(8,56);
    }
    @Override
    public String toString() {
        return getIcao() + "\tAircraft Identification and Category Message \n" +
                "\t\tIdent:\t " + aircraftId + "\n" +
                "\t\tCateg:\t " + emitterCategory + "\n";

    }

    @Override
    public int getEmitterCategory() {
        return emitterCategory;
    }

    @Override
    public String getAircraftId() {
        return aircraftId;
    }
}
