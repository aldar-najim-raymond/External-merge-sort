package com.github.aldar_najim_raymond.merge;

import java.io.IOException;
import java.math.BigInteger;

import com.github.aldar_najim_raymond.UtilisationClass;
import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;

public class FileCreator {

	/*
	 * Create an arbitrary large file with random integer values
	 */
	public void createRandomFile(String fileName, BigInteger integers, int memory) {
		ReaderWriterMapped writer = new ReaderWriterMapped(fileName, IOType.WRITE, memory);

		try {
			for (BigInteger i = BigInteger.ZERO; i.compareTo(integers) < 0; i = i.add(BigInteger.ONE)) {
				writer.writeInt(UtilisationClass.randomNumber());
			}
			writer.closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
