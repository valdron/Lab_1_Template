package lab4;

import messer.ADSBAirbonePositionMessage;
import messer.ADSBMessageFactory;
import senser.ADSBSentence;
import senser.ADSBSentenceFactory;

import javax.xml.soap.MessageFactory;

/**
 * Created by paul on 12.01.17.
 */
public class AircraftDataTest {
    public static void main(String[] args) {

        ADSBSentence even = new ADSBSentence("0","8D","40621","58C382D690C8AC","2863A7");
        ADSBSentence odd = new ADSBSentence("0","8D","40621","58C386435CC412","692AD6");

        ADSBMessageFactory f = new ADSBMessageFactory();

        ADSBAirbonePositionMessage e = (ADSBAirbonePositionMessage) f.fromADSBSentence(even);
        ADSBAirbonePositionMessage o = (ADSBAirbonePositionMessage) f.fromADSBSentence(odd);

        AircraftData ad = new AircraftData();
        ad.positionMessageEven = e;
        ad.positionMessageOdd = o;
        ad.lastEvenorOdd = 1;

        double lat;
        double lon;
        try {
            lat  = ad.decodeCprLatitude();
            lon =  ad.decodeCprLongtitude(lat);
            System.out.println("Latitude:" + lat +"Longtitude: "+ lon);
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        ADSBSentenceFactory f2 = new ADSBSentenceFactory();
        System.out.println(f2.fromWebdisJson("1379574427.9127481!ADS-B*8D3C6DD6581F97E703EBAB40067F;"));

        ADSBSentence id = new ADSBSentence("0.0","8D","4840D6","202CC371C32CE0","576098");
        System.out.println(id);

        System.out.println(f.fromADSBSentence(id));


        System.out.println(ADSBMessageFactory.hexToBinaryString("202CC371C32CE0"));


    }
}
