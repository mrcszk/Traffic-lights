package traffic_lights;

import java.awt.Color;

import javax.swing.JPanel;



public class Road extends Thread{
	TrafficLight light;
	JPanel panel;
	Timer timer;
	Events evTimer;

	Events ToRed, ToGreen, OnRed;


	final int timeRedYellow = 500;
	final int timeGreen = 1000;
	final int timeYellow = 500;



	enum States {
		ON_RED, ON_REDYELLOW, ON_GREEN, ON_YELLOW
	};

	States states;

	public Road(TrafficLight light, JPanel panel, Events ToGreen, Events ToRed, Events OnRed) {
		this.light = light;
		this.panel = panel;

		this.ToRed = ToRed;
		this.ToGreen = ToGreen;
		this.OnRed = OnRed;

		start();
	}

	// du¿e œwiat³a

	public void run() {
		SetRed();
		states = States.ON_RED;
		while (true) {
			switch (states) {
				case ON_RED:
					while (true) {
						if (ToGreen.arrivedEvent()) {
							ToGreen.waitEvent();
							SetRedYellow();
							states = States.ON_REDYELLOW;

							evTimer = new Events();
							timer = new Timer(timeRedYellow, evTimer);
							break;
						} else	Thread.yield();
					}
					break;
				case ON_REDYELLOW:
					while (true) {
						if (evTimer.arrivedEvent()) {
							evTimer.waitEvent();
							SetGreen();
							states = States.ON_GREEN;

							evTimer = new Events();
							timer = new Timer(timeRedYellow, evTimer);
							break;
						}  else Thread.yield();
					}
					break;
				case ON_GREEN:
					while (true){
						if (ToRed.arrivedEvent()) {
							ToRed.waitEvent();
							SetYellow();
							states = States.ON_YELLOW;

							evTimer = new Events();
							timer = new Timer(timeGreen, evTimer);
							break;
						}else Thread.yield();
					}
					break;
				case ON_YELLOW:
					while (true) {
						if (evTimer.arrivedEvent()) {
							evTimer.waitEvent();
							SetRed();
							states = States.ON_RED;

							OnRed.sendEvent();
							break;
						}else Thread.yield();
					}
					break;
			}
		}
	}

	private void SetRed() {
		setLight(1, Color.RED);
		setLight(2, Color.LIGHT_GRAY);
		setLight(3, Color.LIGHT_GRAY);
	}

	private void SetYellow() {
		setLight(1, Color.LIGHT_GRAY);
		setLight(2, Color.YELLOW);
		setLight(3, Color.LIGHT_GRAY);
	}

	private void SetGreen() {
		setLight(1, Color.LIGHT_GRAY);
		setLight(2, Color.LIGHT_GRAY);
		setLight(3, Color.GREEN);
	}

	private void SetRedYellow() {
		setLight(1, Color.RED);
		setLight(2, Color.YELLOW);
		setLight(3, Color.LIGHT_GRAY);
	}

	public void setLight(int place, Color color) {
		light.colorLight[place - 1] = color;
		panel.repaint();
	}
}
