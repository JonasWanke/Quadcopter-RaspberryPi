package com.jw.quadcopter.raspberrypi.algorithms;

public class Kalman
{
	private double Q_angle;
	private double Q_bias;
	private double R_measure;

	private double angle;
	private double bias;
	private double rate;

	private double P[][] = new double[2][2];

	public Kalman()
	{
		this(0.001f, 0.003f, 0.03f);
	}
	public Kalman(double q_angle, double q_bias, double r_measure)
	{
		Q_angle = q_angle;
		Q_bias = q_bias;
		R_measure = r_measure;

		angle = 0f;
		bias = 0f;

		P[0][0] = 0;
		P[0][1] = 0;
		P[1][0] = 0;
		P[1][1] = 0;
	}

	/**
	 * 
	 * @param newAngle
	 * @param newRate
	 * @param time
	 *            The time from the last call in seconds.
	 * @return
	 */
	public double getAngle(double newAngle, double newRate, double time)
	{
		rate = newRate - bias;
		angle += time * rate;

		P[0][0] += time * (time * P[1][1] - P[0][1] - P[1][0] + Q_angle);
		P[0][1] -= time * P[1][1];
		P[1][0] -= time * P[1][1];
		P[1][1] += time * Q_bias;

		double s = P[0][0] + R_measure;

		double K[] = new double[2];
		K[0] = P[0][0] / s;
		K[1] = P[1][0] / s;

		double deltaAngle = newAngle - angle;

		angle += K[0] * deltaAngle;
		bias += K[1] * deltaAngle;

		double P00 = P[0][0];
		double P01 = P[0][1];
		P[0][0] -= K[0] * P00;
		P[0][1] -= K[0] * P01;
		P[1][0] -= K[1] * P00;
		P[1][1] -= K[1] * P01;

		return angle;
	}

	public double getRate()
	{
		return rate;
	}
	public double getQangle()
	{
		return Q_angle;
	}
	public double getQbias()
	{
		return Q_bias;
	}
	public double getRmeasure()
	{
		return R_measure;
	}

	public void setAngle(double angle)
	{
		this.angle = angle;
	}
	public void setQangle(double q_angle)
	{
		Q_angle = q_angle;
	}
	public void setQbias(double q_bias)
	{
		Q_bias = q_bias;
	}
	public void setRmeasure(double r_measure)
	{
		R_measure = r_measure;
	}
}
