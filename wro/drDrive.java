import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;

public class drDrive {
	 static EV3LargeRegulatedMotor mB = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("B"));
	 static EV3LargeRegulatedMotor mC = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("C"));
	 static EV3UltrasonicSensor ES1 = new EV3UltrasonicSensor(SensorPort.S2);//LEft;
	 static EV3UltrasonicSensor ES2 = new EV3UltrasonicSensor(SensorPort.S4);//Right;
	 static RegulatedMotor mA = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("A"));
	 static EV3ColorSensor EC1= new EV3ColorSensor(SensorPort.S1);
	 
	 public static void main(String[] args) {
		 
		 	
//			while (true) {
//				getCube();
//				sbros();
//				
//			}
		 getCube();
			sbros();
			 getCube();
				sbros();
				 getCube();
					sbros();
					mA.close();
					mB.close();
					mC.close();


	}
	 public static void getCube() {
			
			double reskek 	= (50*360)/	(Math.PI*62.4);
//			double rescube 	= (100*360)/	(Math.PI*62.4); 
//			double rescubeback 	= (50*360)/	(Math.PI*62.4); 
//			
//				Command.cubesHave++;
//				mA.resetTachoCount();
//
//				mB.stop(true);
//				mC.stop(true);
//				mA.stop(true);
				mA.rotate(-700);
				mA.stop(true);			
				mB.resetTachoCount();	
				mC.resetTachoCount();
				
				mC.setSpeed(160);	
				mB.setSpeed(164);
				while(mB.getTachoCount() < reskek && mC.getTachoCount() < reskek){
					mB.forward(); 	    
					mC.forward();

				}
				mB.stop(true);	
				mC.stop(true);			
				
				
//				mA.resetTachoCount();	
				mA.rotate(700);
				mA.stop(true);			
				mB.resetTachoCount();	
				mC.resetTachoCount();
				
				mC.setSpeed(160);
				mB.setSpeed(164);
				while(mB.getTachoCount() > -reskek && mC.getTachoCount() > -reskek){
					mB.backward(); 	  
					mC.backward();

				}
				mB.stop(true);		
				mC.stop(true);
		}
		public static void sbros (){//—брос  убика 
		//	Command.cubesHave = 0;

			mA.rotate(-700);
			mA.stop();
			
			
		}

}
