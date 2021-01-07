package traffic_lights;

import javax.swing.*;
import java.awt.*;

class TrafficLightPanel extends JPanel{
	TrafficLight  lights[];

	public TrafficLightPanel(TrafficLight[] lights)
	{
		super();
		setLayout(null);
		setBackground(Color.WHITE);
		this.lights=lights;
	}

	public void paintComponent(Graphics page)
	{
		int i, j;
		int[] x =new int[5];
		int[] y =new int[5];
		super.paintComponent(page);
		setBackground(Color.DARK_GRAY);

// LEWY PASEK
//		page.setColor(Color.BLUE);
//		page.draw3DRect(0,350,260,100,true);
		page.setColor(Color.green);
		page.fill3DRect(0,350,250,100,true);


		//PRAWY PASEK
		page.setColor(Color.BLUE);
		page.draw3DRect(650,350,230,100,true);
		page.setColor(Color.green);
		page.fill3DRect(650,350,230,100,true);

		//PRAWY DOL
		x[0]=880  ; x[1]=880 ; x[2]=650 ; x[3]=650 ;
		y[0]=570 ;  y[1]=830 ;  y[2]=830 ;  y[3]=570 ;
		page.setColor(Color.green);
		page.fillPolygon(x,y,4);
		page.setColor(Color.BLUE);
		page.drawPolygon(x,y,4);

		//PRAWY GORA
		x[0]=880  ; x[1]=880 ; x[2]=650 ;  x[3]=650 ;
		y[0]=230 ;  y[1]=0 ;  y[2]=0 ;  y[3]=230 ;
		page.setColor(Color.green);
		page.fillPolygon(x,y,4);
		page.setColor(Color.BLUE);
		page.drawPolygon(x,y,4);


// DOLNY PASEK
//		page.setColor(Color.BLUE);
//		page.draw3DRect(450,550,100,230,true);
		page.setColor(Color.green);
		page.fill3DRect(390,570,120,240,true);

// GORNY PASEK
//		page.setColor(Color.BLUE);
//		page.draw3DRect(450,550,100,230,true);
		page.setColor(Color.green);
		page.fill3DRect(390,0,120,230,true);


//LEWA DOL
		x[0]=0  ; x[1]=0 ; x[2]=250 ; x[3]=250 ;
		y[0]=570 ;  y[1]=830 ;  y[2]=830 ; y[3]=570 ;
		page.setColor(Color.green);
		page.fillPolygon(x,y,4);
		page.setColor(Color.BLUE);
		page.drawPolygon(x,y,4);
		page.setColor(Color.BLACK);

//LEWY GORA
		x[0]=0  ; x[1]=0 ; x[2]=250;  x[3]=250 ;
		y[0]=230 ;  y[1]=0 ;  y[2]=0 ; y[3]=230 ;
		page.setColor(Color.green);
		page.fillPolygon(x,y,4);
//		page.setColor(Color.BLUE);
//		page.drawPolygon(x,y,5);

		page.setColor(Color.white);
		page.fill3DRect(755,288,230,5,true);
		page.setColor(Color.white);
		page.fill3DRect(755,508,230,5,true);

		page.setColor(Color.white);
		page.fill3DRect(0,288,150,5,true);
		page.setColor(Color.white);
		page.fill3DRect(0,508,150,5,true);


		page.setColor(Color.white);
		page.fill3DRect(580,650,5,160,true);

		page.setColor(Color.white);
		page.fill3DRect(315,650,5,160,true);

		page.setColor(Color.white);
		page.fill3DRect(580,0,5,150,true);


		page.setColor(Color.white);
		page.fill3DRect(315,0,5,150,true);

		page.setColor(Color.WHITE);

		for (i=0;i<4;i++)
		{
			page.fillRect(150,233+(i*30),90,25);
			page.fillRect(665,233+(i*30),90,25);
			page.fillRect(150,453+(i*30),90,25);
			page.fillRect(665,453+(i*30),90,25);
			page.fillRect(260+(i*30),570,25,80);
			page.fillRect(525+(i*30),570,25,80);
			page.fillRect(260+(i*30),150,25,80);
			page.fillRect(525+(i*30),150,25,80);
		}

		page.setFont(new Font("Courier New",Font.CENTER_BASELINE,12));
		for(i=0;i<lights.length;i++)
		{
			lights[i].draw(page);
			page.setColor(Color.BLACK);
			page.drawString(Integer.toString(i),lights[i].xP[0]+lights[i].diameter/2-3,lights[i].yP[0]+lights[i].diameter/2);
		}

//		page.setColor(Color.WHITE);
//
//		page.fillRect(580,710,15,40);
//		page.fillRect(550,710,30,15);
//		x[0]=280+240 ; x[1]=310+240 ;x[2]=310+240 ;
//		y[0]=227+490 ; y[1]=212+490 ;y[2]=242+490 ;
//		page.fillPolygon(x,y,3);



	}
}
