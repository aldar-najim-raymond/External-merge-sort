package com.github.aldar_najim_raymond.test;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public abstract class TestReadWriteSuite {

	public static ArrayList<BigInteger> testValues = new ArrayList<BigInteger>() {
		private static final long serialVersionUID = 1L;
		{
			add(new BigInteger("250"));
			add(new BigInteger("2500"));
			add(new BigInteger("25000"));
			add(new BigInteger("250000"));
			add(new BigInteger("2500000"));
		}
	};
	
	public static int runs = 5;

		// 2147483647 / (4*8*8) = 8388607.99609 max mapped writing
		// BigInteger integers = new BigInteger("250000000");

		// System.out.println(TestWriteSpeed.testReaderWriterMapped_Write("a4.txt",
		// 10000 * 4 * 8 * 8 ,integers,runs));

		// System.out.println(TestReadSpeed.testReaderWriter_Read("a4.txt",
		// runs, Implementation.MAPPED, 0));
	
	public void deleteFile(String fileName){
		File file = new File(fileName);
		file.delete();
	}
}
