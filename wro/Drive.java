import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Drive {
//	 EV3LargeRegulatedMotor mB = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("B"));
//	 EV3LargeRegulatedMotor mC = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("C"));
//	 RegulatedMotor mA = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("A"));
//	 EV3ColorSensor EC1= new EV3ColorSensor(SensorPort.S1);
//	 EV3UltrasonicSensor ES1 = new EV3UltrasonicSensor(SensorPort.S2);//LEft;
//	 EV3UltrasonicSensor ES2 = new EV3UltrasonicSensor(SensorPort.S4);//Right;
	
	static int rotSpeed = 200; //speed for each motors for rotate
	static double degl90 = (90*129)/62.4; //125 - 135  128
	static double degr90 = (90*129)/62.4;
	static double deg180 = degl90*2; //((180)*130)/62.4;
	static int speedC = 200;
	static int sub = 4;
	static int speedB = speedC + sub;
	
	
	
	public static void rotatel(EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC){
		double tacho=0;
		double b = 0;
		double c = 0;
		mB.resetTachoCount();			mC.resetTachoCount();
//		while(tacho < deg90){
//			mB.forward();				mC.backward();
//			mB.setSpeed(rotSpeed);			mC.setSpeed(rotSpeed);
//			b = Math.abs(mB.getTachoCount());
//			c = Math.abs(mC.getTachoCount());
//			tacho=(b+c)/2.0;
//		}
		while(b < degr90 && c < degl90){
			mB.forward();				mC.backward();
			mB.setSpeed(rotSpeed);			mC.setSpeed(rotSpeed);
			b = Math.abs(mB.getTachoCount());
			c = Math.abs(mC.getTachoCount());
		//	tacho=(b+c)/2.0;
		}
		mC.stop(true);					mB.stop(true);
		}
	public static void rotater(EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC){
		double tacho=0;
		double b = 0;
		double c = 0;
		mB.resetTachoCount();			mC.resetTachoCount();
//		while(tacho < deg90){
//			mB.backward();				mC.forward();
//			mB.setSpeed(rotSpeed);			mC.setSpeed(rotSpeed);
//			b = Math.abs(mB.getTachoCount());
//			c = Math.abs(mC.getTachoCount());
//			tacho=(b+c)/2.0;
//		}
//		mC.stop(true);					mB.stop(true);
//		}
		while(b < degr90 && c < degl90){
			mB.backward();				mC.forward();
			mB.setSpeed(rotSpeed);			mC.setSpeed(rotSpeed);
			b = Math.abs(mB.getTachoCount());
			c = Math.abs(mC.getTachoCount());
			tacho=(b+c)/2.0;
		}
		mC.stop(true);					mB.stop(true);
		}
	public static void rotate180(EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC){
		rotater(mB, mC);
		rotater(mB, mC);
//		double tacho=0;
//		double b;
//		double c ;
//
//		mB.resetTachoCount();			mC.resetTachoCount();
//		while(tacho<deg180){
//			mB.forward();				mC.backward();
//			mB.setSpeed(rotSpeed);			mC.setSpeed(rotSpeed);
//			b = Math.abs(mB.getTachoCount());
//			c = Math.abs(mC.getTachoCount());
//			tacho=(b+c)/2.0;
//		}
//		mC.stop(true);					mB.stop(true);
	}

	
	public  static void tank(EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC, EV3UltrasonicSensor ES1, EV3UltrasonicSensor ES2){
		 		double 	b;		 	 		double c;
		 		double 	derr=0;
		 		double 	integ=0;
		 		double 	kd=5300;
		 		double 	ki=1200;
		 		double 	kp = 4000;
		 		double 	lasterror=0;
		 		int 	Vc;					int    	Vb;
		 		double 	v1;					double v2;
		 		double 	Error ;
		 		double 	target = 0.078;
		 		double 	v = 250;
		 		SampleProvider US1 = ES1.getMode("Distance");
		 		SampleProvider US2 = ES2.getMode("Distance");
		 		float[] Array1 = new float[US1.sampleSize()];
		 		float[] Array2 = new float[US2.sampleSize()];
		 		double 	tacho = 0;
		 		double 	res =  (300*360)/(Math.PI*62.4);
		 		double 	res2 = (125*360)/(Math.PI*62.4);
		 		double 	res3 = (225*360)/(Math.PI*62.4);
		 		mB.resetTachoCount();		
		 		mC.resetTachoCount();
		 		US1.fetchSample(Array1,0);			 
		 		float a1= Array1[0];
		 		US2.fetchSample(Array2,0);	
		 		float a2= Array2[0];
		 		int vwl=200;
	
		 		int speedC 	= 	200;
		 		int sub 	= 	4;
		 		int speedB  = 	speedC + sub;
		 		int subPID	=	0;
		 		
	if (a1>0.16&&a2>0.16){////////////////// стенок нет. движение моторами 
		while(mB.getTachoCount() < res && mC.getTachoCount() < res){
			mB.forward(); 	 
			mC.forward();
			mC.setSpeed(speedC);
			mB.setSpeed(speedB);
		}
		mC.stop(true);
		mB.stop(true);
	}
	else if(a1<0.16&&a2>0.16){	///////////////////// Движение по ху стенки 
		
		mB.resetTachoCount();
		mC.resetTachoCount();
		mB.stop(true);
		mC.stop(true);
		
		double 	resback =  -(50*360)/(Math.PI*62.4);
 		while(mB.getTachoCount() > resback && mC.getTachoCount() > resback){
 			mB.backward(); 	 
 			mC.backward();
 			mC.setSpeed(100);
 			mB.setSpeed(100+sub);
 		}
 		mB.resetTachoCount();
		mC.resetTachoCount();
		mB.stop(true);
		mC.stop(true);
		while(tacho<res2){
		US1.fetchSample(Array1,0);			  
		a1= Array1[0];
		Error = target-a1;
		v1= (v-kp*Error)-(kd*derr)-(ki*integ);
		v2= (v+kp*Error)+(kd*derr)+(ki*integ);
		derr= Error-lasterror;
		integ=0.5*integ+Error;
		Vc=(int) Math.round(v1);	Vb=(int) Math.round(v2);
		mC.forward();				mB.forward();
		mC.setSpeed(Vc+5);			mB.setSpeed(Vb+subPID);
		c=mC.getTachoCount();		b=mB.getTachoCount();
		tacho = (c+b)/2.0;	
		lasterror=Error;
		}
		mC.stop(true);				mB.stop(true);
		mB.resetTachoCount();
		mC.resetTachoCount();
	while(tacho<res3){
		mB.forward(); 	    		mC.forward();
		mC.setSpeed(vwl);			mB.setSpeed(vwl+sub);
		b= mB.getTachoCount();		c= mC.getTachoCount();
		tacho= (int) ((b+c)/2.0);
	}	
	mC.stop(true);					mB.stop(true);
	}
	else if (a1>0.16&&a2<0.16){ ///////////////////////// движение по ху2 стенки 
		mB.resetTachoCount();		mC.resetTachoCount();
		mB.stop(true);				mC.stop(true);
		double 	resback =  -(50*360)/(Math.PI*62.4);
 		while(mB.getTachoCount() > resback && mC.getTachoCount() > resback){
 			mB.backward(); 	 
 			mC.backward();
 			mC.setSpeed(100);
 			mB.setSpeed(100+sub);
 		}
 		mB.resetTachoCount();
		mC.resetTachoCount();
		mB.stop(true);
		mC.stop(true);
		while(tacho<res2){
		US2.fetchSample(Array2,0);			  
		a2= Array2[0];
		Error = a2-target;
		v1= (v-kp*Error)-(kd*derr)-(ki*integ);
		v2= (v+kp*Error)+(kd*derr)+(ki*integ);
		Vc=(int) Math.round(v1);	Vb=(int) Math.round(v2);
		mC.forward();				mB.forward();
		mC.setSpeed(Vc);			mB.setSpeed(Vb+subPID);
		lasterror=Error;

		c=mC.getTachoCount();
		b=mB.getTachoCount();
		tacho = (c+b)/2.0;
		}
	mC.stop(true);					mB.stop(true);
	mB.resetTachoCount();
	mC.resetTachoCount();
	while(tacho<res3){
		mB.forward(); 	    		mC.forward();
		mC.setSpeed(vwl);			mB.setSpeed(vwl+sub);
		b= mB.getTachoCount();		c= mC.getTachoCount();
		tacho= (int) ((b+c)/2.0);
	}	
	mC.stop(true);					mB.stop(true);
	}
	else if (a1<0.16&&a2<0.16){/////////////// две стенки сразу 
		mB.resetTachoCount();		mC.resetTachoCount();
		mB.stop(true);				mC.stop(true);
		double 	resback =  -(50*360)/(Math.PI*62.4);
 		while(mB.getTachoCount() > resback && mC.getTachoCount() > resback){
 			mB.backward(); 	 
 			mC.backward();
 			mC.setSpeed(100);
 			mB.setSpeed(100+sub);
 		}
 		mB.resetTachoCount();
		mC.resetTachoCount();
		mB.stop(true);
		mC.stop(true);
		while(tacho<res2){
			US1.fetchSample(Array1,0);			  
			a1= Array1[0];
			Error = target-a1;
			v1= (v-kp*Error)-(kd*derr)-(ki*integ);
			v2= (v+kp*Error)+(kd*derr)+(ki*integ);
			derr= Error-lasterror;
			integ=0.5*integ+Error;
			Vc=(int) Math.round(v1);	Vb=(int) Math.round(v2);
			mC.forward();				mB.forward();
			mC.setSpeed(Vc);			mB.setSpeed(Vb+subPID);
			c=mC.getTachoCount();		b=mB.getTachoCount();
			tacho = (c+b)/2.0;	
			lasterror=Error;
			}
	mC.stop(true);					mB.stop(true);
	mB.resetTachoCount();
	mC.resetTachoCount();
	while(tacho<res3){
		mB.forward(); 	    		mC.forward();
		mC.setSpeed(vwl);			mB.setSpeed(vwl+sub);
		c=mC.getTachoCount();		b=mB.getTachoCount();
		tacho= (int) ((b+c)/2.0);
	}	
	mC.stop(true);					mB.stop(true);
	}
}
	public static void sbros (RegulatedMotor mA){//Сброс Кубика 
		Command.cubesHave = 0;

		mA.rotate(-700);
		mA.stop();
		
		
	}
	public static void cubeUp(EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC, RegulatedMotor mA) {
		double tacho = 0;
		double reskek 	= (70*360)/	(Math.PI*62.4);
		double rescube 	= (140*360)/	(Math.PI*62.4); 
		double rescubeback 	= (70*360)/	(Math.PI*62.4); 
		double b;					 	double c;
		boolean cube = false;
		mB.resetTachoCount();			mC.resetTachoCount();
		while(tacho<reskek){
			mB.backward(); 	    		mC.backward();
			mC.setSpeed(160);			mB.setSpeed(160 + sub);
			b= Math.abs(mB.getTachoCount()); c= Math.abs(mC.getTachoCount());
			tacho= (int) ((b+c)/2.0);
			}
		mB.stop(true);					mC.stop(true);
		
			Command.cubesHave++;
			mA.rotate(-700);
			mA.stop(true);
			tacho=0;
			mB.resetTachoCount();		mC.resetTachoCount();
			mB.stop(true);				mC.stop(true);
			while(tacho<rescube){
				mB.forward(); 	    	mC.forward();
				mC.setSpeed(160);		mB.setSpeed(160 + sub);
				b= mB.getTachoCount(); 	c=mC.getTachoCount();
				tacho= (int) ((b+c)/2.0);}
			mB.stop(true);				mC.stop(true);
			mA.rotate(700);
			
			tacho=0;
			mB.resetTachoCount();		mC.resetTachoCount();
			mB.stop(true);				mC.stop(true);
			while(tacho<rescubeback){
				mB.backward(); 	    	mC.backward();
				mC.setSpeed(160);		mB.setSpeed(160 + sub);
				b= Math.abs(mB.getTachoCount()); c=Math.abs( mC.getTachoCount());
				tacho= (int) ((b+c)/2.0);}
			mB.stop(true);				mC.stop(true);
	
	}
	public static boolean zalupa1 (EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC, RegulatedMotor mA, EV3ColorSensor EC1){// Сбор кубика 
		double tacho = 0;
		SampleProvider ER1 = EC1.getMode("RGB"); 
		double reskek 	= (70*360)/	(Math.PI*62.4);
		double rescube 	= (120*360)/	(Math.PI*62.4); 
		double rescubeback 	= (70*360)/	(Math.PI*62.4); 
		float[] Array3kek = new float[ER1.sampleSize()];
		double b;					 	double c;
		boolean cube = false;
		mB.resetTachoCount();			mC.resetTachoCount();
		while(tacho<reskek){
			ER1.fetchSample(Array3kek,0);			 
			float a3= Array3kek[0];
			if(a3>0.05){
				cube = true;
			}
			else{
				cube = false;
			}
			mB.backward(); 	    		mC.backward();
			mC.setSpeed(160);			mB.setSpeed(160 + sub);
			b= Math.abs(mB.getTachoCount()); c= Math.abs(mC.getTachoCount());
			tacho= (int) ((b+c)/2.0);
			}
		mB.stop(true);					mC.stop(true);
		if(cube){
			Command.cubesHave++;
			mA.rotate(-700);
			mA.stop(true);
			tacho=0;
			mB.resetTachoCount();		mC.resetTachoCount();
			mB.stop(true);				mC.stop(true);
			while(tacho<rescube){
				mB.forward(); 	    	mC.forward();
				mC.setSpeed(160);		mB.setSpeed(160 + sub);
				b= mB.getTachoCount(); 	c=mC.getTachoCount();
				tacho= (int) ((b+c)/2.0);}
			mB.stop(true);				mC.stop(true);
			mA.rotate(700);
			
			tacho=0;
			mB.resetTachoCount();		mC.resetTachoCount();
			mB.stop(true);				mC.stop(true);
			while(tacho<rescubeback){
				mB.backward(); 	    	mC.backward();
				mC.setSpeed(160);		mB.setSpeed(160 + sub);
				b= Math.abs(mB.getTachoCount()); c=Math.abs( mC.getTachoCount());
				tacho= (int) ((b+c)/2.0);}
			mB.stop(true);				mC.stop(true);
		}
		if (!cube){
			tacho=0;
			mB.resetTachoCount();
			mC.resetTachoCount();
			mB.stop(true);
			mC.stop(true);
			while(tacho<reskek){
				mB.forward(); 	    	mC.forward();
				mC.setSpeed(160);		mB.setSpeed(160 + sub);
				b= mB.getTachoCount(); c=mC.getTachoCount();
				tacho= (int) ((b+c)/2.0);}
			mB.stop(true);
			mC.stop(true);
		}
		return cube;
		}



public static int[] hooker(char side, EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC, RegulatedMotor mA, EV3ColorSensor EC1, EV3UltrasonicSensor ES1, EV3UltrasonicSensor ES2){
	 SampleProvider US1 = ES1.getMode("Distance");
	 SampleProvider US2 = ES2.getMode("Distance");
	 float[] Array1 = new float[US1.sampleSize()];
	 float[] Array2 = new float[US2.sampleSize()];
	
	//cmd - dir in where we want go
	US1.fetchSample(Array1,0);			 
	float a1= Array1[0];
	US2.fetchSample(Array2,0);	
	float a2= Array2[0];
	int[] reskekult = new int[4];
	double dist = 0.2;
	
	int l1, r1;
	if (a2 < dist){
		l1 = 1;
	}
	else{
		l1 = 0;
	}
	if (a1 < dist){
		r1 = 1;}
	else {
		r1 = 0;
	}
//	System.out.println(" l:" + l1 + " r:" + r1);
//	Delay.msDelay(15000);
	if (side == 'l') {
		rotatel(mB, mC);
		reskekult[1] = l1;
		reskekult[3] = r1;
	}
	else if (side == 'r') {
		rotater(mB, mC);
		reskekult[1] = r1;
		reskekult[3] = l1;;
	}
	else {
		System.out.println("Error in class:kek, func: hooker");
	}
	
	US1.fetchSample(Array1,0);			 
	float a3= Array1[0];
	US2.fetchSample(Array2,0);	
	float a4= Array2[0];
	
	if (a4 < dist){
		reskekult[0] = 1;
	}
	else{
		reskekult[0] = 0;
	}
	if (a3 < dist){
		reskekult[2] = 1;
	}
	else{
		reskekult[2] = 0;
	}
	
//	mB.close();
//	mC.close();
//	ES1.close();
//	ES2.close();
	return reskekult;
}
	public static data getInfo(char side, EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC, RegulatedMotor mA, EV3ColorSensor EC1, EV3UltrasonicSensor ES1, EV3UltrasonicSensor ES2){
		//side - direction of rotate ("a" or "d"), dir - current dir
	
		boolean cube = zalupa1(mB, mC, mA, EC1);
		int[] walls = hooker(side, mB, mC, mA, EC1, ES1, ES2);
		data dat = new data(cube, walls);
		return dat;
	}
	public static void goBack(EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC) {
		mB.resetTachoCount();	
		mC.resetTachoCount();
		double 	res =  -(300*360)/(Math.PI*62.4);
 		while(mB.getTachoCount() > res && mC.getTachoCount() > res){
 			mB.backward(); 	 
 			mC.backward();
 			mC.setSpeed(speedC);
 			mB.setSpeed(speedB);
 		}
//	while(tacho<res){
//		mB.forward(); 	 
//		mC.forward();
//		mC.setSpeed(200);
//		mB.setSpeed(203);
//		b= mB.getTachoCount(); 
//		c= mC.getTachoCount();
//		tacho= (int) ((b+c)/2.0);
//	}
	mC.stop(true);
	mB.stop(true);
	}
	public static void getCube(EV3LargeRegulatedMotor mB, EV3LargeRegulatedMotor mC, RegulatedMotor mA) {
		
		double reskek 	= (50*360)/	(Math.PI*62.4);
		double rescube 	= (100*360)/	(Math.PI*62.4); 
		double rescubeback 	= (50*360)/	(Math.PI*62.4); 
	
		
		
			Command.cubesHave++;
			mA.resetTachoCount();

			mB.stop(true);
			mC.stop(true);
			mA.stop(true);
			mA.rotate(-700);
			mA.stop(true);			
			mB.resetTachoCount();	
			mC.resetTachoCount();
			while(mB.getTachoCount() < reskek && mC.getTachoCount() < reskek){
				mB.forward(); 	    
				mC.forward();
				mC.setSpeed(160);	
				mB.setSpeed(160 + sub);
			}
			mB.stop(true);	
			mC.stop(true);			
			
			
			mA.resetTachoCount();	
			mA.rotate(700);
			mA.stop(true);			
			mB.resetTachoCount();	
			mC.resetTachoCount();
			while(mB.getTachoCount() > -reskek && mC.getTachoCount() > -reskek){
				mB.backward(); 	  
				mC.backward();
				mC.setSpeed(160);
				mB.setSpeed(160);
			}
			mB.stop(true);		
			mC.stop(true);
	}
	
}

