package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;
import com.jw.quadcopter.raspberrypi.util.Rotation3d;

public abstract class Magnetometer
{
	protected Rotation3d rotation;

	protected CommunicationManager communicationManager;

	public Magnetometer()
	{
	}

	public void init(CommunicationManager communicationManager)
	{
		this.communicationManager = communicationManager;
	}
	public abstract void updateValues();

	public Rotation3d getRotation()
	{
		return rotation;
	}
}
