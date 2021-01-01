package traffic_lights;

import java.awt.Color;

import javax.swing.JPanel;


class Pedestrian extends Thread {
	TrafficLight light;
	JPanel panel;

	Events toRed, toGreen, onRed;


	enum State {
		ON_RED, ON_GREEN
	};

	State state;

	public Pedestrian(TrafficLight light, TrafficLightPanel panel, Events toGreen, Events toRed, Events onRed) {
		super();

		this.light = light;
		this.panel = panel;

		this.toRed = toRed;
		this.toGreen = toGreen;
		this.onRed = onRed;

		start();
	}

	// przejscia dla pieszych

	public void run(){
		state = State.ON_RED;
		SetRed();
		while (true){
			switch (state) {
				case ON_RED:
					while (true){
						if (toGreen.arrivedEvent()){
							toGreen.waitEvent();
							SetGreen();
							state = State.ON_GREEN;
							break;
						}else Thread.yield();
					}
					break;
				case ON_GREEN:
					while (true){
						if (toRed.arrivedEvent()){
							toRed.waitEvent();
							SetRed();
							onRed.sendEvent();
							state = State.ON_RED;
							break;
						}else Thread.yield();
					}
					break;
			}
		}
	}


	private void SetGreen() {
		setLight(1, Color.GRAY);
		setLight(2, Color.GREEN);
	}

	private void SetRed() {
		setLight(1, Color.RED);
		setLight(2, Color.GRAY);
	}

	public void setLight(int place, Color color) {
		light.colorLight[place - 1] = color;
		panel.repaint();
	}
}
