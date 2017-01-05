package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import com.github.aldar_najim_raymond.merge.FileCreator;
import com.github.aldar_najim_raymond.merge.SingleFileMerge;
import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.test.TestWriteSpeed;


public class TempMainClass {

	public static void main(String[] args) throws Exception {

		BigInteger integers = new BigInteger("25000000");
		int memory = 65536;
		String fileName = "test.txt";
		FileCreator.createRandomFile(fileName, integers, memory);

		ArrayList<String> parts = SingleFileMerge.mergeFilePartly(fileName, memory * 3);
		
		for (String s : parts) {
			System.out.println(s);

		}
		
		System.out.println(SingleFileMerge.isFileSorted(parts.get(0),memory));
	}
}
