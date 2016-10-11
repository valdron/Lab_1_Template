package client;
import java.util.Observable;
import java.util.Observer;

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
		while(true)
		{
			display.display(queue.poll());
		}
	}
}
