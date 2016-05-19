package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;
import com.jw.quadcopter.raspberrypi.util.Rotation3d;

public abstract class Accelerometer
{
	protected Rotation3d rotation;

	protected CommunicationManager communicationManager;
	protected Range range;

	public Accelerometer()
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
		RANGE_2G(2), RANGE_4G(4), RANGE_8G(8), RANGE_16G(16);

		private final int range;

		Range(int range)
		{
			this.range = range;
		}

		public int getRange()
		{
			return range;
		}

		public double getGPerDigit()
		{
			switch (range)
			{
				case 2:
					return 0.0039;
				case 4:
					return 0.0078;
				case 8:
					return 0.0156;
				case 16:
				default:
					return 0.0312;
			}
		}
		public byte getRangeBits()
		{
			switch (range)
			{
				case 2:
					return (byte) 0b00000000;
				case 4:
					return (byte) 0b00000001;
				case 8:
					return (byte) 0b00000010;
				case 16:
				default:
					return (byte) 0b00000011;
			}
		}
	}
}
