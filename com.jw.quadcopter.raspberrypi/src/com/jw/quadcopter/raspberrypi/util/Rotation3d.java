package com.jw.quadcopter.raspberrypi.util;

/**
 * {@code Rotation3d} is a class for storing the rotations of the quadcopter
 * around every single axis. <br />
 * <br />
 * Unit: Radians
 * 
 * @author Jonas Wanke
 *
 */
public class Rotation3d
{
	/**
	 * Contains the rotation around the y-axis. <br />
	 * Unit: Radians <br />
	 * Positive: Nose down, tail up -> flying forward <br />
	 * Negative: Nose up, tail down -> flying backward
	 */
	private double pitch;
	/**
	 * Contains the rotation around the x-axis. <br />
	 * Unit: Radians <br />
	 * Positive: Right side down, left side up -> flying right <br />
	 * Negative: Right side up, left side down -> flying left
	 */
	private double roll;
	/**
	 * Contains the rotation around the z-axis. <br />
	 * Unit: Radians <br />
	 * Positive: Heading east <br />
	 * Negative: Heading west
	 */
	private double yaw;

	/**
	 * Creates a new instance of {@code Rotation3d} with all angles set to
	 * {@code 0}.
	 */
	public Rotation3d()
	{
		pitch = 0;
		roll = 0;
		yaw = 0;
	}
	/**
	 * Creates a new instance of {@code Rotation3d} with the given angles.
	 * 
	 * @param pitch
	 *            Pitch in radians
	 * @param roll
	 *            Roll in radians
	 * @param yaw
	 *            Yaw in radians
	 */
	public Rotation3d(double pitch, double roll, double yaw)
	{
		this.pitch = pitch;
		this.roll = roll;
		this.yaw = yaw;
	}

	/**
	 * Gets the {@link #pitch}.
	 * 
	 * @return The current {@link #pitch}.
	 */
	public double getPitch()
	{
		return pitch;
	}
	/**
	 * Gets the {@link #roll}.
	 * 
	 * @return The current {@link #roll}.
	 */
	public double getRoll()
	{
		return roll;
	}
	/**
	 * Gets the {@link #yaw}.
	 * 
	 * @return The current {@link #yaw}.
	 */
	public double getYaw()
	{
		return yaw;
	}

	/**
	 * Sets the {@link #pitch}.
	 * 
	 * @param pitch
	 *            The new pitch
	 */
	public void setPitch(double pitch)
	{
		this.pitch = pitch;
	}
	/**
	 * Sets the {@link #roll}.
	 * 
	 * @param roll
	 *            The new roll
	 */
	public void setRoll(double roll)
	{
		this.roll = roll;
	}
	/**
	 * Sets the {@link #yaw}.
	 * 
	 * @param yaw
	 *            The new yaw
	 */
	public void setYaw(double yaw)
	{
		this.yaw = yaw;
	}

}
