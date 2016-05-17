package com.jw.quadcopter.raspberrypi.util;

public class Rotation3d
{
	/**
	 * Contains the rotation around the y-axis. <br />
	 * Positive: Nose down, tail up -> flying forward <br />
	 * Negative: Nose up, tail down -> flying backward
	 */
	private double pitch;
	/**
	 * Contains the rotation around the x-axis. <br />
	 * Positive: Right side down, left side up -> flying right <br />
	 * Negative: Right side up, left side down -> flying left
	 */
	private double roll;
	/**
	 * Contains the rotation around the z-axis. <br />
	 * Positive: Heading east <br />
	 * Negative: Heading west
	 */
	private double yaw;

	public Rotation3d()
	{
		pitch = 0;
		roll = 0;
		yaw = 0;
	}
	public Rotation3d(double pitch, double roll, double yaw)
	{
		this.pitch = pitch;
		this.roll = roll;
		this.yaw = yaw;
	}

	public double getPitch()
	{
		return pitch;
	}
	public double getRoll()
	{
		return roll;
	}
	public double getYaw()
	{
		return yaw;
	}

	public void setPitch(double pitch)
	{
		this.pitch = pitch;
	}
	public void setRoll(double roll)
	{
		this.roll = roll;
	}
	public void setYaw(double yaw)
	{
		this.yaw = yaw;
	}

}
