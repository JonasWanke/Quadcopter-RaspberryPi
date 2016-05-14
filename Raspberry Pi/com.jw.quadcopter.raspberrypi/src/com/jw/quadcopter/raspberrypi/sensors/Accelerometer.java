package com.jw.quadcopter.raspberrypi.sensors;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Accelerometer
{
	public double x;
	public double y;
	public double z;
	
	private Range range;

	public Accelerometer()
	{
	}
	
	public abstract void init(OutputStream outputStream, Range range);
	public abstract void updateValues(InputStream inputStream);

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
		
		public double getGPerLSB()
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
