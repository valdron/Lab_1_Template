package messer;

import senser.ADSBSentence;

import java.math.BigInteger;

public class ADSBMessageFactory implements ADSBMessageFactoryInterface {
    @Override
    public ADSBMessage fromADSBSentence(ADSBSentence sentence) {


        String binaryPayload = hexToBinaryString(sentence.getPayload());
        String binaryDfca = hexToBinaryString(sentence.getDfca());



        String type = binaryPayload.substring(0,5);
        String sub_type = binaryPayload.substring(5,8);
        String df = binaryDfca.substring(0,5);
        String ca = binaryDfca.substring(5,8);

        int type_int = Integer.parseInt(type,2);
        int df_int = Integer.parseInt(df,2);
        int ca_int = Integer.parseInt(ca,2);
        int sub_type_int = Integer.parseInt(sub_type,2);

        ADSBMessage message;
        if(type_int < 5 && type_int > 0)
        {
            message = new ADSBAircraftIdentificationMessage(sentence.getIcao(), type_int, df_int, ca_int, sentence.getPayload(), sentence.getTimestamp());
        }

        else if(type_int == 19 && (sub_type_int <= 4 || sub_type_int >= 1)){
            message = new ADSBAirboneVelocityMessage(sentence.getIcao(), type_int, df_int, ca_int, sentence.getPayload(), sentence.getTimestamp());
        }

        else if((type_int <= 22 && type_int >= 20) || (type_int <= 18 && type_int >= 9) || type_int == 0){
            message = new ADSBAirbonePositionMessage(sentence.getIcao(), type_int, df_int, ca_int, sentence.getPayload(), sentence.getTimestamp());
        }

        else {
            message = new ADSBMessage(sentence.getIcao(), type_int, df_int, ca_int, sentence.getPayload(), sentence.getTimestamp());
        }

        return message;
    }

    public static String hexToBinaryString(String hex_string){
        String result = "";
        for(char c: hex_string.toCharArray()){
            switch (c){
                case '0':
                    result += "0000";
                    break;
                case '1':
                    result += "0001";
                    break;
                case '2':
                    result += "0010";
                    break;
                case '3':
                    result += "0011";
                    break;
                case '4':
                    result += "0100";
                    break;
                case '5':
                    result += "0101";
                    break;
                case '6':
                    result += "0110";
                    break;
                case '7':
                    result += "0111";
                    break;
                case '8':
                    result += "1000";
                    break;
                case '9':
                    result += "1001";
                    break;
                case 'A':
                    result += "1010";
                    break;
                case 'B':
                    result += "1011";
                    break;
                case 'C':
                    result += "1100";
                    break;
                case 'D':
                    result += "1101";
                    break;
                case 'E':
                    result += "1110";
                    break;
                case 'F':
                    result += "1111";
                    break;
            }

        }

        return result;
    }
    static String frombinary6bitString(String str){
        String result = "";
        for(int i = 0; i < str.length();i+=6) {
            int charvalue;

            if(i+6 >= str.length())
                charvalue = Integer.parseInt(str.substring(i),2);
            else
                charvalue = Integer.parseInt(str.substring(i,i+6),2);

            if(charvalue < 32)
                charvalue += 64;
            result += (char) charvalue;
        }
        return result;
    }
}
