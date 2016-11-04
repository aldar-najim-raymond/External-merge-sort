package com.github.aldar_najim_raymond;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class UtilisationClass {

	private static Random random = new Random();

	/*
	 * Use reflection to calculate the running time of a method
	 */
	public static long getRuntime(String methodName, Class<?> cls,
			Class<?>[] parameterTypes, Object[] parameterValues) {
		long before, after;
		try {
			Object obj = cls.newInstance();
			Method m = cls.getDeclaredMethod(methodName, parameterTypes);

			before = System.currentTimeMillis();
			m.invoke(obj, parameterValues);
			after = System.currentTimeMillis();

			return (after - before);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return 0;
	}

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
