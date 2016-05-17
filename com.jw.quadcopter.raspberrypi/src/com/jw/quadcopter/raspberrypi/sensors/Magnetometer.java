package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;

public abstract class Magnetometer
{
	protected double x;
	protected double y;
	protected double z;

	protected CommunicationManager communicationManager;

	public Magnetometer()
	{
	}

	public void init(CommunicationManager communicationManager)
	{
		this.communicationManager = communicationManager;
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
