package traffic_lights;

import java.lang.Thread;


class Events{
	private boolean inUse;
	private boolean waiting;

	public Events(){
		resetEvent();
	}

	public void resetEvent(){
		waiting = false;
		inUse = false;
	}

	public boolean arrivedEvent(){
		return inUse;
	}

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

	public void sendEvent(){
		while (!trySendEvent()) Thread.yield();
	}

	public synchronized boolean trySendEvent(){
		if (inUse)
			return false;

		inUse = true;

		if (waiting)
			notify();

		return true;
	}

}