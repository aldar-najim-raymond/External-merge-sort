package com.github.aldar_najim_raymond.test;

import java.util.ArrayList;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TestMemoryBuffer_BufferSizes {
	/*
	 * Testing different Memory buffer sizes
	 */
	public static void main(String[] args) {

		// Different buffer sizes to test
		ArrayList<Integer> bufferSize = new ArrayList<>();
		for (int i = 1024; i < 32768; i += 1024) {
			bufferSize.add(i);
		}

		String fileType = "memoryBuffer";

		// Testing write for each buffer size with a 100mb large file
		System.out.println("Testing different bufferSizes for writing and reading 100mb");
		System.out.println("Buffer Size, Write, Read");
		for (Integer i : bufferSize) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTakenWrite = 0;
			long timeTakenRead = 0;
			for (int runs = 0; runs < TestReadWriteSuite.runs; runs++) {
				timeTakenWrite += TestWriteSpeed.testReaderWriterMemoryBuffer_Write(fileName, i, 2500000, 1);
				timeTakenRead += TestReadSpeed.testReaderWriter_Read(fileName, Implementation.MEMORYBUFFER, i);
			}
			timeTakenWrite /= TestReadWriteSuite.runs;
			timeTakenRead /= TestReadWriteSuite.runs;
			System.out.println(i.toString() + " " + timeTakenWrite + " " + timeTakenRead);
			TestReadWriteSuite.deleteFile(fileName);
		}
	}
}
