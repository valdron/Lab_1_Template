package messer;

public class ADSBMessageDisplay implements ADSBMessageDisplayInterface{

    @Override
    public void display(ADSBMessage message) {
        System.out.println(message);
    }
}
