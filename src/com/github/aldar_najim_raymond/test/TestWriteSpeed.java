package com.github.aldar_najim_raymond.test;

import java.io.IOException;
import java.math.BigInteger;

import com.github.aldar_najim_raymond.UtilisationClass;
import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMemoryBuffer;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterSimple;

public class TestWriteSpeed {
	/*
	 * Testing the write speed of the ReaderWriterSimple class (1.1.1). Only use
	 * n integers to write, as we expect this to be very slow compared to the
	 * other implementations
	 */
	public static long testReaderWriterSimple_Write(String fileName,
			int integers, int runs) {

		long before = System.currentTimeMillis();
		ReaderWriterSimple rws;

		try {
			for (int currentRun = 0; currentRun < runs; currentRun++) {
				rws = new ReaderWriterSimple(fileName, IOType.WRITE);
				for (int i = 0; i < integers; i++) {
					rws.writeInt(UtilisationClass.randomNumber());
				}
				rws.closeStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ((System.currentTimeMillis() - before) / runs);
	}

	/*
	 * Test write speed of the buffered (1.1.2) implementation
	 */
	public static long testReaderWriterBuffered_Write(String fileName,
			int integers, int runs) {
		return (testReaderWriterBuffered_Write(fileName,
				BigInteger.valueOf(integers), runs));
	}

	public static long testReaderWriterBuffered_Write(String fileName,
			BigInteger integers, int runs) {

		long before = System.currentTimeMillis();
		ReaderWriterBuffered rws;
		try {
			for (int currentRun = 0; currentRun < runs; currentRun++) {
				rws = new ReaderWriterBuffered(fileName, IOType.WRITE);
				/*
				 * Iterating n BigInteger times
				 */
				for (BigInteger i = BigInteger.ONE; i.compareTo(integers) < 0; i = i
						.add(BigInteger.ONE)) {
					rws.writeInt(UtilisationClass.randomNumber());
				}
				rws.closeStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ((System.currentTimeMillis() - before) / runs);
	}

	/*
	 * Test write speed of the memory buffered (1.1.3) implementation
	 */
	public static long testReaderWriterMemoryBuffer_Write(String fileName,
			int bufferSize, int integers, int runs) {
		return (testReaderWriterMemoryBuffer_Write(fileName, bufferSize,
				BigInteger.valueOf(integers), runs));
	}

	public static long testReaderWriterMemoryBuffer_Write(String fileName,
			int bufferSize, BigInteger integers, int runs) {

		long before = System.currentTimeMillis();
		ReaderWriterMemoryBuffer rws;
		try {
			for (int currentRun = 0; currentRun < runs; currentRun++) {
				rws = new ReaderWriterMemoryBuffer(fileName, IOType.WRITE,
						bufferSize);
				/*
				 * Iterating n BigInteger times
				 */
				for (BigInteger i = BigInteger.ONE; i.compareTo(integers) < 0; i = i
						.add(BigInteger.ONE)) {
					rws.writeInt(UtilisationClass.randomNumber());
				}
				rws.closeStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ((System.currentTimeMillis() - before) / runs);
	}

	/*
	 * Test write speed of the memory mapped (1.1.4) implementation
	 */
	public static long testReaderWriterMapped_Write(String fileName,
			int bufferSize, int integers, int runs) {
		return (testReaderWriterMapped_Write(fileName, bufferSize,
				BigInteger.valueOf(integers), runs));
	}

	public static long testReaderWriterMapped_Write(String fileName,
			int bufferSize, BigInteger integers, int runs) {

		long before = System.currentTimeMillis();
		ReaderWriterMapped rws;
		try {
			for (int currentRun = 0; currentRun < runs; currentRun++) {
				rws = new ReaderWriterMapped(fileName, IOType.WRITE, bufferSize);
				/*
				 * Iterating n BigInteger times
				 */
				for (BigInteger i = BigInteger.ONE; i.compareTo(integers) < 0; i = i
						.add(BigInteger.ONE)) {
					rws.writeInt(UtilisationClass.randomNumber());
				}
				rws.closeStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ((System.currentTimeMillis() - before) / runs);
	}

}
