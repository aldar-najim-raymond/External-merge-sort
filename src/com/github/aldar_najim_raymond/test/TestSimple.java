package com.github.aldar_najim_raymond.test;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TestSimple {

	public static void main(String[] args) {

		String fileType = "simple";

		/*
		 * Testing the write and read speed of simple writer
		 */
		System.out.println("Testing Write and Read speed of the " + fileType + " implementation");
		System.out.println("Integers, Write, Read, Write/Int, Read/Int");
		for (BigInteger i : TestReadWriteSuite.testValues) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTakenWrite = 0;
			long timeTakenRead = 0;
			for (int runs = 0; runs < TestReadWriteSuite.runs; runs++) {
				timeTakenWrite += TestWriteSpeed.testReaderWriterSimple_Write(fileName, i, 1);
				timeTakenRead += TestReadSpeed.testReaderWriter_Read(fileName, Implementation.SIMPLE, 0);
			}
			timeTakenWrite /= TestReadWriteSuite.runs;
			timeTakenRead /= TestReadWriteSuite.runs;
			long writePerInt = timeTakenWrite / i.longValue();
			long readPerInt = timeTakenRead / i.longValue();
			System.out.println(
					i.toString() + " " + timeTakenWrite + " " + timeTakenRead + " " + writePerInt + " " + readPerInt);
			TestReadWriteSuite.deleteFile(fileName);
		}
	}
}
