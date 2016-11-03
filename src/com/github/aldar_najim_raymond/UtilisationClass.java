package com.github.aldar_najim_raymond;

import java.util.Random;

public class UtilisationClass {
	
	private static Random random = new Random();
	
	/*
	 * Returns a random number between Integer.MIN and Integer.MAX
	 */
	public static int randomNumber() {
		int randomHigh = random.nextInt(Integer.MAX_VALUE);
		int randomLow = random.nextInt(Integer.MAX_VALUE);
		return randomHigh - randomLow;
	}
	
	/*
	 * taken from http://stackoverflow.com/a/28360724
	 */
	public static byte[] IntToByteArray(int data) {

		byte[] result = new byte[4];

		result[0] = (byte) ((data & 0xFF000000) >> 24);
		result[1] = (byte) ((data & 0x00FF0000) >> 16);
		result[2] = (byte) ((data & 0x0000FF00) >> 8);
		result[3] = (byte) ((data & 0x000000FF) >> 0);

		return result;
	}
}
