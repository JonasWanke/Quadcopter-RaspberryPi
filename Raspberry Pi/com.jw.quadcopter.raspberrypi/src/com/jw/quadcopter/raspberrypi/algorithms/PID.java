package com.jw.quadcopter.raspberrypi.algorithms;

public class PID {
	private double kp;
	private double ki;
	private double kd;

	private double lastInput = 0;
	private double errorSum = 0;

	public PID(double kp) {
		this.kp = kp;
		this.ki = 0;
		this.kd = 0;
	}

	public PID(double kp, double ki) {
		this.kp = kp;
		this.ki = ki;
		this.kd = 0;
	}

	public PID(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}

	public double getOutput(double setPoint, double input, int timeChange) {
		double error = setPoint - input;
		errorSum += error * timeChange;
		
		lastInput = input;
		return kp * error + ki * errorSum - kd * (input - lastInput) / timeChange;
	}

	public double getKp() {
		return kp;
	}

	public double getKi() {
		return ki;
	}

	public double getKd() {
		return kd;
	}


	public void setKp(double kp) {
		this.kp = kp;
	}

	public void setKi(double ki) {
		errorSum *= this.ki / ki;
		this.ki = ki;
	}

	public void setKd(double kd) {
		this.kd = kd;
	}
}
