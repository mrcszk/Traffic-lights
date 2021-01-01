package traffic_lights;

 
public class Timer extends Thread{
    private final long time;
    private final Events evTime;
	private boolean cancel=false;

    public Timer(long time, Events evTime){
        this.time=time;
        this.evTime=evTime;
        setDaemon(true);
        start();
    }
	
	public void cancel()
	{
		cancel=true;
	}

    public void run(){
        try 
		{
//        	System.out.println("timer started "+time);
            sleep(time);
//            System.out.println("timer finished "+time);
        } catch (InterruptedException ex) {}
        //System.out.println(cancel);
        if (!cancel)
			evTime.sendEvent();
    }

}