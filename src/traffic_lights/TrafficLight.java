package traffic_lights;

import java.awt.*;


/**
 * The type Traffic light.
 */
class TrafficLight {
    /**
     * The Num of lights.
     */
    int numOfLights;
    /**
     * The X p.
     */
    int[] xP, /**
     * The Y p.
     */
    yP;
    /**
     * The Diameter.
     */
    int diameter;
    /**
     * The Color light.
     */
    Color[] colorLight;


    /**
     * Instantiates a new Traffic light.
     *
     * @param num the num
     * @param dia the dia
     * @param x0  the x 0
     * @param y0  the y 0
     * @param x1  the x 1
     * @param y1  the y 1
     */
    TrafficLight(int num, int dia, int x0, int y0, int x1, int y1) {
        numOfLights = num;
        diameter = dia;
        xP = new int[numOfLights];
        yP = new int[numOfLights];
        colorLight = new Color[numOfLights];
        colorLight[0] = Color.RED;
        colorLight[1] = Color.GRAY;
        xP[0] = x0;
        yP[0] = y0;
        xP[1] = x1;
        yP[1] = y1;
    }

    /**
     * Instantiates a new Traffic light.
     *
     * @param num the num
     * @param dia the dia
     * @param x0  the x 0
     * @param y0  the y 0
     * @param x1  the x 1
     * @param y1  the y 1
     * @param x2  the x 2
     * @param y2  the y 2
     */
    TrafficLight(int num, int dia, int x0, int y0, int x1, int y1, int x2, int y2) {
        numOfLights = num;
        diameter = dia;
        xP = new int[numOfLights];
        yP = new int[numOfLights];
        colorLight = new Color[numOfLights];
        colorLight[0] = Color.RED;
        colorLight[1] = Color.LIGHT_GRAY;
        colorLight[2] = Color.LIGHT_GRAY;
        xP[0] = x0;
        yP[0] = y0;
        xP[1] = x1;
        yP[1] = y1;
        xP[2] = x2;
        yP[2] = y2;
    }

    /**
     * Draw.
     *
     * @param page the page
     */
    void draw(Graphics page) {
        for (int i = 0; i < numOfLights; i++) {
            page.setColor(colorLight[i]);
            page.fillOval(xP[i], yP[i], diameter, diameter);
        }
    }
}
