package com.jw.quadcopter.raspberrypi.communication;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;

import com.jw.quadcopter.raspberrypi.main.Quadcopter;

public class ArduinoOutputManager extends Thread
{
	private OutputStream outputStream;
	private ArduinoCommunicationManager arduinoCommunicationManager;
	private Queue<byte[]> outputQueue;

	private int currentID;

	private volatile boolean end = false;

	public ArduinoOutputManager(OutputStream outputStream, ArduinoCommunicationManager arduinoCommunicationManager)
	{
		this.outputStream = outputStream;
		this.arduinoCommunicationManager = arduinoCommunicationManager;
		outputQueue = new LinkedList<>();
	}

	public void end()
	{
		end = true;
	}

	@Override
	public synchronized void run()
	{
		while (!end)
			if (outputQueue.size() > 0)
				try
				{
					outputStream.write(outputQueue.remove());
				}
				catch (IOException e)
				{
					Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
				}
			else
				try
				{
					// Sleep 10µs to decrease CPU usage
					Thread.sleep(0, 10000);
				}
				catch (InterruptedException e)
				{
					Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
				}
	}

	public int send(byte b, int resultLength)
	{
		return send(new byte[] { b }, resultLength);
	}
	public synchronized int send(byte[] b, int resultLength)
	{
		if (resultLength != 0)
		{
			// arduinoCommunicationManager.addInputBufferLength(resultLength);
			arduinoCommunicationManager.addInputBufferID(currentID);
		}
		outputQueue.add(b);
		return currentID++;
	}
}
