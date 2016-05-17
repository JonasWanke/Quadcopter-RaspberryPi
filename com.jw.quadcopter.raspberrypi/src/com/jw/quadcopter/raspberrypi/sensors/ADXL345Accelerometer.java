package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;

public final class ADXL345Accelerometer extends Accelerometer
{
	public static final byte BW_RATE_REGISTER = (byte) 0x2C;
	/**
	 * 0: Device in normal mode <br />
	 * 1: Device in low power mode. Results in higher noise
	 */
	public static final byte BW_RATE_LOW_POWER = (byte) 0b00010000;
	public static final byte BW_RATE_3200 = (byte) 0b00001111;
	public static final byte BW_RATE_1600 = (byte) 0b00001110;
	public static final byte BW_RATE_800 = (byte) 0b00001101;
	public static final byte BW_RATE_400 = (byte) 0b00001100;
	public static final byte BW_RATE_200 = (byte) 0b00001011;
	public static final byte BW_RATE_100 = (byte) 0b00001010;
	public static final byte BW_RATE_50 = (byte) 0b00001001;
	public static final byte BW_RATE_25 = (byte) 0b00001000;
	public static final byte BW_RATE_12_5 = (byte) 0b00000111;
	public static final byte BW_RATE_6_25 = (byte) 0b00000110;
	public static final byte BW_RATE_3_13 = (byte) 0b00000101;
	public static final byte BW_RATE_1_56 = (byte) 0b00000100;
	public static final byte BW_RATE_0_78 = (byte) 0b00000011;
	public static final byte BW_RATE_0_39 = (byte) 0b00000010;
	public static final byte BW_RATE_0_20 = (byte) 0b00000001;
	public static final byte BW_RATE_0_10 = (byte) 0b00000000;

	public static final byte POWER_CTL_REGISTER = (byte) 0x2D;
	/**
	 * TODO: Add Javadoc for POWER_CTL_LINK <br />
	 * 0: <br />
	 * 1:
	 */
	public static final byte POWER_CTL_LINK = (byte) 0b00100000;
	/**
	 * 0: Disables automatic switching to sleep mode. <br />
	 * 1: Device automatically sleeps if inactivity is detected by inactivity
	 * function
	 */
	public static final byte POWER_CTL_AUTO_SLEEP = (byte) 0b00010000;
	/**
	 * 0: Device in standby mode <br />
	 * 1: Device in measurement mode
	 */
	public static final byte POWER_CTL_MEASURE = (byte) 0b00001000;
	/**
	 * 0: Device in normal mode <br />
	 * 1: Device in sleep mode
	 */
	public static final byte POWER_CTL_SLEEP = (byte) 0b00000100;
	public static final byte POWER_CTL_WAKEUP_8HZ = (byte) 0b00000000;
	public static final byte POWER_CTL_WAKEUP_4HZ = (byte) 0b00000001;
	public static final byte POWER_CTL_WAKEUP_2HZ = (byte) 0b00000010;
	public static final byte POWER_CTL_WAKEUP_1HZ = (byte) 0b00000011;

	public static final byte DATA_FORMAT_REGISTER = (byte) 0x31;
	public static final byte DATA_FORMAT_SELF_TEST = (byte) 0b10000000;
	public static final byte DATA_FORMAT_SPI = (byte) 0b01000000;
	public static final byte DATA_FORMAT_INT_INVERT = (byte) 0b00100000;
	public static final byte DATA_FORMAT_JUSTIFY = (byte) 0b00000100;

	public static final byte FIFO_CTL_REGISTER = (byte) 0x38;
	public static final byte FIFO_CTL_FIFO_MODE_BYPASS = (byte) 0b00000000;
	public static final byte FIFO_CTL_FIFO_MODE_FIFO = (byte) 0b01000000;
	public static final byte FIFO_CTL_FIFO_MODE_STREAM = (byte) 0b10000000;
	public static final byte FIFO_CTL_FIFO_MODE_TRIGGER = (byte) 0b11000000;
	/**
	 * 0: Trigger event linked to INT1 <br />
	 * 1: Trigger event linked to INT2
	 */
	public static final byte FIFO_CTL_TRIGGER = (byte) 0b00100000;

	public ADXL345Accelerometer()
	{
	}

	@Override
	public void init(CommunicationManager communicationManager, Range range)
	{
		super.init(communicationManager, range);

		communicationManager.send(CommunicationManager.CTRL_SENSOR_INIT_ACCELEROMETER);
		communicationManager.send((byte) 3);

		// BW_RATE
		communicationManager.send(BW_RATE_REGISTER);
		communicationManager.send(BW_RATE_200);

		// POWER_CTL
		communicationManager.send(POWER_CTL_REGISTER);
		communicationManager.send((byte) (POWER_CTL_LINK | POWER_CTL_MEASURE | POWER_CTL_WAKEUP_8HZ));

		// FIFO_CTL
		communicationManager.send(FIFO_CTL_REGISTER);
		communicationManager.send(FIFO_CTL_FIFO_MODE_STREAM);

		// DATA_FORMAT
		communicationManager.send(FIFO_CTL_REGISTER);
		communicationManager.send(FIFO_CTL_FIFO_MODE_STREAM);
	}

	@Override
	public void updateValues()
	{
	}
}
