package com.jw.quadcopter.raspberrypi.communication;

import java.io.IOException;
import java.io.InputStream;

import com.jw.quadcopter.raspberrypi.main.Quadcopter;

public final class ArduinoInputListener extends Thread
{
	private InputStream inputStream;
	private ArduinoCommunicationManager arduinoCommunicationManager;

	private volatile boolean end = false;

	public ArduinoInputListener(InputStream inputStream, ArduinoCommunicationManager arduinoCommunicationManager)
	{
		this.inputStream = inputStream;
		this.arduinoCommunicationManager = arduinoCommunicationManager;
	}

	public void end()
	{
		end = true;
	}

	@Override
	public synchronized void run()
	{
		byte currentInput;
		while (!end)
			try
			{
				while ((currentInput = (byte) inputStream.read()) > -1)
				{
					arduinoCommunicationManager.addInputByte(currentInput);
				}
				System.out.println(this.getClass().getName() + ": InputStream returned -1");
			}
			catch (IOException e)
			{
				Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
			}
	}
}
