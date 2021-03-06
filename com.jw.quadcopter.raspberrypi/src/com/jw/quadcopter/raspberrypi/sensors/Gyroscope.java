package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;
import com.jw.quadcopter.raspberrypi.util.Rotation3d;

public abstract class Gyroscope
{
	/**
	 * {@code rotation} contains the measured angular change per second in
	 * radians.
	 */
	protected Rotation3d rotation;

	protected CommunicationManager communicationManager;
	protected Range range;

	public Gyroscope()
	{
	}

	public void init(CommunicationManager communicationManager, Range range)
	{
		this.communicationManager = communicationManager;
	}
	public abstract void updateValues();

	public Rotation3d getRotation()
	{
		return rotation;
	}

	public Range getRange()
	{
		return range;
	}

	public enum Range
	{
		RANGE_250DPS(250), RANGE_500DPS(500), RANGE_2000DPS(2000);

		private final int range;

		Range(int range)
		{
			this.range = range;
		}

		public int getRange()
		{
			return range;
		}

		public double getDPSPerDigit()
		{
			switch (range)
			{
				case 250:
					return 0.00875;
				case 500:
					return 0.0175;
				case 2000:
				default:
					return 0.07;
			}
		}
		public byte getRangeBits()
		{
			switch (range)
			{
				case 250:
					return (byte) 0b00000000;
				case 500:
					return (byte) 0b00010000;
				case 2000:
				default:
					return (byte) 0b00100000;
			}
		}
	}
}
