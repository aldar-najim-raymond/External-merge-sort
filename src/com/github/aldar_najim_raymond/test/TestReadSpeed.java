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
		SIMPLE,
		BUFFERED,
		MEMORYBUFFER,
		MAPPED
	}
	
	public static long testReaderWriterSimple_Read(String fileName, int runs, Implementation implementation) {

		long before = System.currentTimeMillis();
		ReaderWriterInterface rws;

		try {
			for (int currentRun = 0; currentRun < runs; currentRun++) {
				if (implementation == Implementation.SIMPLE){
					rws = new ReaderWriterSimple(fileName, IOType.READ);
				} else if (implementation == Implementation.BUFFERED){
					rws = new ReaderWriterBuffered(fileName, IOType.READ);
				} else if (implementation == Implementation.MEMORYBUFFER){
					rws = new ReaderWriterMemoryBuffer(fileName, IOType.READ);
				} else {
					rws = new ReaderWriterMapped(fileName, IOType.READ);
				}
				double i = 0;
				try {
					// Read until file has reached EOF
					while (true) {
						System.out.println(i + " " + rws.readInt());
						//rws.readInt();
						i++;
						
					}
				} catch (EOFException eof) {
					System.out.println("iterated " + i + " times");
					rws.closeStream();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ((System.currentTimeMillis() - before) / runs);
	}
}
