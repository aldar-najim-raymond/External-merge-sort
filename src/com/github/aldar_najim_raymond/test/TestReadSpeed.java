// TODO : buffer sizes
package com.github.aldar_najim_raymond.test;

import java.io.EOFException;
import java.io.IOException;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterInterface;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMemoryBuffer;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterSimple;

public class TestReadSpeed {

	public enum Implementation {
		SIMPLE, BUFFERED, MEMORYBUFFER, MAPPED
	}

	public static long testReaderWriter_Read(String fileName, int runs, 
			Implementation implementation, int bufferSize) {

		long before = System.currentTimeMillis();
		ReaderWriterInterface rws;

		try {
			for (int currentRun = 0; currentRun < runs; currentRun++) {
				if (implementation == Implementation.SIMPLE) {
					rws = new ReaderWriterSimple(fileName, IOType.READ);

				} else if (implementation == Implementation.BUFFERED) {
					rws = new ReaderWriterBuffered(fileName, IOType.READ);

				} else if (implementation == Implementation.MEMORYBUFFER) {
					rws = new ReaderWriterMemoryBuffer(fileName, IOType.READ, bufferSize);

				} else if (implementation == Implementation.MAPPED) {
					rws = new ReaderWriterMapped(fileName, IOType.READ, bufferSize);
					
				} else {
					continue;
				}
				try {
					// Read until file has reached EOF
					while (true) {
						rws.readInt();
					}
				} catch (EOFException eof) {
					rws.closeStream();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ((System.currentTimeMillis() - before) / runs);
	}
}
