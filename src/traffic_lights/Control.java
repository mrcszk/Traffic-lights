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
    Events[] toRed, /**
     * The To green.
     */
    toGreen, /**
     * The On red.
     */
    onRed;

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


    /**
     * The Ev timer.
     */
//	Timer timer;
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
         * G 0 to red state.
         */
        G0_TO_RED,
        /**
         * G 0 in red state.
         */
        G0_IN_RED,
        /**
         * G 1 to green state.
         */
        G1_TO_GREEN,
        /**
         * G 1 to red state.
         */
        G1_TO_RED,
        /**
         * G 1 in red state.
         */
        G1_IN_RED,
        /**
         * G 2 to green state.
         */
        G2_TO_GREEN,
        /**
         * G 2 to red state.
         */
        G2_TO_RED,
        /**
         * G 2 in red state.
         */
        G2_IN_RED,
        /**
         * G 3 to green state.
         */
        G3_TO_GREEN,
        /**
         * G 3 to red state.
         */
        G3_TO_RED,
        /**
         * G 3 in red state.
         */
        G3_IN_RED,
        /**
         * G 4 to green state.
         */
        G4_TO_GREEN,
        /**
         * G 4 to red state.
         */
        G4_TO_RED,
        /**
         * G 4 in red state.
         */
        G4_IN_RED,
        /**
         * G 5 to green state.
         */
        G5_TO_GREEN,
        /**
         * G 5 to red state.
         */
        G5_TO_RED,
        /**
         * G 5 in red state.
         */
        G5_IN_RED
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
     * @param onRed   the on red
     */
    public Control(Events[] toRed, Events[] toGreen, Events[] onRed) {
        super();
        this.toRed = toRed;
        this.toGreen = toGreen;
        this.onRed = onRed;

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
                            evTimer.waitEvent();
                            setRed(g0);
                            state = State.G0_TO_RED;
                            break;
                        } else Thread.yield();
                    }
                }
                case G0_TO_RED -> {
                    System.out.println("G0_TO_RED");
                    while (true) {
                        if (isOnRed(g0)) {
                            state = State.G0_IN_RED;
                            evTimer = new Events();
                            evTimer.run(timeDelayGreenToRed);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G0_IN_RED -> {
                    System.out.println("G0_IN_RED");
                    while (state == State.G0_IN_RED) {
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
                            evTimer.waitEvent();
                            setRed(g1);
                            state = State.G1_TO_RED;
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G1_TO_RED -> {
                    System.out.println("G1_TO_RED");
                    while (true) {
                        if (isOnRed(g1)) {
                            state = State.G1_IN_RED;
                            evTimer = new Events();
                            evTimer.run(timeDelayGreenToRed);

                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G1_IN_RED -> {
                    System.out.println("G1_IN_RED");
                    while (state == State.G1_IN_RED) {
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
                            evTimer.waitEvent();
                            setRed(g2);
                            state = State.G2_TO_RED;
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G2_TO_RED -> {
                    System.out.println("G2_TO_RED");
                    while (true) {
                        if (isOnRed(g2)) {
                            state = State.G2_IN_RED;
                            evTimer = new Events();
                            evTimer.run(timeDelayGreenToRed);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G2_IN_RED -> {
                    System.out.println("G2_IN_RED");
                    while (state == State.G2_IN_RED) {
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
                            evTimer.waitEvent();
                            setRed(g3);
                            state = State.G3_TO_RED;
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G3_TO_RED -> {
                    System.out.println("G3_TO_RED");
                    while (true) {
                        if (isOnRed(g3)) {
                            state = State.G3_IN_RED;
                            evTimer = new Events();
                            evTimer.run(timeDelayGreenToRed);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G3_IN_RED -> {
                    System.out.println("G3_IN_RED");
                    while (state == State.G3_IN_RED) {
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
                            evTimer.waitEvent();
                            setRed(g4);
                            state = State.G4_TO_RED;
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G4_TO_RED -> {
                    System.out.println("G4_TO_RED");
                    while (true) {
                        if (isOnRed(g4)) {
                            state = State.G4_IN_RED;
                            evTimer = new Events();
                            evTimer.run(timeDelayGreenToRed);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G4_IN_RED -> {
                    System.out.println("G4_IN_RED");
                    while (state == State.G4_IN_RED) {
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
                            evTimer.waitEvent();
                            setRed(g5);
                            state = State.G5_TO_RED;
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G5_TO_RED -> {
                    System.out.println("G5_TO_RED");
                    while (true) {
                        if (isOnRed(g5)) {
                            state = State.G5_IN_RED;
                            evTimer = new Events();
                            evTimer.run(timeDelayGreenToRed);
                            break;
                        } else
                            Thread.yield();
                    }
                }
                case G5_IN_RED -> {
                    System.out.println("G5_IN_RED");
                    while (state == State.G5_IN_RED) {
                        if (evTimer.arrivedEvent()) {
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
        setGreen(g);
        state = gState;
        evTimer = new Events();
        evTimer.run(timeReplaceGroups);
    }

    private boolean isOnRed(Set<Integer> g) {
        boolean flag = true;
        for (Integer num : g) {
            flag = flag && onRed[num].arrivedEvent();
        }
        if (flag)
            for (Integer num : g) {
                onRed[num].waitEvent();
            }
        return flag;
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
