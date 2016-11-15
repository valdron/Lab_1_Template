package client;
import java.math.BigInteger;
import java.util.Observable;
import java.util.Observer;

import messer.ADSBMessage;
import messer.ADSBMessageFactory;
import senser.ADSBSentence;
import senser.ADSBSentenceDisplay;
import senser.Queue;


public class Client extends Thread implements Observer
{
	private Queue<ADSBSentence> queue;
	
	public Client()
	{
		this.queue = new Queue<ADSBSentence>();
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		queue.offer((ADSBSentence) arg1);		
	}

	public void run()
	{ 
		ADSBSentenceDisplay display = new ADSBSentenceDisplay();
		ADSBMessageFactory messageFactory = new ADSBMessageFactory();
		while(true)
		{
			ADSBMessage message = messageFactory.fromADSBSentence(queue.poll());
		}
	}
}
