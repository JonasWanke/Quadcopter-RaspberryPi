package com.jw.quadcopter.raspberrypi.util;

public enum OperatingSystem
{
	UNKNOWN, LINUX, WINDOWS_10;

	private static OperatingSystem operatingSystem;

	public static OperatingSystem getOperatingSystem()
	{
		if (operatingSystem == null)
		{
			switch (System.getProperty("os.name"))
			{
				case "Windows 10":
					operatingSystem = WINDOWS_10;
					break;
				default:
					operatingSystem = UNKNOWN;
					break;
			}
		}
		return operatingSystem;
	}
}
