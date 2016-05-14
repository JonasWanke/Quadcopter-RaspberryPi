package com.jw.quadcopter.raspberrypi.sensors;

public abstract class Magnetometer
{
	private double x;
	private double y;
	private double z;

	public Magnetometer()
	{
	}

	public abstract void updateValues();

	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getZ()
	{
		return z;
	}
}
