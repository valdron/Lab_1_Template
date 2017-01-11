package lab4;

import messer.ADSBAirbonePositionMessage;
import messer.ADSBAirboneVelocityMessage;
import messer.ADSBAircraftIdentificationMessage;
import messer.ADSBMessage;

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


                while (true) {

                    synchronized (timeMap){
                        for(String key: timeMap.keySet()){
                            Calendar resetTime = timeMap.get(key);
                            if (resetTime.after(Calendar.getInstance())) {
                                deleteActive(key);
                            }
                        }
                    }

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
                data.positionMessage = (ADSBAirbonePositionMessage) message;
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
