package messer;

import senser.ADSBSentence;

import java.math.BigInteger;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public class ADSBMessageFactory implements ADSBMessageFactoryInterface {
    @Override
    public ADSBMessage fromADSBSentence(ADSBSentence sentence) {
        String type = hexToBinaryString(sentence.getPayload()).substring(0,5);
        String sub_type = hexToBinaryString(sentence.getPayload()).substring(5,8);
        String df = hexToBinaryString(sentence.getDfca()).substring(0,5);
        String ca = hexToBinaryString(sentence.getDfca()).substring(5,8);
        int type_int = Integer.parseInt(type,2);
        int df_int = Integer.parseInt(df,2);
        int ca_int = Integer.parseInt(ca,2);
        int sub_type_int = Integer.parseInt(sub_type,2);

        ADSBMessage message;
        if(type_int < 5 && type_int > 0)
        {
            message = new ADSBAirbonePositionMessage(sentence.getIcao(), type_int, df_int, ca_int, sentence.getPayload(), sentence.getTimestamp());
            System.out.println("HIER BIN ICH");
        }

        else if(type_int == 19 && (sub_type_int <= 4 || sub_type_int >= 1)){
            message = new ADSBAirboneVelocityMessage(sentence.getIcao(), type_int, df_int, ca_int, sentence.getPayload(), sentence.getTimestamp());
        }

        else if((type_int <= 22 && type_int >= 20) || (type_int <= 18 && type_int >= 9) || type_int == 0){
            message = new ADSBAircraftIdentificationMessage(sentence.getIcao(), type_int, df_int, ca_int, sentence.getPayload(), sentence.getTimestamp());
        }

        else {
            message = new ADSBMessage(sentence.getIcao(), type_int, df_int, ca_int, sentence.getPayload(), sentence.getTimestamp());
        }

        return message;
    }

    public static String hexToBinaryString(String hex_string){
        BigInteger c = new BigInteger(hex_string,16);
        return c.toString(2);
    }
}
