package lab4;


import messer.ADSBAirbonePositionMessage;
import messer.ADSBAirboneVelocityMessage;
import messer.ADSBAircraftIdentificationMessage;
import messer.ADSBMessage;

/**
 * Created by paul on 11.01.17.
 */
public class AircraftData {
    public ADSBAirbonePositionMessage positionMessage;
    public ADSBAirboneVelocityMessage velocityMessage;
    public ADSBAircraftIdentificationMessage identificationMessage;
    public ADSBMessage otherMessage;
}
