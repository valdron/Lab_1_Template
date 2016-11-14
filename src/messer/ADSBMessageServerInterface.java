package messer;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public interface ADSBMessageServerInterface {
    ADSBMessageServer Constructor(String url);
    ADSBMessageObserver getObserver();

}
