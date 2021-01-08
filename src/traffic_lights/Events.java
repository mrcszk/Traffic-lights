package traffic_lights;

import java.lang.Thread;


/**
 * The type Events.
 */
class Events extends Thread{
	private boolean inUse;
	private boolean waiting;

	/**
	 * Instantiates a new Events.
	 */
	public Events(){
		resetEvent();
	}

	/**
	 * Reset event.
	 */
	public void resetEvent(){
		waiting = false;
		inUse = false;
	}

	/**
	 * Run event.
	 */
	public void run(long time){
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sendEvent();
	}

	/**
	 * Arrived event boolean.
	 *
	 * @return the boolean
	 */
	public boolean arrivedEvent(){
		return inUse;
	}

	/**
	 * Wait event.
	 */
	public synchronized void waitEvent(){
		if (waiting)
			notify();

		if (!inUse){
			waiting = true;
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		resetEvent();
	}

	/**
	 * Send event.
	 */
	public void sendEvent(){
		while (!trySendEvent()) Thread.yield();
	}

	/**
	 * Try send event boolean.
	 *
	 * @return the boolean
	 */
	public synchronized boolean trySendEvent(){
		if (inUse)
			return false;

		inUse = true;

		if (waiting)
			notify();

		return true;
	}

}