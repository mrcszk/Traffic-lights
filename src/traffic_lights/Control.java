package traffic_lights;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Control extends Thread
{
	Events[] toRed, toGreen, onRed;

	Set<Integer> g0;
	Set<Integer> g1;
	Set<Integer> g2;
	
	Timer timer;
	Events evTimer;
	final int timeReplaceGroups = 2000;
	final int timeDelayGreenToRed = 500;


	enum State{
		G0_TO_GREEN, G0_TO_RED, G0_IN_RED,
		G1_TO_GREEN, G1_TO_RED, G1_IN_RED,
		G2_TO_GREEN, G2_TO_RED, G2_IN_RED
	}

	State state;
	
	public Control(Events[] toRed, Events[] toGreen, Events[] onRed) {
		super();
		this.toRed = toRed;
		this.toGreen = toGreen;
		this.onRed = onRed;
		
		g0 = new HashSet<>(Arrays.asList(0, 6, 7, 9, 10, 12, 13));
		g1 = new HashSet<>(Arrays.asList(1, 4, 5, 6, 7, 9, 10, 12, 13));
		g2 = new HashSet<>(Arrays.asList(2, 3, 4, 5, 8, 11,14,15));
		
		start();
	}

	// pilnowanie œwiate³

	public void run() {
		System.out.println("Started run");
		setG0Green();
		evTimer=new Events();
		timer=new Timer(timeReplaceGroups, evTimer);
		state = State.G0_TO_GREEN;
		while(true){
			switch (state){
				case G0_TO_GREEN:
					System.out.println("G0_TO_GREEN");
					while(true){
						if(evTimer.arrivedEvent()){
							evTimer.waitEvent();
							setG0Red();
							state = State.G0_TO_RED;
							break;
						}else Thread.yield();
					}
					break;
				case G0_TO_RED:
					System.out.println("G0_TO_RED");

					while(true){
						if(isG0OnRed()){
							state = State.G0_IN_RED;
							evTimer=new Events();
							timer=new Timer(timeDelayGreenToRed, evTimer);
							break;
						}else
							Thread.yield();
					}
					break;
				case G0_IN_RED:
					System.out.println("G0_IN_RED");
					while(state == State.G0_IN_RED){
						if(evTimer.arrivedEvent()){
							GoStateG1ToGreen();
							break;
						}else
							Thread.yield();
					}
					break;

				case G1_TO_GREEN:
					System.out.println("G1_TO_GREEN");
					while(true)
					{
						if(evTimer.arrivedEvent())
						{
							evTimer.waitEvent();
							setG1Red();
							state = State.G1_TO_RED;
							break;
						}else
							Thread.yield();
					}
					break;
				case G1_TO_RED:
					System.out.println("G1_TO_RED");
					while(true)
					{
						if(isG1OnRed())
						{
							state = State.G1_IN_RED;
							evTimer=new Events();
							timer=new Timer(timeDelayGreenToRed, evTimer);
							break;
						}else
							Thread.yield();
					}
					break;
				case G1_IN_RED:
					System.out.println("G1_IN_RED");
					while(state == State.G1_IN_RED)
					{
						if(evTimer.arrivedEvent())
						{
							GoStateG2ToGreen();
							break;
						}
						else
							Thread.yield();

					}
					break;
				case G2_TO_GREEN:
					System.out.println("G2_TO_GREEN");
					while(true)
					{
						if(evTimer.arrivedEvent())
						{
							evTimer.waitEvent();
							setG2Red();
							state = State.G2_TO_RED;
							break;
						}else
							Thread.yield();
					}
					break;
				case G2_TO_RED:
					System.out.println("G2_TO_RED");
					while(true)
					{
						if(isG2OnRed())
						{
							state = State.G2_IN_RED;
							evTimer=new Events();
							timer=new Timer(timeDelayGreenToRed, evTimer);
							break;
						}else
							Thread.yield();
					}
					break;
				case G2_IN_RED:
					System.out.println("G2_IN_RED");
					while(state == State.G2_IN_RED)
					{
						if(evTimer.arrivedEvent())
						{
							GoStateG0ToGreen();
							break;
						}
						else
							Thread.yield();
					}
					break;
			}
		}
	}

	private void GoStateG1ToGreen() {
		setG1Green();
		state = State.G1_TO_GREEN;
		evTimer=new Events();
		timer=new Timer(timeReplaceGroups, evTimer);
	}
	private void GoStateG2ToGreen() {
		setG2Green();
		state = State.G2_TO_GREEN;
		evTimer=new Events();
		timer=new Timer(timeReplaceGroups, evTimer);
		
	}
	private void GoStateG0ToGreen() {
		setG0Green();
		state = State.G0_TO_GREEN;
		evTimer=new Events();
		timer=new Timer(timeReplaceGroups, evTimer);
		
	}
	
	private boolean isG0OnRed() {
		boolean flag = true;

		for (Integer num : g0) 
		{
			flag = flag && onRed[num].arrivedEvent();
		}
		if (flag)
			for (Integer num : g0) {
				onRed[num].waitEvent();
			}
		return flag;
	}
	private boolean isG1OnRed()
		{
			boolean flag=true;
			for (Integer num : g1) {
				flag = flag && onRed[num].arrivedEvent();
			}
			if(flag)
				for (Integer num : g1) {
					onRed[num].waitEvent();
				}
			return flag;
		}

	private boolean isG2OnRed()
		{
			boolean flag=true;
			for (Integer num : g2) {
				flag = flag && onRed[num].arrivedEvent();
			}
			if(flag)
				for (Integer num : g2) {
					onRed[num].waitEvent();
				}
			return flag;
		}


	private void setG0Green() {
		for (Integer num : g0) {
			toGreen[num].sendEvent();
		}
	}

	private void setG1Green() {
		for (Integer num : g1) {
			toGreen[num].sendEvent();
		}
	}
	private void setG2Green() {
		for (Integer num : g2) {
			toGreen[num].sendEvent();
		}
	}
	
	private void setG0Red() {
		for (Integer num : g0) {
			toRed[num].sendEvent();
		}
	}
	private void setG1Red() {
		for (Integer num : g1) {
			toRed[num].sendEvent();
		}
	}
	private void setG2Red() {
		for (Integer num : g2) {
			toRed[num].sendEvent();
		}
	}
	

}
