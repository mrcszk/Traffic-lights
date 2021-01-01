package traffic_lights;

import javax.swing.JFrame;

public class TrafficLightFrame extends JFrame{
	private final int WIDTH = 800, HEIGHT = 750;
	TrafficLightPanel myPanel;

	public TrafficLightFrame(String h, TrafficLight[] light){
		super(h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(90, -15);
		myPanel = new TrafficLightPanel(light);
		add(myPanel);
		pack();
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
	}
}

