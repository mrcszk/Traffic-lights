package traffic_lights;

import javax.swing.*;


public class TrafficLightFrame extends JFrame
{
	private final int WIDTH = 890, HEIGHT = 840;
	TrafficLightPanel myPanel;

	public TrafficLightFrame(String h, TrafficLight[] ramzorim)
	{
		super(h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(90, -10);
		myPanel = new TrafficLightPanel(ramzorim);
		add(myPanel);
		pack();
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
	}
}

