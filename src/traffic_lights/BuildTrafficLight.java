package traffic_lights;


/**
 * The type Build traffic light.
 */
public class BuildTrafficLight {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final int numOfLights = 24;//cars+pedestrians

        Events[] toGreen = new Events[numOfLights];
        Events[] toRed = new Events[numOfLights];

        for (int i = 0; i < numOfLights; i++){
            toRed[i] = new Events();
            toGreen[i] = new Events();
        }

        TrafficLight[] light = new TrafficLight[24];
        light[0] = new TrafficLight(3, 40, 590, 440, 590, 482, 590, 524);
        light[1] = new TrafficLight(3, 40, 530, 440, 530, 482, 530, 524);

        light[2] = new TrafficLight(3, 40, 350, 525, 308, 525, 266, 525);
        light[3] = new TrafficLight(3, 40, 350, 465, 308, 465, 266, 465);

        light[4] = new TrafficLight(3, 40, 280, 330, 280, 288, 280, 246);
        light[5] = new TrafficLight(3, 40, 340, 330, 340, 288, 340, 246);

        light[6] = new TrafficLight(3, 40, 505, 240, 547, 240, 589, 240);
        light[7] = new TrafficLight(3, 40, 505, 290, 547, 290, 589, 290);

        light[8] = new TrafficLight(2, 20, 670, 185, 670, 205);
        light[9] = new TrafficLight(2, 20, 670, 375, 670, 355);
        light[10] = new TrafficLight(2, 20, 670, 405, 670, 425);
        light[11] = new TrafficLight(2, 20, 670, 595, 670, 575);

        light[12] = new TrafficLight(2, 20, 675, 615, 655, 615);
        light[13] = new TrafficLight(2, 20, 465, 615, 485, 615);
        light[14] = new TrafficLight(2, 20, 415, 615, 395, 615);
        light[15] = new TrafficLight(2, 20, 205, 615, 225, 615);

        light[16] = new TrafficLight(2, 20, 210, 595, 210, 575);
        light[17] = new TrafficLight(2, 20, 210, 405, 210, 425);
        light[18] = new TrafficLight(2, 20, 210, 375, 210, 355);
        light[19] = new TrafficLight(2, 20, 210, 185, 210, 205);


        light[20] = new TrafficLight(2, 20, 205, 165, 225, 165);
        light[21] = new TrafficLight(2, 20, 415, 165, 395, 165);
        light[22] = new TrafficLight(2, 20, 465, 165, 485, 165);
        light[23] = new TrafficLight(2, 20, 675, 165, 655, 165);


        TrafficLightFrame tlf = new TrafficLightFrame("Traffic lights", light);

        for (int i = 0; i < 8; i++) {
            new Road(light[i], tlf.myPanel, toGreen[i], toRed[i]);
        }

        for (int i = 8; i < light.length; i++) {
            new Pedestrian(light[i], tlf.myPanel, toGreen[i], toRed[i]);
        }

        int[] g0 = {0, 16, 17, 18, 19,4};
        int[] g1 = {2, 20, 21, 22, 23,6};
        int[] g2 = {0, 4, 8, 9, 10, 11};
        int[] g3 = {2, 6, 12, 13, 14, 15};
        int[] g4 = {1, 5};
        int[] g5 = {3, 7};

        new Control(toGreen, toRed, g0, g1, g2, g3, g4, g5);
    }
}
