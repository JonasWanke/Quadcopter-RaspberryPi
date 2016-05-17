package com.jw.quadcopter.raspberrypi.main;

import com.jw.quadcopter.raspberrypi.communication.ArduinoCommunicationManager;
import com.jw.quadcopter.raspberrypi.sensors.Accelerometer;
import com.jw.quadcopter.raspberrypi.sensors.Barometer;
import com.jw.quadcopter.raspberrypi.sensors.Gyroscope;
import com.jw.quadcopter.raspberrypi.sensors.Magnetometer;
import com.jw.quadcopter.raspberrypi.sensors.SensorManager;
import com.jw.quadcopter.raspberrypi.sensors.Thermometer;

public class Quadcopter
{
	private static Quadcopter quadcopter;

	private SensorManager sensorManager;
	private ArduinoCommunicationManager arduinoCommunicationManager;

	private Quadcopter(Accelerometer accelerometer, Gyroscope gyroscope, Magnetometer magnetometer, Barometer barometer,
			Thermometer thermometer)
	{
		sensorManager = new SensorManager(accelerometer, gyroscope, magnetometer, barometer, thermometer);
	}
	public void createQuadcopter(Accelerometer accelerometer, Gyroscope gyroscope, Magnetometer magnetometer,
			Barometer barometer, Thermometer thermometer)
	{
		if (quadcopter != null)
			throw new IllegalStateException(
					"Attempted to create a second instance of com.jw.quadcopter.raspberrypi.main.Quadcopter");
		quadcopter = new Quadcopter(accelerometer, gyroscope, magnetometer, barometer, thermometer);
		arduinoCommunicationManager = new ArduinoCommunicationManager();
	}
	public static Quadcopter getQuadcopter()
	{
		return quadcopter;
	}

	public void init()
	{
		arduinoCommunicationManager.init();
		sensorManager.initSensors(arduinoCommunicationManager, Accelerometer.Range.RANGE_4G,
				Gyroscope.Range.RANGE_250DPS);
	}

	public void RegisterSeriousError(String className, String exception)
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!        Serious exception occured:        !");
		System.out.println(className + ":");
		System.out.println(exception);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	public void RegisterSeriousError(String className, Exception exception)
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!        Serious exception occured:        !");
		System.out.println(className + ":");
		System.out.println(exception);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	public void RegisterSeriousError(String className, String message, Exception exception)
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!        Serious exception occured:        !");
		System.out.println(className + ":");
		System.out.println(message);
		System.out.println(exception);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	public SensorManager getSensorManager()
	{
		return sensorManager;
	}
	public ArduinoCommunicationManager getArduinoCommunicationManager()
	{
		return arduinoCommunicationManager;
	}
}
