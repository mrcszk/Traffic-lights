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
	Set<Integer> g3;
	Set<Integer> g4;
	Set<Integer> g5;


	Timer timer;
	Events evTimer;
	final int timeReplaceGroups = 4000;
	final int timeDelayGreenToRed = 500;


	enum State{
		G0_TO_GREEN, G0_TO_RED, G0_IN_RED,
		G1_TO_GREEN, G1_TO_RED, G1_IN_RED,
		G2_TO_GREEN, G2_TO_RED, G2_IN_RED,
		G3_TO_GREEN, G3_TO_RED, G3_IN_RED,
		G4_TO_GREEN, G4_TO_RED, G4_IN_RED,
		G5_TO_GREEN, G5_TO_RED, G5_IN_RED
	}

	State state;
	
	public Control(Events[] toRed, Events[] toGreen, Events[] onRed) {
		super();
		this.toRed = toRed;
		this.toGreen = toGreen;
		this.onRed = onRed;
		
		g0 = new HashSet<>(Arrays.asList(0, 16, 17, 18, 19));
		g1 = new HashSet<>(Arrays.asList(2, 20, 21,22,23));
		g2 = new HashSet<>(Arrays.asList(4,8,9,10,11));
		g3 = new HashSet<>(Arrays.asList(6,12,13,14,15));
		g4 = new HashSet<>(Arrays.asList(1,5));
		g5 = new HashSet<>(Arrays.asList(3,7));
		
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
							GoStateG3ToGreen();
							break;
						}
						else
							Thread.yield();
					}
					break;
				case G3_TO_GREEN:
					System.out.println("G3_TO_GREEN");
					while(true)
					{
						if(evTimer.arrivedEvent())
						{
							evTimer.waitEvent();
							setG3Red();
							state = State.G3_TO_RED;
							break;
						}else
							Thread.yield();
					}
					break;
				case G3_TO_RED:
					System.out.println("G3_TO_RED");
					while(true)
					{
						if(isG3OnRed())
						{
							state = State.G3_IN_RED;
							evTimer=new Events();
							timer=new Timer(timeDelayGreenToRed, evTimer);
							break;
						}else
							Thread.yield();
					}
					break;
				case G3_IN_RED:
					System.out.println("G3_IN_RED");
					while(state == State.G3_IN_RED)
					{
						if(evTimer.arrivedEvent())
						{
							GoStateG4ToGreen();
							break;
						}
						else
							Thread.yield();
					}
					break;
				case G4_TO_GREEN:
					System.out.println("G4_TO_GREEN");
					while(true)
					{
						if(evTimer.arrivedEvent())
						{
							evTimer.waitEvent();
							setG4Red();
							state = State.G4_TO_RED;
							break;
						}else
							Thread.yield();
					}
					break;
				case G4_TO_RED:
					System.out.println("G4_TO_RED");
					while(true)
					{
						if(isG4OnRed())
						{
							state = State.G4_IN_RED;
							evTimer=new Events();
							timer=new Timer(timeDelayGreenToRed, evTimer);
							break;
						}else
							Thread.yield();
					}
					break;
				case G4_IN_RED:
					System.out.println("G4_IN_RED");
					while(state == State.G4_IN_RED)
					{
						if(evTimer.arrivedEvent())
						{
							GoStateG5ToGreen();
							break;
						}
						else
							Thread.yield();
					}
					break;
				case G5_TO_GREEN:
					System.out.println("G5_TO_GREEN");
					while(true)
					{
						if(evTimer.arrivedEvent())
						{
							evTimer.waitEvent();
							setG5Red();
							state = State.G5_TO_RED;
							break;
						}else
							Thread.yield();
					}
					break;
				case G5_TO_RED:
					System.out.println("G5_TO_RED");
					while(true)
					{
						if(isG5OnRed())
						{
							state = State.G5_IN_RED;
							evTimer=new Events();
							timer=new Timer(timeDelayGreenToRed, evTimer);
							break;
						}else
							Thread.yield();
					}
					break;
				case G5_IN_RED:
					System.out.println("G5_IN_RED");
					while(state == State.G5_IN_RED)
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
	private void GoStateG0ToGreen() {
		setG0Green();
		state = State.G0_TO_GREEN;
		evTimer=new Events();
		timer=new Timer(timeReplaceGroups, evTimer);

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


	private void GoStateG3ToGreen() {
		setG3Green();
		state = State.G3_TO_GREEN;
		evTimer = new Events();
		timer = new Timer(timeReplaceGroups, evTimer);

	}
	private void GoStateG4ToGreen() {
		setG4Green();
		state = State.G4_TO_GREEN;
		evTimer = new Events();
		timer = new Timer(timeReplaceGroups, evTimer);
	}
	private void GoStateG5ToGreen() {
		setG5Green();
		state = State.G5_TO_GREEN;
		evTimer = new Events();
		timer = new Timer(timeReplaceGroups, evTimer);

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


	private boolean isG3OnRed() {
		boolean flag = true;

		for (Integer num : g3)
		{
			flag = flag && onRed[num].arrivedEvent();
		}
		if (flag)
			for (Integer num : g3) {
				onRed[num].waitEvent();
			}
		return flag;
	}
	private boolean isG4OnRed()
	{
		boolean flag=true;
		for (Integer num : g4) {
			flag = flag && onRed[num].arrivedEvent();
		}
		if(flag)
			for (Integer num : g4) {
				onRed[num].waitEvent();
			}
		return flag;
	}

	private boolean isG5OnRed()
	{
		boolean flag=true;
		for (Integer num : g5) {
			flag = flag && onRed[num].arrivedEvent();
		}
		if(flag)
			for (Integer num : g5) {
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

	private void setG3Green() {
		for (Integer num : g3) {
			toGreen[num].sendEvent();
		}
	}

	private void setG4Green() {
		for (Integer num : g4) {
			toGreen[num].sendEvent();
		}
	}
	private void setG5Green() {
		for (Integer num : g5) {
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

	private void setG3Red() {
		for (Integer num : g3) {
			toRed[num].sendEvent();
		}
	}
	private void setG4Red() {
		for (Integer num : g4) {
			toRed[num].sendEvent();
		}
	}
	private void setG5Red() {
		for (Integer num : g5) {
			toRed[num].sendEvent();
		}
	}
	

}
