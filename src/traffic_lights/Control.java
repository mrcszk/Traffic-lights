package traffic_lights;

import jdk.jshell.EvalException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Control.
 */
public class Control extends Thread {

    /**
     * The To red.
     */
    Events[]  toGreen, toRed;

    Events evTimer = new Events();
    /**
     * The Time replace groups.
     */
    final int timeReplaceGroups = 4000;
    /**
     * The Time delay green to red.
     */
    final int timeDelayGreenToRed = 500;

    int[] g0, g1, g2, g3, g4, g5;

    /**
     * The enum State.
     */
    enum State {
        /**
         * G 0 to green state.
         */
        G0_TO_GREEN,
        /**
         * G 0 in red state.
         */
        G0_TO_RED,
        /**
         * G 1 to green state.
         */
        G1_TO_GREEN,
        /**
         * G 1 in red state.
         */
        G1_TO_RED,
        /**
         * G 2 to green state.
         */
        G2_TO_GREEN,
        /**
         * G 2 in red state.
         */
        G2_TO_RED,
        /**
         * G 3 to green state.
         */
        G3_TO_GREEN,
        /**
         * G 3 in red state.
         */
        G3_TO_RED,
        /**
         * G 4 to green state.
         */
        G4_TO_GREEN,
        /**
         * G 4 in red state.
         */
        G4_TO_RED,
        /**
         * G 5 to green state.
         */
        G5_TO_GREEN,
        /**
         * G 5 in red state.
         */
        G5_TO_RED
    }

    /**
     * The State.
     */
    State state;

    /**
     * Instantiates a new Control.
     *
     * @param toRed   the to red
     * @param toGreen the to green
     */
    public Control(Events[] toGreen, Events[] toRed, int[] g0, int[] g1, int[] g2, int[] g3, int[] g4, int[] g5) {
        super();
        this.toGreen = toGreen;
        this.toRed = toRed;

        this.g0 = g0;
        this.g1 = g1;
        this.g2 = g2;
        this.g3 = g3;
        this.g4 = g4;
        this.g5 = g5;

        start();
    }

    public void run() {
        System.out.println("Started run");
        setGreen(g0);
        evTimer.run(timeReplaceGroups);
        state = State.G0_TO_GREEN;
        while (true) {
            switch (state) {
                case G0_TO_GREEN -> {
                    System.out.println("G0_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g0, State.G0_TO_RED);
                            break;
                        } else Thread.yield();
                    }
                }
                case G0_TO_RED -> {
                    System.out.println("G0_TO_RED");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g1, State.G1_TO_GREEN);
                            break;
                      } else
                            Thread.yield();
                    }
                }
                case G1_TO_GREEN -> {
                    System.out.println("G1_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g1, State.G1_TO_RED);
                            break;
                        }
                            Thread.yield();
                    }
                }
                case G1_TO_RED -> {
                    System.out.println("G1_TO_RED");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g2, State.G2_TO_GREEN);
                            break;
                        }
                            Thread.yield();

                    }
                }
                case G2_TO_GREEN -> {
                    System.out.println("G2_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g2, State.G2_TO_RED);
                            break;
                        }
                            Thread.yield();
                    }
                }
                case G2_TO_RED -> {
                    System.out.println("G2_TO_RED");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g3, State.G3_TO_GREEN);
                            break;
                        }
                            Thread.yield();
                    }
                }
                case G3_TO_GREEN -> {
                    System.out.println("G3_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g3, State.G3_TO_RED);
                            break;
                        }
                            Thread.yield();
                    }
                }
                case G3_TO_RED -> {
                    System.out.println("G3_TO_RED");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g4, State.G4_TO_GREEN);
                            break;
                        }
                            Thread.yield();
                    }
                }
                case G4_TO_GREEN -> {
                    System.out.println("G4_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g4, State.G4_TO_RED);
                            break;
                        }
                            Thread.yield();
                    }
                }
                case G4_TO_RED -> {
                    System.out.println("G4_TO_RED");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g5, State.G5_TO_GREEN);
                            break;
                        }
                            Thread.yield();
                    }
                }
                case G5_TO_GREEN -> {
                    System.out.println("G5_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()){
                            goStateToRed(g5, State.G5_TO_RED);
                            break;
                        }
                            Thread.yield();
                    }
                }
                case G5_TO_RED -> {
                    System.out.println("G5_TO_RED");
                    while (true) {
                        if (evTimer.arrivedEvent()){
                            goStateToGreen(g0, State.G0_TO_GREEN);
                            break;
                        }
                            Thread.yield();
                    }
                }
            }
        }
    }


    private void goStateToGreen(int[] g, State gState) {
        evTimer.waitEvent();
        setGreen(g);
        state = gState;
        evTimer.run(timeReplaceGroups);
    }

    private void goStateToRed(int[] g, State gState){
        evTimer.waitEvent();
        setRed(g);
        state = gState;
        evTimer.run(timeDelayGreenToRed);
    }

    private void setGreen(int[]  g) {
        for (int num : g) {
            toGreen[num].sendEvent();
        }
    }

    private void setRed(int[]  g) {
        for (int num : g) {
            toRed[num].sendEvent();
        }
    }

}
