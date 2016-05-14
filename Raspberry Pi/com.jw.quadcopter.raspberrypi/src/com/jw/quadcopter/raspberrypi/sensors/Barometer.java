package com.jw.quadcopter.raspberrypi.sensors;

public abstract class Barometer
{
	private double height;

	public Barometer()
	{
	}

	public abstract void updateValues();

	public double getHeight()
	{
		return height;
	}
}
