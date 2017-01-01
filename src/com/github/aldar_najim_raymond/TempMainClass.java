package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import com.github.aldar_najim_raymond.merge.SingleFileMerge;
import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMemoryBuffer;
import com.github.aldar_najim_raymond.test.TestReadSpeed;
import com.github.aldar_najim_raymond.test.TestWriteSpeed;
import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TempMainClass {

	public static void main(String[] args) throws Exception {

		int integers = 10;

		ReaderWriterMapped writer = new ReaderWriterMapped("asd.txt", IOType.WRITE, integers * 4 * 8 * 8);
		ReaderWriterMapped reader = new ReaderWriterMapped("asd.txt", IOType.READ);

		try {
			for (int i = 0; i < integers; i++) {
				writer.writeInt(i);
			}
			writer.closeStream();
			try {
				while(true) {
					System.out.println(reader.readInt());
				}
			} catch(EOFException ex) {
				System.out.println(ex.toString());
				reader.closeStream();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
