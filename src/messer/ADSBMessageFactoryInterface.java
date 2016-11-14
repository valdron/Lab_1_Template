package messer;

import senser.ADSBSentence;

/**
 * Created by Paul Seehofer on 13.11.2016.
 */
public interface ADSBMessageFactoryInterface {
    ADSBMessage fromADSBSentence(ADSBSentence sentence);
}
