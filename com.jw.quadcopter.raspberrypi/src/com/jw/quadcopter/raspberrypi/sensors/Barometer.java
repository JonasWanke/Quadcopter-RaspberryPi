package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;

public abstract class Barometer
{
	protected double height;

	protected CommunicationManager communicationManager;

	public Barometer()
	{
	}

	public void init(CommunicationManager communicationManager)
	{
		this.communicationManager = communicationManager;
	}
	public abstract void updateValues();

	public double getHeight()
	{
		return height;
	}
}
