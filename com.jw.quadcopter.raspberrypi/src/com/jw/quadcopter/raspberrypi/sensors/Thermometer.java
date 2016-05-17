package com.jw.quadcopter.raspberrypi.sensors;

public abstract class Thermometer
{
	private double temperature;

	public Thermometer()
	{
	}

	public abstract void updateValues();

	public double getTemperature()
	{
		return temperature;
	}
}
