package com.github.aldar_najim_raymond;

import java.nio.ByteBuffer;
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
	 * Transforming an integer to a byte array
	 */
	public static byte[] IntToByteArray(int number) {
		return  ByteBuffer.allocate(4).putInt(number).array();
	}
	
	/*
	 * Transforming byte array to an integer
	 */
	public static int ByteArrayToInt(byte[] number){
	     return ByteBuffer.wrap(number).getInt();
	}
}
