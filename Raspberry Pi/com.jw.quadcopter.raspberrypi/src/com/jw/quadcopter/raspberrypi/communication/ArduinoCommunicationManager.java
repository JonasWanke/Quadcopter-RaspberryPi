package com.jw.quadcopter.raspberrypi.communication;

import java.io.IOException;

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

	public static final byte CTRL_SENSOR_INIT_ACCELEROMETER = 0b00001000;
	public static final byte CTRL_SENSOR_INIT_GYROSCOPE = 0b00001001;
	public static final byte CTRL_SENSOR_INIT_MAGNETOMETER = 0b00001010;
	public static final byte CTRL_SENSOR_INIT_BAROMETER = 0b00001011;
	
	private SerialPort serialPort;
	private ArduinoInputListener arduinoInputListener;

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
					Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(),
							"Unknown operating system");
					break;
			}
			CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(identifier);
			serialPort = (SerialPort) commPortIdentifier.open(this.getClass().getName(), PORT_OPEN_TIMEOUT);
			serialPort.setSerialPortParams(BAUD_RATE, DATABITS, STOPBITS, PARITY);

			inputStream = serialPort.getInputStream();
			arduinoInputListener = new ArduinoInputListener(inputStream, this);
			arduinoInputListener.run();
			outputStream = serialPort.getOutputStream();
		}
		catch (NoSuchPortException e)
		{
			Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
		}
		catch (PortInUseException e)
		{
			Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
		}
		catch (UnsupportedCommOperationException e)
		{
			Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
		}
		catch (IOException e)
		{
			Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
		}
	}
	@Override
	public void close()
	{
		try
		{
			arduinoInputListener.join();
		}
		catch (InterruptedException e)
		{
			Quadcopter.getQuadcopter().RegisterSeriousError(this.getClass().getName(), e);
		}
	}

	@Override
	public void send(int b)
	{
		sendForResult(b, 0);
	}
	@Override
	public void send(byte[] b)
	{
		sendForResult(b, 0);
	}

	@Override
	public byte[] sendForResult(int b, int answerLength)
	{
		return null;
	}
	@Override
	public byte[] sendForResult(byte[] b, int answerLength)
	{
		return null;
	}
}
