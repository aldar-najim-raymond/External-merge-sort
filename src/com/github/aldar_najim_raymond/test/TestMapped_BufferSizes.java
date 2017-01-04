package com.github.aldar_najim_raymond.test;

import java.util.ArrayList;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TestMapped_BufferSizes {

	/*
	 * Testing different buffer Sizes for the mapped implementation
	 */
	public static void main(String[] args) {
		// Different buffer sizes to test
		ArrayList<Integer> bufferSize = new ArrayList<>();
		for (int i = 1024; i < 16384; i += 1024) {
			bufferSize.add(i);
		}
		for (int i = 16384; i <= 65536; i += 4096) {
			bufferSize.add(i);
		}

		int integers = 2500000; // 100mb
		
		String fileType = "mapped";

		// Testing write for each buffer size with a 100mb large file
		System.out.println("Testing different mapped bufferSizes for writing and reading 100mb");
		System.out.println("Buffer Size, Write, Read, Write/Int, Read/Int");
		for (Integer i : bufferSize) {
			String fileName = fileType + "_" + i.toString() + ".txt";
			long timeTakenWrite = 0;
			long timeTakenRead = 0;
			for (int runs = 0; runs < TestReadWriteSuite.runs; runs++) {
				timeTakenWrite += TestWriteSpeed.testReaderWriterMapped_Write(fileName, i, integers, 1);
				timeTakenRead += TestReadSpeed.testReaderWriter_Read(fileName, Implementation.MAPPED, i);
			}
			timeTakenWrite /= TestReadWriteSuite.runs;
			timeTakenRead /= TestReadWriteSuite.runs;
			long writePerInt = timeTakenWrite / integers;
			long readPerInt = timeTakenRead / integers;
			System.out.println(
					i.toString() + " " + timeTakenWrite + " " + timeTakenRead + " " + writePerInt + " " + readPerInt);
			TestReadWriteSuite.deleteFile(fileName);
		}
	}
}
