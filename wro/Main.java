//import lejos.hardware.BrickFinder;
//import lejos.hardware.motor.EV3LargeRegulatedMotor;
//import lejos.robotics.RegulatedMotor;
//import lejos.utility.Delay;
//import lejos.hardware.BrickFinder;
//import lejos.hardware.motor.EV3LargeRegulatedMotor;
//import lejos.hardware.port.SensorPort;
//import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.hardware.sensor.EV3UltrasonicSensor;
//import lejos.robotics.RegulatedMotor;
//import lejos.robotics.SampleProvider;
import java.awt.Button;

import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import lejos.hardware.port.*;
import lejos.hardware.motor.*;
import lejos.hardware.*;

public class Main {


	 static EV3LargeRegulatedMotor mB = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("B"));
	 static EV3LargeRegulatedMotor mC = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("C"));
	 static EV3UltrasonicSensor ES1 = new EV3UltrasonicSensor(SensorPort.S2);//LEft;
	 static EV3UltrasonicSensor ES2 = new EV3UltrasonicSensor(SensorPort.S4);//Right;
	 static RegulatedMotor mA = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("A"));
	 static EV3ColorSensor EC1= new EV3ColorSensor(SensorPort.S1);
	 static Command n = new Command();
	

	 public static void forward() {
		 mB.resetTachoCount();
		 mC.resetTachoCount();
		 Command.chgCoords(n, 'f');
		 Drive.tank(mB, mC, ES1, ES2); 
	 }
	 public static void left90() {
		 mB.resetTachoCount();
		 mC.resetTachoCount();
		 Command.CngDir(n, 'l');
		 Drive.rotatel(mB, mC);
	 }
	 public static void right90() {
		 mB.resetTachoCount();
		 mC.resetTachoCount();
		 Command.CngDir(n, 'r');
		 Drive.rotater(mB, mC);
	 }
	 public static void turn180() {
		 mB.resetTachoCount();
		 mC.resetTachoCount();
		 Command.CngDir(n, 'b');
		 Drive.rotate180(mB, mC);
	 }
	 public static data getInfo(char side, char dir) {
		 mB.resetTachoCount();
		 mC.resetTachoCount();
		 Command.CngDir(n, side);
		 return Drive.getInfo(side, mB, mC, mA, EC1, ES1, ES2);
	 }
	 public static data getWalls(char side) {
		 mB.resetTachoCount();
		 mC.resetTachoCount();
		 Command.CngDir(n, side);
		 data d = new data(Drive.hooker(side, mB, mC, mA, EC1, ES1, ES2));
		 return d;
	 }
	 public static void getCube() {
		 /// smth connected with Rusel code 
//			mA.resetTachoCount();
			mB.resetTachoCount();	
			mC.resetTachoCount();
			Drive.cubeUp(mB, mC, mA);
			//Drive.getCube(mB, mC, mA);
	 }
	 public static void throwCubes(){
//		mA.resetTachoCount();
		Drive.sbros(mA);
	 }
	 public static void back() {
		 mB.resetTachoCount();
		 mC.resetTachoCount();
		 Command.chgCoords(n, 'b');
		 Drive.goBack(mB, mC);
	 }
	 public static void timeBreak(int t) {
		 Delay.msDelay(t);
	 }
	 public static void WaitForPress() {
		 lejos.hardware.Button.waitForAnyPress();
	 }
	 public static void PowerOff(){
		 	mA.close();
			mB.close();
			mC.close();
			ES1.close();
			ES2.close();
			EC1.close();
	 }
//	public static void main(String[] args) {
//		 
////
////		 EV3LargeRegulatedMotor mB = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("B"));
////		 EV3LargeRegulatedMotor mC = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("C"));
////		 EV3UltrasonicSensor ES1 = new EV3UltrasonicSensor(SensorPort.S2);//LEft;
////		 EV3UltrasonicSensor ES2 = new EV3UltrasonicSensor(SensorPort.S4);//Right;
//		 
////		Drive.rotate180(mB, mC);
////		Drive.tank(mB, mC, ES1, ES2);
////		Drive.tank(mB, mC, ES1, ES2);
////		Drive.rotatel(mB, mC);
////		Drive.tank(mB, mC, ES1, ES2);
////		Drive.rotater(mB, mC);
////		Drive.rotate180(mB, mC);
////			Driv2.rotate180(mB, mC);
////			Driv2.tank(mB, mC, ES1, ES2);
////			Driv2.tank(mB, mC, ES1, ES2);
////			Driv2.rotatel(mB, mC);
////			Driv2.tank(mB, mC, ES1, ES2);
////			Driv2.rotater(mB, mC);
////			Driv2.rotate180(mB, mC);
//		 Driv2.rotatel(mB, mC);
//		 Driv2.rotatel(mB, mC);
//		 Driv2.rotatel(mB, mC);
//		 Driv2.rotatel(mB, mC);
//		 Driv2.rotate180(mB, mC);
//		 Driv2.rotate180(mB, mC);
//		 Driv2.tank(mB, mC, ES1, ES2);
//		 Driv2.tank(mB, mC, ES1, ES2);
//		 Driv2.rotatel(mB, mC);
//		 Driv2.tank(mB, mC, ES1, ES2);
//		 Driv2.rotatel(mB, mC);
//		 Driv2.tank(mB, mC, ES1, ES2);
//		 Driv2.tank(mB, mC, ES1, ES2);
//		 Driv2.rotater(mB, mC);
//		 Driv2.rotater(mB, mC);
//		 Driv2.rotater(mB, mC);
//		 Driv2.tank(mB, mC, ES1, ES2);
//		 
//			
//		 
//		mB.close();
//		mC.close();
//		ES1.close();
//		ES2.close();
//		
//		
//	}
}


