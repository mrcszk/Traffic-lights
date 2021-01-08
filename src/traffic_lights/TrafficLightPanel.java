package traffic_lights;

import javax.swing.*;
import java.awt.*;

/**
 * The type Traffic light panel.
 */
class TrafficLightPanel extends JPanel {
    /**
     * The Lights.
     */
    TrafficLight[] lights;

    /**
     * Instantiates a new Traffic light panel.
     *
     * @param lights the lights
     */
    public TrafficLightPanel(TrafficLight[] lights) {
        super();
        setLayout(null);
        setBackground(Color.WHITE);
        this.lights = lights;
    }

    public void paintComponent(Graphics page) {
        int i;
        int[] x = new int[5];
        int[] y = new int[5];
        super.paintComponent(page);
        setBackground(Color.DARK_GRAY);

        page.setColor(new Color(0, 102, 0));
        page.fill3DRect(0, 350, 250, 100, true);

        page.setColor(new Color(0, 102, 0));
        page.fill3DRect(650, 350, 230, 100, true);

        x[0] = 880;
        x[1] = 880;
        x[2] = 650;
        x[3] = 650;
        y[0] = 570;
        y[1] = 830;
        y[2] = 830;
        y[3] = 570;
        page.setColor(new Color(0, 102, 0));
        page.fillPolygon(x, y, 4);

        x[0] = 880;
        x[1] = 880;
        x[2] = 650;
        x[3] = 650;
        y[0] = 230;
        y[1] = 0;
        y[2] = 0;
        y[3] = 230;
        page.setColor(new Color(0, 102, 0));
        page.fillPolygon(x, y, 4);

        page.setColor(new Color(0, 102, 0));
        page.fill3DRect(390, 570, 120, 240, true);

        page.setColor(new Color(0, 102, 0));
        page.fill3DRect(390, 0, 120, 230, true);

        x[0] = 0;
        x[1] = 0;
        x[2] = 250;
        x[3] = 250;
        y[0] = 570;
        y[1] = 830;
        y[2] = 830;
        y[3] = 570;
        page.setColor(new Color(0, 102, 0));
        page.fillPolygon(x, y, 4);

        x[0] = 0;
        x[1] = 0;
        x[2] = 250;
        x[3] = 250;
        y[0] = 230;
        y[1] = 0;
        y[2] = 0;
        y[3] = 230;
        page.setColor(new Color(0, 102, 0));
        page.fillPolygon(x, y, 4);

        page.setColor(Color.white);
        page.fill3DRect(755, 288, 230, 5, true);
        page.setColor(Color.white);
        page.fill3DRect(755, 508, 230, 5, true);

        page.setColor(Color.white);
        page.fill3DRect(0, 288, 150, 5, true);
        page.setColor(Color.white);
        page.fill3DRect(0, 508, 150, 5, true);


        page.setColor(Color.white);
        page.fill3DRect(580, 650, 5, 160, true);

        page.setColor(Color.white);
        page.fill3DRect(315, 650, 5, 160, true);

        page.setColor(Color.white);
        page.fill3DRect(580, 0, 5, 150, true);


        page.setColor(Color.white);
        page.fill3DRect(315, 0, 5, 150, true);

        page.setColor(Color.WHITE);

        for (i = 0; i < 4; i++) {
            page.fillRect(150, 233 + (i * 30), 90, 25);
            page.fillRect(665, 233 + (i * 30), 90, 25);
            page.fillRect(150, 453 + (i * 30), 90, 25);
            page.fillRect(665, 453 + (i * 30), 90, 25);
            page.fillRect(260 + (i * 30), 570, 25, 80);
            page.fillRect(525 + (i * 30), 570, 25, 80);
            page.fillRect(260 + (i * 30), 150, 25, 80);
            page.fillRect(525 + (i * 30), 150, 25, 80);
        }

        page.setFont(new Font("Courier New", Font.BOLD, 12));
        for (i = 0; i < lights.length; i++) {
            lights[i].draw(page);
            page.setColor(Color.BLACK);
            page.drawString(Integer.toString(i), lights[i].xP[0] + lights[i].diameter / 2 - 3, lights[i].yP[0] + lights[i].diameter / 2);
        }

        page.setColor(Color.WHITE);

        page.fillRect(555, 713, 7, 35);
        page.fillRect(540, 713, 15, 7);
        x[0] = 280 + 240;
        x[1] = 300 + 240;
        x[2] = 300 + 240;
        y[0] = 227 + 490;
        y[1] = 217 + 490;
        y[2] = 237 + 490;
        page.fillPolygon(x, y, 3);

        page.fillRect(605, 700, 7, 50);
        page.fillRect(612, 713, 15, 7);
        x[0] = 405 + 240;
        x[1] = 385 + 240;
        x[2] = 385 + 240;
        y[0] = 227 + 490;
        y[1] = 217 + 490;
        y[2] = 237 + 490;
        page.fillPolygon(x, y, 3);
        x[0] = 368 + 240;
        x[1] = 358 + 240;
        x[2] = 378 + 240;
        y[0] = 190 + 490;
        y[1] = 210 + 490;
        y[2] = 210 + 490;
        page.fillPolygon(x, y, 3);

        page.fillRect(815, 300, 35, 7);
        page.fillRect(815, 307, 7, 15);
        x[0] = 578 + 240;
        x[1] = 568 + 240;
        x[2] = 588 + 240;
        y[0] = 342;
        y[1] = 322;
        y[2] = 322;
        page.fillPolygon(x, y, 3);

        page.fillRect(802, 270, 50, 7);
        page.fillRect(815, 255, 7, 15);
        x[0] = 578 + 240;
        x[1] = 568 + 240;
        x[2] = 588 + 240;
        y[0] = 235;
        y[1] = 255;
        y[2] = 255;
        page.fillPolygon(x, y, 3);
        x[0] = 542 + 240;
        x[1] = 562 + 240;
        x[2] = 562 + 240;
        y[0] = 273;
        y[1] = 263;
        y[2] = 283;
        page.fillPolygon(x, y, 3);

        page.fillRect(50, 523, 50, 7);
        page.fillRect(80, 530, 7, 15);
        x[0] = 120;
        x[1] = 100;
        x[2] = 100;
        y[0] = 527;
        y[1] = 517;
        y[2] = 537;
        page.fillPolygon(x, y, 3);
        x[0] = 83;
        x[1] = 73;
        x[2] = 93;
        y[0] = 563;
        y[1] = 543;
        y[2] = 543;
        page.fillPolygon(x, y, 3);

        page.fillRect(50, 487, 37, 7);
        page.fillRect(80, 473, 7, 15);
        x[0] = 83;
        x[1] = 73;
        x[2] = 93;
        y[0] = 453;
        y[1] = 473;
        y[2] = 473;
        page.fillPolygon(x, y, 3);

        page.fillRect(330, 53, 7, 37);
        page.fillRect(337, 83, 15, 7);
        x[0] = 372;
        x[1] = 352;
        x[2] = 352;
        y[0] = 86;
        y[1] = 76;
        y[2] = 96;
        page.fillPolygon(x, y, 3);

        page.fillRect(295, 53, 7, 50);
        page.fillRect(280, 83, 15, 7);
        x[0] = 262;
        x[1] = 282;
        x[2] = 282;
        y[0] = 86;
        y[1] = 76;
        y[2] = 96;
        page.fillPolygon(x, y, 3);
        x[0] = 298;
        x[1] = 288;
        x[2] = 308;
        y[0] = 123;
        y[1] = 103;
        y[2] = 103;
        page.fillPolygon(x, y, 3);


    }
}
