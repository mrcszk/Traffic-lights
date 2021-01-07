package traffic_lights;


public class BuildTrafficLight
{

	public static void main(String[] args) 
	{
		final int numOfLights=4+12;//cars+pedestrians
		
//		Events[] toGreen = new Events[numOfLights];
//		Events[] toRed = new Events[numOfLights];
//		Events[] onRed = new Events[numOfLights];
//
//		for (int i = 0; i < numOfLights; i++){
//			toRed[i] = new Events();
//			toGreen[i] = new Events();
//			onRed[i] = new Events();
//		}


		TrafficLight[] light =new TrafficLight[24];
		light[0]=new TrafficLight(3,40,590,440,590,440+42,590,440+2*42);
		light[1]=new TrafficLight(3,40,530,440,530,440+42,530,440+2*42);

		light[2]=new TrafficLight(3,40,350,525,308,525,266,525);
		light[3]=new TrafficLight(3,40,350,465,308,465,266,465);

		light[4]=new TrafficLight(3,40,280,330,280,330-42,280,330-2*42);
		light[5]=new TrafficLight(3,40,340,330,340,330-42,340,330-2*42);

		light[6]=new TrafficLight(3,40,505,240,547,240,589,240);
		light[7]=new TrafficLight(3,40,505,290,547,290,589,290);

		light[8]=new TrafficLight(2,20,670,185,670,205);
		light[9]=new TrafficLight(2,20,670,375,670,355);
		light[10]=new TrafficLight(2,20,670,405,670,425);
		light[11]=new TrafficLight(2,20,670,595,670,575);

		light[12]=new TrafficLight(2,20,675,615,655,615);
		light[13]=new TrafficLight(2,20,465,615,485,615);
		light[14]=new TrafficLight(2,20,415,615,395,615);
		light[15]=new TrafficLight(2,20,205,615,225,615);

		light[16]=new TrafficLight(2,20,210,595,210,575);
		light[17]=new TrafficLight(2,20,210,405,210,425);
		light[18]=new TrafficLight(2,20,210,375,210,355);
		light[19]=new TrafficLight(2,20,210,185,210,205);


		light[20]=new TrafficLight(2,20,205,165,225,165);
		light[21]=new TrafficLight(2,20,415,165,395,165);
		light[22]=new TrafficLight(2,20,465,165,485,165);
		light[23]=new TrafficLight(2,20,675,165,655,165);



		TrafficLightFrame tlf = new TrafficLightFrame("Traffic lights", light);


//		for (int i = 0; i < 4; i++) {
//			new Road(light[i],tlf.myPanel,toGreen[i], toRed[i], onRed[i]);
//		}
//
//		for (int i = 4; i < light.length; i++){
//			new Pedestrian(light[i],tlf.myPanel,toGreen[i], toRed[i], onRed[i]);
//		}

//		new Control(toRed,toGreen, onRed);
	}
}
