package client;
import java.math.BigInteger;
import java.util.Observable;
import java.util.Observer;

import acamo.Gui;
import lab4.ActiveAircrafts;
import messer.ADSBMessage;
import messer.ADSBMessageFactory;
import senser.ADSBSentence;
import messer.ADSBMessageDisplay;
import senser.Queue;


public class Client extends Thread implements Observer
{
	private Queue<ADSBSentence> queue;
	
	public Client()
	{
		this.queue = new Queue<>();
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		queue.offer((ADSBSentence) arg1);		
	}

	public void run()
	{ 
		ADSBMessageDisplay messageDisplay = new ADSBMessageDisplay();
		ADSBMessageFactory messageFactory = new ADSBMessageFactory();
		ActiveAircrafts active = new ActiveAircrafts();
		//Gui gui = new Gui();
		while(true)
		{
			ADSBMessage message = messageFactory.fromADSBSentence(queue.poll());

			messageDisplay.display(message);
			//gui.selectMessage(message);

			active.updateActive(message);
		}
	}
}
