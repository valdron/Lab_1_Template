package senser;
import java.util.Observable;

public class Senser extends Observable implements Runnable
{
	StreamingWebClient client;

	public Senser(String uri)
	{
		client = new StreamingWebClient(uri, 512);
	}

	private String getSentence()
	{
		String filter = "\\d{10}\\.\\d{7}!ADS-B\\*[A-F0-9]{28}";
		// 10 numbers . 7 numers '!ADSB*' 28 Hexnumbers
		
		return client.readChunk(filter);
	}
	
	public void run()
	{
		ADSBSentence sentence;

		// Creating factory and display
		ADSBSentenceFactory factory = new ADSBSentenceFactory();
		ADSBSentenceDisplay display = new ADSBSentenceDisplay();
		
		while (true)
		{
			sentence = factory.fromWebdisJson(getSentence());
			
			if (sentence != null)
			{
				//Display the sentence
				display.display(sentence);
				
				//Notify all observers
				setChanged();
				notifyObservers(sentence);
			}			
		}		
	}
}
