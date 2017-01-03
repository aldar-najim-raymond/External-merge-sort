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
		System.out.println("Testing different bufferSizes for writing");
		for (Integer i : bufferSize) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTaken = TestWriteSpeed.testReaderWriterMemoryBuffer_Write(fileName, i, 2500000,
					TestReadWriteSuite.runs);
			System.out.println(i.toString() + " " + timeTaken);
		}

		// Testing read for each buffer size with a 100mb large file
		System.out.println("Testing different bufferSizes for reading");
		for (Integer i : bufferSize) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTaken = TestReadSpeed.testReaderWriter_Read(fileName, TestReadWriteSuite.runs,
					Implementation.MEMORYBUFFER, i);
			System.out.println(i.toString() + " " + timeTaken);
		}

	}

}
