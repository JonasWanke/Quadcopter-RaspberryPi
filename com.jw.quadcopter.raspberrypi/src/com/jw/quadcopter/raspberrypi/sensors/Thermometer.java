package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;

public abstract class Thermometer
{
	protected double temperature;

	protected CommunicationManager communicationManager;

	public Thermometer()
	{
	}

	public void init(CommunicationManager communicationManager)
	{
		this.communicationManager = communicationManager;
	}
	public abstract void updateValues();

	public double getTemperature()
	{
		return temperature;
	}
}
