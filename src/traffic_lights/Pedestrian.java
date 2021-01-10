package traffic_lights;

import javax.swing.*;
import java.awt.*;


/**
 * The type Pedestrian.
 */
class Pedestrian extends Thread {
    /**
     * The Light.
     */
    TrafficLight light;
    /**
     * The Panel.
     */
    JPanel panel;

    /**
     * The To red.
     */
    Events toRed, toGreen;


    /**
     * The enum State.
     */
    enum State {
        /**
         * On red state.
         */
        ON_RED,
        /**
         * On green state.
         */
        ON_GREEN
    };

    /**
     * The State.
     */
    State state;

    /**
     * Instantiates a new Pedestrian.
     *
     * @param light   the light
     * @param panel   the panel
     * @param toGreen the to green
     * @param toRed   the to red
     */
    public Pedestrian(TrafficLight light, TrafficLightPanel panel, Events toGreen, Events toRed) {
        super();

        this.light = light;
        this.panel = panel;

        this.toRed = toRed;
        this.toGreen = toGreen;

        start();
    }

    public void run() {
        state = State.ON_RED;
        setRed();
        while (true) {
            switch (state) {
                case ON_RED:
                    while (true) {
                        if (toGreen.arrivedEvent()){
                            toGreen.waitEvent();
                            setGreen();
                            state = State.ON_GREEN;
                            break;
                        } else Thread.yield();
                    }
                    break;
                case ON_GREEN:
                    while (true) {
                        if (toRed.arrivedEvent()) {
                            toRed.waitEvent();
                            setRed();
                            state = State.ON_RED;
                            break;
                        } else Thread.yield();
                    }
                    break;
            }
        }
    }


    private void setGreen() {
        setLight(1, Color.GRAY);
        setLight(2, Color.GREEN);
    }

    private void setRed() {
        setLight(1, Color.RED);
        setLight(2, Color.GRAY);
    }

    /**
     * Sets light.
     *
     * @param place the place
     * @param color the color
     */
    public void setLight(int place, Color color) {
        light.colorLight[place - 1] = color;
        panel.repaint();
    }
}
