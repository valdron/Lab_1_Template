package lab4;

import messer.ADSBAirbonePositionMessage;
import messer.ADSBAirboneVelocityMessage;
import messer.ADSBAircraftIdentificationMessage;
import messer.ADSBMessage;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import java.lang.Thread;


public class ActiveAircrafts{
    private Map<String, AircraftData> dataMap = new HashMap<>();
    private Map<String, Calendar> timeMap = new HashMap<>();

    public ActiveAircrafts() {
        new Thread(new Runnable(){
            @Override
            public void run() {

                Jedis jed = new Jedis("localhost");
                while (true) {

                    synchronized (timeMap){
                        //System.out.print("------------------" + timeMap.size() + "-----------------");
                        ArrayList<String> deletkeys = new ArrayList<String>();
                        //System.out.println("STAAAAAAAAAAAAART Cecking");
                        for(String key: timeMap.keySet()){
                            Calendar resetTime = timeMap.get(key);
                            if (resetTime.before(Calendar.getInstance())) {
                                deletkeys.add(key);
                                //System.out.println("Zwischensave für Delete SPÄÄÄÄÄTER = "+ key);
                            }
                        }

                        for(String key: deletkeys) {
                            deleteActive(key);
                            //System.out.println("DEEEEEEEEEEEELETE = "+ key);
                        }
                    }

                    //Write KML-String
                    String kml = "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
                            "<Document>";
                    for(String key: dataMap.keySet()) {
                        kml = kml + dataMap.get(key).toKml();
                    }
                    kml = kml + "</Document>\n" +
                            "</kml>";

                    jed.set("kmlDataString",kml);


                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


    public void updateActive(ADSBMessage message) {


        synchronized (timeMap) {
        String key = message.getIcao();
            // check if in map
            if (!dataMap.containsKey(key)) {
                dataMap.put(key, new AircraftData());
            }

            AircraftData data = dataMap.get(key);

            //update data object
            if (message instanceof ADSBAirbonePositionMessage) {

                if( ((ADSBAirbonePositionMessage) message).getCprFormat() == 0 ) {
                    data.positionMessageEven = (ADSBAirbonePositionMessage) message;
                    data.lastEvenorOdd = 0;
                }
                else{
                    data.positionMessageOdd = (ADSBAirbonePositionMessage) message;
                    data.lastEvenorOdd = 1;
                }

            } else if (message instanceof ADSBAirboneVelocityMessage) {
                data.velocityMessage = (ADSBAirboneVelocityMessage) message;
            } else if (message instanceof ADSBAircraftIdentificationMessage) {
                data.identificationMessage = (ADSBAircraftIdentificationMessage) message;
            } else {
                data.otherMessage = message;
            }

            //write back in map
            dataMap.put(key, data);
            updateTimer(key);
            //System.out.println("UPDATE HIIIIIIIER ="+ key);
        }
    }

    private void updateTimer(String key) {
        Calendar start = Calendar.getInstance();
        start.add(start.MINUTE, 4);
        timeMap.put(key,start);
    }

    private void deleteActive(String key) {
        dataMap.remove(key);
        timeMap.remove(key);
    }





}
