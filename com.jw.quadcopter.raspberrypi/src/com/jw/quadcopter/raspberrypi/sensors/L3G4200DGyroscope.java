package com.jw.quadcopter.raspberrypi.sensors;

import com.jw.quadcopter.raspberrypi.communication.CommunicationManager;

public final class L3G4200DGyroscope extends Gyroscope
{
	public final byte CTRL_REG1_REGISTER = (byte) 0x20;
	/**
	 * Output Data Rate and Bandwidth selection <br />
	 * Format: CTRL_REG1_DR_BW_<ODR>_<Cut-Off>
	 */
	public final byte CTRL_REG1_DR_BW_100_12_5 = (byte) 0b00000000;
	public final byte CTRL_REG1_DR_BW_100_25 = (byte) 0b00010000;
	public final byte CTRL_REG1_DR_BW_200_12_5 = (byte) 0b01000000;
	public final byte CTRL_REG1_DR_BW_200_25 = (byte) 0b01010000;
	public final byte CTRL_REG1_DR_BW_200_50 = (byte) 0b01100000;
	public final byte CTRL_REG1_DR_BW_200_70 = (byte) 0b01110000;
	public final byte CTRL_REG1_DR_BW_400_20 = (byte) 0b10000000;
	public final byte CTRL_REG1_DR_BW_400_25 = (byte) 0b10010000;
	public final byte CTRL_REG1_DR_BW_400_50 = (byte) 0b10100000;
	public final byte CTRL_REG1_DR_BW_400_110 = (byte) 0b10110000;
	public final byte CTRL_REG1_DR_BW_800_30 = (byte) 0b11000000;
	public final byte CTRL_REG1_DR_BW_800_35 = (byte) 0b11010000;
	public final byte CTRL_REG1_DR_BW_800_50 = (byte) 0b11100000;
	public final byte CTRL_REG1_DR_BW_800_110 = (byte) 0b11110000;
	/**
	 * Power down mode enable <br />
	 * 0: Device in normal mode or sleep mode <br />
	 * 1: Device in power down mode <br />
	 * Default: 0
	 */
	public final byte CTRL_REG1_PD = (byte) 0b00001000;
	/**
	 * Z axis enable <br />
	 * 0: Z axis disabled <br />
	 * 1: Z axis enabled <br />
	 * Default: 1
	 */
	public final byte CTRL_REG1_Z_EN = (byte) 0b00000100;
	/**
	 * Y axis enable <br />
	 * 0: Y axis disabled <br />
	 * 1: Y axis enabled <br />
	 * Default: 1
	 */
	public final byte CTRL_REG1_Y_EN = (byte) 0b00000010;
	/**
	 * X axis enable <br />
	 * 0: X axis disabled <br />
	 * 1: X axis enabled <br />
	 * Default: 1
	 */
	public final byte CTRL_REG1_X_EN = (byte) 0b00000001;

	public final byte CTRL_REG2_REGISTER = (byte) 0x21;
	public final byte CTRL_REG2_HPF_MODE_NORMAL_RESET = (byte) 0b00000000;
	public final byte CTRL_REG2_HPF_MODE_REFERENCE_SIGNAL = (byte) 0b00010000;
	public final byte CTRL_REG2_HPF_MODE_NORMAL = (byte) 0b00100000;
	public final byte CTRL_REG2_HPF_MODE_AUTORESET_ON_INTERRUPT = (byte) 0b00110000;

	public final byte CTRL_REG4_REGISTER = (byte) 0x23;
	/**
	 * Block Data Update <br />
	 * 0: Continuous update <br />
	 * 1: Output registers not updated until MSB and LSB reading <br />
	 * Default: 0
	 */
	public final byte CTRL_REG4_BDU = (byte) 0b10000000;
	/**
	 * Big/Little Endian Data Selection <br />
	 * 0: Data LSB @ lower address <br />
	 * 1: Data MSB @ lower address <br />
	 * Default: 0
	 */
	public final byte CTRL_REG4_BLE = (byte) 0b01000000;
	/**
	 * Full scale selection <br />
	 * Default: 00 (250)
	 */
	public final byte CTRL_REG4_FS_250 = (byte) 0b00000000;
	public final byte CTRL_REG4_FS_500 = (byte) 0b00010000;
	public final byte CTRL_REG4_FS_2000 = (byte) 0b00100000;
	/**
	 * Self Test Enable <br />
	 * Default: 00 (Normal mode)
	 */
	public final byte CTRL_REG4_ST_NORMAL_MODE = (byte) 0b00000000;
	public final byte CTRL_REG4_ST_SELF_TEST_0 = (byte) 0b00000010;
	public final byte CTRL_REG4_ST_SELF_TEST_1 = (byte) 0b00000110;
	/**
	 * SPI Serial Interface Mode selection <br />
	 * 0: 4-wire interface <br />
	 * 1: 3-wire interface <br />
	 * Default: 0
	 */
	public final byte CTRL_REG4_SIM = (byte) 0b00000001;

	public final byte CTRL_REG5_REGISTER = (byte) 0x24;
	/**
	 * Reboot memory content <br />
	 * 0: normal mode <br />
	 * 1: reboot memory content <br />
	 * Default: 0
	 */
	public final byte CTRL_REG5_BOOT = (byte) 0b10000000;
	/**
	 * FIFO enable <br />
	 * 0: FIFO disabled <br />
	 * 1: FIFO enabled <br />
	 * Default: 0
	 */
	public final byte CTRL_REG5_FIFO_EN = (byte) 0b01000000;
	/**
	 * High Pass filter Enable <br />
	 * 0: HPF disabled <br />
	 * 1: HPF enabled <br />
	 * Default: 0
	 */
	public final byte CTRL_REG5_HP_EN = (byte) 0b00010000;

	public L3G4200DGyroscope()
	{
	}

	@Override
	public void init(CommunicationManager communicationManager, Range range)
	{
		super.init(communicationManager, range);

		communicationManager.send(CommunicationManager.CTRL_SENSOR_INIT_GYROSCOPE);
		communicationManager.send((byte) 4);

		// CTRL_REG1
		communicationManager.send(CTRL_REG1_REGISTER);
		communicationManager.send((byte) (CTRL_REG1_PD | CTRL_REG1_X_EN | CTRL_REG1_Y_EN | CTRL_REG1_Z_EN));

		// CTRL_REG2
		communicationManager.send(CTRL_REG2_REGISTER);
		communicationManager.send((byte) 0);

		// CTRL_REG4
		communicationManager.send(CTRL_REG4_REGISTER);
		communicationManager.send(range.getRangeBits());

		// CTRL_REG5
		communicationManager.send(CTRL_REG5_REGISTER);
		communicationManager.send((byte) 0);
	}

	@Override
	public void updateValues()
	{

	}
}
