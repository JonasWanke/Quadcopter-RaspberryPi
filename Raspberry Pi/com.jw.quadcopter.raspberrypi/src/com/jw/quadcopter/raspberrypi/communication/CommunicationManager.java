package com.jw.quadcopter.raspberrypi.communication;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public abstract class CommunicationManager
{
	protected InputStream inputStream;
	protected OutputStream outputStream;
	
	protected LinkedList<Byte> inputBuffer;
	
	public CommunicationManager()
	{
	}
	
	public abstract void init();
	public abstract void close();
	
	/*
	public InputStream getInputStream()
	{
		return inputStream;
	}//*/
	public OutputStream getOutputStream()
	{
		return outputStream;
	}//*/

	public abstract void send(int b);
	public abstract void send(byte[] b);
	public abstract byte[] sendForResult(int b, int answerLength);
	public abstract byte[] sendForResult(byte[] b, int answerLength);

	public void addInputByte(byte b)
	{
		inputBuffer.push(b);
	}
}
