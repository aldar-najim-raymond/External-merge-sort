package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;
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

	public static void main(String[] args) {

		//BigInteger n = new BigInteger("100000000");
		int n = 120000;
		int runs = 1;
		
		//System.out.println(TestWriteSpeed.testReaderWriterSimple_Write("a1.txt", n,runs));
		//System.out.println(TestWriteSpeed.testReaderWriterBuffered_Write("a2.txt", n,runs));
		//System.out.println(TestWriteSpeed.testReaderWriterMemoryBuffer_Write("a3.txt", 8192,n,runs));
		//System.out.println(TestWriteSpeed.testReaderWriterMapped_Write("a4.txt", n*4*8*8,n,runs));
		
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a1.txt", runs, Implementation.SIMPLE));
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a1.txt", runs, Implementation.BUFFERED));
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a1.txt", runs, Implementation.MEMORYBUFFER));
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a1.txt", runs, Implementation.MAPPED));
		

	}
}
