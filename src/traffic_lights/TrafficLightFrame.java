package traffic_lights;

import javax.swing.*;


/**
 * The type Traffic light frame.
 */
public class TrafficLightFrame extends JFrame {
    TrafficLightPanel myPanel;

    /**
     * Instantiates a new Traffic light frame.
     *
     * @param h      the h
     * @param lights the lights
     */
    public TrafficLightFrame(String h, TrafficLight[] lights) {
        super(h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(90, -10);
        myPanel = new TrafficLightPanel(lights);
        add(myPanel);
        pack();
        int WIDTH = 890;
        int HEIGHT = 840;
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
    }
}

