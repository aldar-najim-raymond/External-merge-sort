package com.github.aldar_najim_raymond;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Random;

public class UtilisationClass {

	private static Random random = new Random();

	/*
	 * Use reflection to calculate the running time of a method
	 */
	// TODO: parameters for the constructor
	public static long getRuntime(String methodName, Class<?> cls,
			Class<?>[] constructorTypes, Object[] constructorValues,
			Class<?>[] parameterTypes, Object[] parameterValues) {
		long before, after;
		try {
			//Object obj = cls.newInstance();
			Object obj = cls.getClass().getDeclaredConstructor(constructorTypes).newInstance(constructorValues);
			System.out.println(obj.getClass());
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
