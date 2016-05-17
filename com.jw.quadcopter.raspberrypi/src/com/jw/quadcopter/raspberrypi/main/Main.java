package com.jw.quadcopter.raspberrypi.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class Main
{
	public static void main(String[] args)
			throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException
	{
		System.out.println(System.getProperty("os.name"));
		
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements())
		{
			CommPortIdentifier portIdentifier = portEnum.nextElement();
			System.out.println(portIdentifier.getName() + " - " + getPortTypeName(portIdentifier.getPortType()));
		}

		CommPortIdentifier identifier = CommPortIdentifier.getPortIdentifier("COM5");
		if (identifier.isCurrentlyOwned())
		{
			System.out.println("Error: Port is currently in use");
			return;
		}
		CommPort port = identifier.open(Main.class.getName(), 2000);
		SerialPort serialPort = (SerialPort) port;
		serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		// InputStream input = serialPort.getInputStream();
		BufferedReader input = new BufferedReader(
				new InputStreamReader(serialPort.getInputStream(), StandardCharsets.US_ASCII));
		// DataInputStream input = new
		// DataInputStream(serialPort.getInputStream());
		while (true)
		{
			if (input.ready())
			{
				System.out.println(input.readLine());
			}
		}
	}

	static String getPortTypeName(int portType)
	{
		switch (portType)
		{
			case CommPortIdentifier.PORT_I2C:
				return "I2C";
			case CommPortIdentifier.PORT_PARALLEL:
				return "Parallel";
			case CommPortIdentifier.PORT_RAW:
				return "Raw";
			case CommPortIdentifier.PORT_RS485:
				return "RS485";
			case CommPortIdentifier.PORT_SERIAL:
				return "Serial";
			default:
				return "unknown type";
		}
	}
}