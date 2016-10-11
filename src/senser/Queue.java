package senser;
import java.io.Serializable;
import java.util.LinkedList;

public class Queue<T> implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Object key;
	LinkedList<T> queue;

	public Queue()
	{
		key = new Object();
		queue = new LinkedList<T>();
	}

	public boolean offer(T t)
	{
		// Synchronise the access to the queue
		synchronized (key)
		{
			boolean returnValue = queue.offer(t);
//			System.out.println("offered");

			// If there is only the current job
			// in the queue, than notify the dispatcher
			if (queue.size() == 1)
			{
				key.notify();
			}
			return returnValue;
		}
	}

	public T poll()
	{
		// Synchronize the access to the queue
		synchronized (key)
		{
			if (queue.size() == 0)
			{
				try
				{
					key.wait();
				}
				catch (InterruptedException e)
				{
					// It doesn't mater if it is interrupted
					return null;
				}
			}
//			System.out.println("polled");
			return queue.poll();
		}
	}

	/**
	 * @return a current number jobs
	 */
	public int size()
	{
		synchronized (key)
		{
			return queue.size();
		}
	}
}
