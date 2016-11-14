package messer;

import java.lang.reflect.Constructor;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public interface ADSBMessageInterface {
    ADSBMessage Constructor(String icao,String type, String df, String payload);
    String getTimestamp();
    int getType();
    String getIcao();
    int getDownlinkFormat();
    int getCapability();
}
