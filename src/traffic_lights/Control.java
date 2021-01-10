package traffic_lights;

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
    Events[]
    /**
     * The To green.
     */
    toGreen, /**
     * The to red.
     */
    toRed;

    /**
     * The G 0.
     */
    Set<Integer> g0;
    /**
     * The G 1.
     */
    Set<Integer> g1;
    /**
     * The G 2.
     */
    Set<Integer> g2;
    /**
     * The G 3.
     */
    Set<Integer> g3;
    /**
     * The G 4.
     */
    Set<Integer> g4;
    /**
     * The G 5.
     */
    Set<Integer> g5;


    Events evTimer;
    /**
     * The Time replace groups.
     */
    final int timeReplaceGroups = 4000;
    /**
     * The Time delay green to red.
     */
    final int timeDelayGreenToRed = 500;


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
    public Control(Events[] toGreen, Events[] toRed) {
        super();
        this.toGreen = toGreen;
        this.toRed = toRed;

        g0 = new HashSet<>(Arrays.asList(0, 16, 17, 18, 19));
        g1 = new HashSet<>(Arrays.asList(2, 20, 21, 22, 23));
        g2 = new HashSet<>(Arrays.asList(4, 8, 9, 10, 11));
        g3 = new HashSet<>(Arrays.asList(6, 12, 13, 14, 15));
        g4 = new HashSet<>(Arrays.asList(1, 5));
        g5 = new HashSet<>(Arrays.asList(3, 7));

        start();
    }

    public void run() {
        System.out.println("Started run");
        setGreen(g0);
        evTimer = new Events();
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
                    while (state == State.G0_TO_RED) {
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
                        } else
                            Thread.yield();
                    }
                }
                case G1_TO_RED -> {
                    System.out.println("G1_TO_RED");
                    while (state == State.G1_TO_RED) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g2, State.G2_TO_GREEN);
                            break;
                        } else
                            Thread.yield();

                    }
                }
                case G2_TO_GREEN -> {
                    System.out.println("G2_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g2, State.G2_TO_RED);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G2_TO_RED -> {
                    System.out.println("G2_TO_RED");
                    while (state == State.G2_TO_RED) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g3, State.G3_TO_GREEN);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G3_TO_GREEN -> {
                    System.out.println("G3_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g3, State.G3_TO_RED);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G3_TO_RED -> {
                    System.out.println("G3_TO_RED");
                    while (state == State.G3_TO_RED) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g4, State.G4_TO_GREEN);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G4_TO_GREEN -> {
                    System.out.println("G4_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g4, State.G4_TO_RED);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G4_TO_RED -> {
                    System.out.println("G4_TO_RED");
                    while (state == State.G4_TO_RED) {
                        if (evTimer.arrivedEvent()) {
                            goStateToGreen(g5, State.G5_TO_GREEN);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G5_TO_GREEN -> {
                    System.out.println("G5_TO_GREEN");
                    while (true) {
                        if (evTimer.arrivedEvent()) {
                            goStateToRed(g5, State.G5_TO_RED);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G5_TO_RED -> {
                    System.out.println("G5_TO_RED");
                    while (state == State.G5_TO_RED) {
                        if (evTimer.arrivedEvent()) {
//                            run();
                            goStateToGreen(g0, State.G0_TO_GREEN);
                            break;
                        } else
                            Thread.yield();
                    }
                }
            }
        }
    }


    private void goStateToGreen(Set<Integer> g, State gState) {
        evTimer.waitEvent();
        setGreen(g);
        state = gState;
        evTimer = new Events();
        evTimer.run(timeReplaceGroups);
    }

    private void goStateToRed(Set<Integer> g, State gState){
        evTimer.waitEvent();
        setRed(g);
        state = gState;
        evTimer = new Events();
        evTimer.run(timeDelayGreenToRed);
    }

    private void setGreen(Set<Integer> g) {
        for (Integer num : g) {
            toGreen[num].sendEvent();
        }
    }

    private void setRed(Set<Integer> g) {
        for (Integer num : g) {
            toRed[num].sendEvent();
        }
    }

}
