package com.jw.quadcopter.raspberrypi.sensors;

import java.io.InputStream;
import java.io.OutputStream;

public class SensorManager
{
	private Accelerometer accelerometer;
	private Gyroscope gyroscope;
	private Magnetometer magnetometer;
	private Barometer barometer;
	private Thermometer thermometer;

	public SensorManager(Accelerometer accelerometer, Gyroscope gyroscope, Magnetometer magnetometer,
			Barometer barometer, Thermometer thermometer)
	{
		this.accelerometer = accelerometer;
		this.gyroscope = gyroscope;
		this.magnetometer = magnetometer;
		this.barometer = barometer;
		this.thermometer = thermometer;
	}

	public void initSensors(OutputStream outputStream, Accelerometer.Range accelerometerRange)
	{
		accelerometer.init(outputStream, accelerometerRange);
	}
	
	public void updateValues(InputStream inputStream)
	{
		accelerometer.updateValues(inputStream);
		gyroscope.updateValues(inputStream);
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
}
/*
 * accelerometer gyroscope magnetometer barometer thermometer //
 */