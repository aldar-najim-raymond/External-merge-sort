package com.github.aldar_najim_raymond.test;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TestSimple {

	public static void main(String[] args) {

		String fileType = "simple";

		/*
		 * Testing the write speed of simple writer
		 */
		System.out.println("Testing Write speed of the " + fileType + " implementation");
		for (BigInteger i : TestReadWriteSuite.testValues) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTaken = TestWriteSpeed.testReaderWriterSimple_Write(fileName, i, TestReadWriteSuite.runs);
			System.out.println(i.toString() + " " + timeTaken);
		}

		/*
		 * Testing the read speed of the simple reader
		 */
		System.out.println("Testing Read speed of the " + fileType + " implementation");
		for (BigInteger i : TestReadWriteSuite.testValues) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTaken = TestReadSpeed.testReaderWriter_Read(fileName, TestReadWriteSuite.runs,
					Implementation.SIMPLE, 0);
			System.out.println(i.toString() + " " + timeTaken);
		}
	}
}
