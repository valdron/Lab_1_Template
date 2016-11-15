package messer;

import java.lang.reflect.Constructor;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public interface ADSBMessageInterface {
    String getTimestamp();
    int getType();
    String getIcao();
    int getDownlinkFormat();
    int getCapability();
}
