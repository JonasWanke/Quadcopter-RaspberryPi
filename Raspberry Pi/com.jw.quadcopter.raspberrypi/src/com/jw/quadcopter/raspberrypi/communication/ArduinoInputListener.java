package com.jw.quadcopter.raspberrypi.communication;

import java.io.IOException;
import java.io.InputStream;

import com.jw.quadcopter.raspberrypi.main.Quadcopter;

public final class ArduinoInputListener extends Thread
{
	private InputStream inputStream;
	private ArduinoCommunicationManager arduinoCommunicationManager;
	
	public ArduinoInputListener(InputStream inputStream, ArduinoCommunicationManager arduinoCommunicationManager)
	{
		this.inputStream = inputStream;
		this.arduinoCommunicationManager = arduinoCommunicationManager;
	}
	
	@Override
	public void run()
	{
		byte currentInput;
		while(true)
		{
			try
			{
				while((currentInput = (byte) inputStream.read()) > -1)
				{
					arduinoCommunicationManager.addInputByte(currentInput);
				}
			}
			catch (IOException e)
			{
				Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
			}
		}
	}
}
