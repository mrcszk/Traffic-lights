package traffic_lights;


public class BuildTrafficLight
{

	public static void main(String[] args) 
	{
		final int numOfLights=4+12;//cars+pedestrians
		
		Events[] toGreen = new Events[numOfLights];
		Events[] toRed = new Events[numOfLights];
		Events[] onRed = new Events[numOfLights];

		for (int i = 0; i < numOfLights; i++){
			toRed[i] = new Events();
			toGreen[i] = new Events();
			onRed[i] = new Events();
		}


		// RYSOWANIE SWIATEL
		// LICZBA ŒWIATE£, ŒREDNICA, PO£O¯ENIE KO£A

		TrafficLight[] light = new TrafficLight[numOfLights];
		light[0]=new TrafficLight(3,40,430,110,472,110,514,110);
		light[1]=new TrafficLight(3,40,450,310,450,352,450,394);
		light[2]=new TrafficLight(3,40,310,630,280,605,250,580);
		light[3]=new TrafficLight(3,40,350,350,308,350,266,350);

		light[4]=new TrafficLight(2,20,600,18,600,40);
		light[5]=new TrafficLight(2,20,600,227,600,205);
		light[6]=new TrafficLight(2,20,600,255,600,277);
		light[7]=new TrafficLight(2,20,600,455,600,433);
		light[8]=new TrafficLight(2,20,575,475,553,475);
		light[9]=new TrafficLight(2,20,140,608,150,590);
		light[10]=new TrafficLight(2,20,205,475,193,490);
		light[11]=new TrafficLight(2,20,230,475,250,475);
		light[12]=new TrafficLight(2,20,200,453,200,433);
		light[13]=new TrafficLight(2,20,200,255,200,277);
		light[14]=new TrafficLight(2,20,200,227,200,205);
		light[15]=new TrafficLight(2,20,200,18,200,40);



		TrafficLightFrame tlf = new TrafficLightFrame("Traffic lights", light);


		for (int i = 0; i < 4; i++) {
			new Road(light[i],tlf.myPanel,toGreen[i], toRed[i], onRed[i]);
		}

		for (int i = 4; i < light.length; i++){
			new Pedestrian(light[i],tlf.myPanel,toGreen[i], toRed[i], onRed[i]);
		}

		new Control(toRed,toGreen, onRed);
	}
}
