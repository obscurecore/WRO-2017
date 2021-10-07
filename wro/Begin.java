import java.util.LinkedList;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.HiTechnicCompass;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Begin {
	
	public static int getDeg(){
		
	HiTechnicCompass HQ = new HiTechnicCompass(SensorPort.S3);//Compass
	SampleProvider H1 = HQ.getMode("Compass");
	 float[] Array = new float[H1.sampleSize()];			 
		H1.fetchSample(Array,0);			 
		float a= Array[0];
		int b = (int)a;
		return b;
	 
	}
	public static float getDeg(HiTechnicCompass HQ) {
		SampleProvider H1 = HQ.getMode("Compass");
		float[] Array = new float[H1.sampleSize()];			 
		H1.fetchSample(Array,0);			 
		float a= Array[0];
		return a;
//		int b = (int)a;
//		return b;
	}
}
