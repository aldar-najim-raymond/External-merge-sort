package com.github.aldar_najim_raymond.test;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TestBuffered {

	public static void main(String[] args) {
		String fileType = "buffered";

		/*
		 * Testing the write and read speed of simple writer
		 */
		System.out.println("Testing Write and Read speed of the " + fileType + " implementation");
		System.out.println("Integers, Write, Read");
		for (BigInteger i : TestReadWriteSuite.testValues) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTakenWrite = 0;
			long timeTakenRead = 0;
			for (int runs = 0; runs < TestReadWriteSuite.runs; runs++) {
				timeTakenWrite += TestWriteSpeed.testReaderWriterBuffered_Write(fileName, i, 1);
				timeTakenRead += TestReadSpeed.testReaderWriter_Read(fileName, Implementation.BUFFERED, 0);
			}
			timeTakenWrite /= TestReadWriteSuite.runs;
			timeTakenRead /= TestReadWriteSuite.runs;
			System.out.println(i.toString() + " " + timeTakenWrite + " " + timeTakenRead);
			TestReadWriteSuite.deleteFile(fileName);
		}
	}

}
