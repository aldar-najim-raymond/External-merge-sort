package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import com.github.aldar_najim_raymond.merge.FileCreator;
import com.github.aldar_najim_raymond.merge.InputStream;
import com.github.aldar_najim_raymond.merge.MultiWayMerge;
import com.github.aldar_najim_raymond.merge.SingleFileMerge;
import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.test.TestWriteSpeed;


public class TempMainClass {
	
	
	public static void main(String[] args) throws Exception {
		
		//System.out.println(SingleFileMerge.isFileSorted("1.txt.1.sorted", 65536));
		
		
		BigInteger integers = new BigInteger("250");
		FileCreator.createRandomFile("1.txt", integers, 65536);

		MultiWayMerge.startMultiWayMerge(100, 1, "1.txt");
		
	}
}
