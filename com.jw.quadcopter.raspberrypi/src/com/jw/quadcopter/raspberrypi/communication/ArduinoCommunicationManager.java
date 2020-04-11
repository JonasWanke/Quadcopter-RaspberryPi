package com.jw.quadcopter.raspberrypi.communication;

import java.io.IOException;
import java.util.LinkedList;

import com.jw.quadcopter.raspberrypi.main.Quadcopter;
import com.jw.quadcopter.raspberrypi.util.OperatingSystem;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class ArduinoCommunicationManager extends CommunicationManager
{
	public static final int BAUD_RATE = 19200;
	public static final int DATABITS = SerialPort.DATABITS_8;
	public static final int STOPBITS = SerialPort.STOPBITS_1;
	public static final int PARITY = SerialPort.PARITY_NONE;
	public static final int PORT_OPEN_TIMEOUT = 2000;

	private SerialPort serialPort;
	private ArduinoInputListener arduinoInputListener;
	private ArduinoOutputManager arduinoOutputManager;

	public ArduinoCommunicationManager()
	{

	}

	@Override
	public void init()
	{
		/*
		 * // List all available serial ports if running on Windows if
		 * (OperatingSystem.getOperatingSystem() == OperatingSystem.WINDOWS_10)
		 * {
		 * 
		 * @SuppressWarnings("unchecked") Enumeration<CommPortIdentifier>
		 * portEnum = CommPortIdentifier.getPortIdentifiers(); while
		 * (portEnum.hasMoreElements()) { CommPortIdentifier portIdentifier =
		 * portEnum.nextElement(); System.out.println(portIdentifier.getName() +
		 * " - " + portIdentifier.getPortType()); } }//
		 */

		try
		{
			String identifier = "";
			switch (OperatingSystem.getOperatingSystem())
			{
				case WINDOWS_10:
					identifier = "COM5";
					break;
				case LINUX:
					identifier = "/dev/ttyACM0";
					break;
				default:
					Quadcopter.getQuadcopter().registerSeriousError(this.getClass().getName(),
							"Unknown operating system");
					break;
			}
			CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(identifier);
			serialPort = (SerialPort) commPortIdentifier.open(this.getClass().getName(), PORT_OPEN_TIMEOUT);
			serialPort.setSerialPortParams(BAUD_RATE, DATABITS, STOPBITS, PARITY);

			inputBuffer = new LinkedList<Byte>();
			inputStream = serialPort.getInputStream();
			arduinoInputListener = new ArduinoInputListener(inputStream, this);
			arduinoInputListener.run();

			outputStream = serialPort.getOutputStream();
			arduinoOutputManager = new ArduinoOutputManager(outputStream, this);
			arduinoOutputManager.run();
		}
		catch (NoSuchPortException e)
		{
			Quadcopter.getQuadcopter().registerSeriousError(this.getClass().getName(), e);
		}
		catch (PortInUseException e)
		{
			Quadcopter.getQuadcopter().registerSeriousError(this.getClass().getName(), e);
		}
		catch (UnsupportedCommOperationException e)
		{
			Quadcopter.getQuadcopter().registerSeriousError(this.getClass().getName(), e);
		}
		catch (IOException e)
		{
			Quadcopter.getQuadcopter().registerSeriousError(this.getClass().getName(), e);
		}
	}
	@Override
	public void end()
	{
		arduinoInputListener.end();
		arduinoOutputManager.end();
	}

	@Override
	public void send(byte b)
	{
		sendForResult(b, 0);
	}
	@Override
	public void send(byte[] b)
	{
		sendForResult(b, 0);
	}

	@Override
	public byte[] sendForResult(byte b, int resultLength)
	{
		return sendForResult(new byte[] { b }, resultLength);
	}
	@Override
	public byte[] sendForResult(byte[] b, int resultLength)
	{
		int id = arduinoOutputManager.send(b, resultLength);
		if (resultLength == 0)
			return null;
		while (inputBufferIDs.peek() != id)
			try
			{
				// Sleep 10µs to decrease CPU usage
				Thread.sleep(0, 10000);
			}
			catch (InterruptedException e)
			{
				Quadcopter.getQuadcopter().registerSeriousError(this.getClass().getName(), e);
			}
		byte[] result = new byte[resultLength];
		for (int i = 0; i < resultLength; i++)
			result[i] = inputBuffer.remove();
		inputBufferIDs.remove();
		return result;
	}
}
