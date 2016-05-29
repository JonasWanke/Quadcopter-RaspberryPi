package com.jw.quadcopter.raspberrypi.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.jw.quadcopter.raspberrypi.communication.ArduinoCommunicationManager;
import com.jw.quadcopter.raspberrypi.sensors.Accelerometer;
import com.jw.quadcopter.raspberrypi.sensors.Barometer;
import com.jw.quadcopter.raspberrypi.sensors.Gyroscope;
import com.jw.quadcopter.raspberrypi.sensors.Magnetometer;
import com.jw.quadcopter.raspberrypi.sensors.SensorManager;
import com.jw.quadcopter.raspberrypi.sensors.Thermometer;
import com.jw.quadcopter.raspberrypi.util.OperatingSystem;

public class Quadcopter
{
	private static Quadcopter quadcopter;

	public final static Logger LOGGER = Logger
			.getLogger(Quadcopter.class.getPackage().getName());

	private SensorManager sensorManager;
	private ArduinoCommunicationManager arduinoCommunicationManager;

	static
	{
		LOGGER.addHandler(new ConsoleHandler());
		try
		{
			FileHandler fileHandler;
			switch (OperatingSystem.getOperatingSystem())
			{
				case WINDOWS_10:
					fileHandler = new FileHandler(
							"%UserProfile%\\Quadcopter\\Logs\\"
									+ new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss.SSS")
													.format(new Date()));
					break;
				case LINUX:
				default:
					fileHandler = new FileHandler("/var/log/Quadcopter/"
							+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
									.format(new Date()));
					break;
			}
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(new FileHandler());
		}
		catch (SecurityException e)
		{
			Quadcopter.getQuadcopter().registerSeriousError(
					Quadcopter.class.getClass().getName(), e);
		}
		catch (IOException e)
		{
			Quadcopter.getQuadcopter().registerSeriousError(
					Quadcopter.class.getClass().getName(), e);
		}
	}

	private Quadcopter(Accelerometer accelerometer, Gyroscope gyroscope,
			Magnetometer magnetometer, Barometer barometer,
			Thermometer thermometer)
	{
		sensorManager = new SensorManager(accelerometer, gyroscope,
				magnetometer, barometer, thermometer);
	}
	public void createQuadcopter(Accelerometer accelerometer,
			Gyroscope gyroscope, Magnetometer magnetometer, Barometer barometer,
			Thermometer thermometer)
	{
		if (quadcopter != null)
			throw new IllegalStateException(
					"Attempted to create a second instance of com.jw.quadcopter.raspberrypi.main.Quadcopter");
		quadcopter = new Quadcopter(accelerometer, gyroscope, magnetometer,
				barometer, thermometer);
		arduinoCommunicationManager = new ArduinoCommunicationManager();
	}
	public static Quadcopter getQuadcopter()
	{
		return quadcopter;
	}

	public void init()
	{
		arduinoCommunicationManager.init();
		sensorManager.initSensors(arduinoCommunicationManager,
				Accelerometer.Range.RANGE_4G, Gyroscope.Range.RANGE_250DPS);
	}

	public void registerSeriousError(String className, String exception)
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!        Serious exception occured:        !");
		System.out.println(className + ":");
		System.out.println(exception);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	public void registerSeriousError(String className, Exception exception)
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!        Serious exception occured:        !");
		System.out.println(className + ":");
		System.out.println(exception);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	public void registerSeriousError(String className, String message,
			Exception exception)
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
