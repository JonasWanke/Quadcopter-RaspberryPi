package com.jw.quadcopter.raspberrypi.communication;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Queue;

public abstract class CommunicationManager
{
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
