package com.jw.quadcopter.raspberrypi.communication;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Queue;

public abstract class CommunicationManager
{
	public static final byte CTRL_SENSOR_INIT_ACCELEROMETER = 0b00001000;
	public static final byte CTRL_SENSOR_INIT_GYROSCOPE = 0b00001001;
	public static final byte CTRL_SENSOR_INIT_MAGNETOMETER = 0b00001010;
	public static final byte CTRL_SENSOR_INIT_BAROMETER = 0b00001011;
	
	protected InputStream inputStream;
	protected OutputStream outputStream;

	protected Queue<Byte> inputBuffer;
	protected Queue<Integer> inputBufferIDs;

	public CommunicationManager()
	{
	}

	public abstract void init();
	public abstract void close();

	public abstract void send(byte b);
	public abstract void send(byte[] b);
	public abstract byte[] sendForResult(byte b, int resultLength);
	public abstract byte[] sendForResult(byte[] b, int resultLength);

	public void addInputByte(byte b)
	{
		inputBuffer.add(b);
	}
	public void addInputBufferID(int id)
	{
		inputBufferIDs.add(id);
	}
}
