package traffic_lights;

import javax.swing.*;
import java.awt.*;


/**
 * The type Road.
 */
public class Road extends Thread {
    /**
     * The Light.
     */
    TrafficLight light;
    /**
     * The Panel.
     */
    JPanel panel;
    /**
     * The Ev timer.
     */
    Events evTimer = new Events();

    /**
     * The To red.
     */
    Events toRed, toGreen;

    /**
     * The Time red yellow.
     */
    final int timeRedYellow = 500;

    /**
     * The enum States.
     */
    enum States {
        /**
         * On red states.
         */
        ON_RED,
        /**
         * On redyellow states.
         */
        ON_REDYELLOW,
        /**
         * On green states.
         */
        ON_GREEN,
        /**
         * On yellow states.
         */
        ON_YELLOW
    }

    /**
     * The States.
     */
    States states;

    /**
     * Instantiates a new Road.
     *
     * @param light   the light
     * @param panel   the panel
     * @param toGreen the to green
     * @param toRed   the to red
     */
    public Road(TrafficLight light, JPanel panel, Events toGreen, Events toRed) {
        this.light = light;
        this.panel = panel;

        this.toRed = toRed;
        this.toGreen = toGreen;

        start();
    }


    public void run() {
        SetRed();
        states = States.ON_RED;
        while (true) {
            switch (states) {
                case ON_RED:
                    while (true) {
                        if (toGreen.arrivedEvent()) {
                            toGreen.waitEvent();
                            SetRedYellow();
                            states = States.ON_REDYELLOW;

                            evTimer.run(timeRedYellow);
                            break;
                        }  Thread.yield();
                    }
                    break;
                case ON_REDYELLOW:
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            evTimer.waitEvent();
                            SetGreen();
                            states = States.ON_GREEN;

                            break;
                        }  Thread.yield();
                    }
                    break;
                case ON_GREEN:
                    while (true) {
                        if (toRed.arrivedEvent()) {
                            toRed.waitEvent();
                            SetYellow();
                            states = States.ON_YELLOW;

                            evTimer.run(timeRedYellow);
                            break;
                        }  Thread.yield();
                    }
                    break;
                case ON_YELLOW:
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            evTimer.waitEvent();
                            SetRed();
                            states = States.ON_RED;

                            break;
                        }  Thread.yield();
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
