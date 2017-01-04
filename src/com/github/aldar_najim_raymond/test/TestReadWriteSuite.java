package com.github.aldar_najim_raymond.test;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

public abstract class TestReadWriteSuite {

	// TODO: https://docs.oracle.com/javase/7/docs/api/java/nio/channels/FileChannel.html#map(java.nio.channels.FileChannel.MapMode,%20long,%20long)
	// mapping!!
	
	public static ArrayList<BigInteger> testValues = new ArrayList<BigInteger>() {
		private static final long serialVersionUID = 1L;
		{
			add(new BigInteger("25"));
			add(new BigInteger("250"));
			add(new BigInteger("2500"));
			add(new BigInteger("25000"));
			add(new BigInteger("250000"));
			add(new BigInteger("2500000"));
		}
	};
	
	public static int runs = 10;
	
	public static void deleteFile(String fileName){
		File file = new File(fileName);
		file.delete();
	}
}
