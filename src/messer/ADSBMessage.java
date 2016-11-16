package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBMessage implements ADSBMessageInterface {

    private String icao;
    private String payload;
    private String timestamp;
    private int type, df, ca;

    public ADSBMessage (String icao, int type, int df, int ca, String payload, String timestamp) {
        this.icao = icao;
        this.type = type;
        this.df = df;
        this.ca = ca;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getIcao() {
        return icao;
    }

    @Override
    public int getDownlinkFormat() {
        return df;
    }

    @Override
    public int getCapability() {
        return ca;
    }
}
