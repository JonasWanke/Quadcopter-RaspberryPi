package com.jw.quadcopter.raspberrypi.sensors;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Gyroscope
{
	private double x;
	private double y;
	private double z;
	
	private Range range;

	public Gyroscope()
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
		
		public double getDPSPerLSB()
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
