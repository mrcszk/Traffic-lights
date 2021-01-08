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
    Events ToRed, /**
     * The To green.
     */
    ToGreen, /**
     * The On red.
     */
    OnRed;


    /**
     * The Time red yellow.
     */
    final int timeRedYellow = 500;
    /**
     * The Time green.
     */
    final int timeGreen = 1000;

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

    ;

    /**
     * The States.
     */
    States states;

    /**
     * Instantiates a new Road.
     *
     * @param light   the light
     * @param panel   the panel
     * @param ToGreen the to green
     * @param ToRed   the to red
     * @param OnRed   the on red
     */
    public Road(TrafficLight light, JPanel panel, Events ToGreen, Events ToRed, Events OnRed) {
        this.light = light;
        this.panel = panel;

        this.ToRed = ToRed;
        this.ToGreen = ToGreen;
        this.OnRed = OnRed;

        start();
    }


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
                            evTimer.run(timeRedYellow);
                            break;
                        } else Thread.yield();
                    }
                    break;
                case ON_REDYELLOW:
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            evTimer.waitEvent();
                            SetGreen();
                            states = States.ON_GREEN;

                            evTimer = new Events();
                            evTimer.run(timeRedYellow);
                            break;
                        } else Thread.yield();
                    }
                    break;
                case ON_GREEN:
                    while (true) {
                        if (ToRed.arrivedEvent()) {
                            ToRed.waitEvent();
                            SetYellow();
                            states = States.ON_YELLOW;

                            evTimer = new Events();
                            evTimer.run(timeGreen);
                            break;
                        } else Thread.yield();
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
                        } else Thread.yield();
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
