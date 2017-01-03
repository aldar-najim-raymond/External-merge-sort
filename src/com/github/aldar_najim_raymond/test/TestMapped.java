package com.github.aldar_najim_raymond.test;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TestMapped {

	public static void main(String[] args) {
		String fileType = "mapped";

		/*
		 * Testing the write speed of simple writer
		 */
		System.out.println("Testing Write speed of the " + fileType + " implementation");
		for (BigInteger i : TestReadWriteSuite.testValues) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTaken = TestWriteSpeed.testReaderWriterMapped_Write(fileName, i.intValue() * (4 * 8 * 8), i,
					TestReadWriteSuite.runs);
			System.out.println(i.toString() + " " + timeTaken);
		}

		/*
		 * Testing the read speed of the simple reader
		 */
		System.out.println("Testing Read speed of the " + fileType + " implementation");
		for (BigInteger i : TestReadWriteSuite.testValues) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTaken = TestReadSpeed.testReaderWriter_Read(fileName, TestReadWriteSuite.runs,
					Implementation.MAPPED, 0);
			System.out.println(i.toString() + " " + timeTaken);
		}

	}

}
