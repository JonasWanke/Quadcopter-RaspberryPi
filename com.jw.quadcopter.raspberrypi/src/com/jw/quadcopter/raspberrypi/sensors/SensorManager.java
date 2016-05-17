package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;
import com.jw.quadcopter.raspberrypi.util.Rotation3d;

public class SensorManager
{
	private Accelerometer accelerometer;
	private Gyroscope gyroscope;
	private Magnetometer magnetometer;
	private Barometer barometer;
	private Thermometer thermometer;

	protected Rotation3d rotation;

	private CommunicationManager communicationManager;

	public SensorManager(Accelerometer accelerometer, Gyroscope gyroscope, Magnetometer magnetometer,
			Barometer barometer, Thermometer thermometer)
	{
		this.accelerometer = accelerometer;
		this.gyroscope = gyroscope;
		this.magnetometer = magnetometer;
		this.barometer = barometer;
		this.thermometer = thermometer;
	}

	public void initSensors(CommunicationManager communicationManager, Accelerometer.Range accelerometerRange,
			Gyroscope.Range gyroscopeRange)
	{
		this.communicationManager = communicationManager;

		accelerometer.init(communicationManager, accelerometerRange);
		gyroscope.init(communicationManager, gyroscopeRange);
	}

	public void updateValues()
	{
		accelerometer.updateValues();
		gyroscope.updateValues();
		magnetometer.updateValues();
		barometer.updateValues();
		thermometer.updateValues();
	}

	public Accelerometer getAccelerometer()
	{
		return accelerometer;
	}
	public Gyroscope getGyroscope()
	{
		return gyroscope;
	}
	public Magnetometer getMagnetometer()
	{
		return magnetometer;
	}
	public Barometer getBarometer()
	{
		return barometer;
	}
	public Thermometer getThermometer()
	{
		return thermometer;
	}

	public Rotation3d getRotation()
	{
		return rotation;
	}
}
/*
 * accelerometer gyroscope magnetometer barometer thermometer //
 */