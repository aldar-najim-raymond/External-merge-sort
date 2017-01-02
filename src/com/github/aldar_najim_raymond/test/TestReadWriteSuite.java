package com.github.aldar_najim_raymond.test;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TestReadWriteSuite {

	public static void main(String[] args) {
		//BigInteger integers = new BigInteger("100000");
		int integers = 120;
		int runs = 1;
		
		//System.out.println(TestWriteSpeed.testReaderWriterSimple_Write("a3.txt", integers,runs));
		//System.out.println(TestWriteSpeed.testReaderWriterBuffered_Write("a2.txt", integers,runs));
		//System.out.println(TestWriteSpeed.testReaderWriterMemoryBuffer_Write("a3.txt", 8192,integers,runs));
		//System.out.println(TestWriteSpeed.testReaderWriterMapped_Write("a4.txt", integers*4*8*8,integers,runs));
		
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a3.txt", runs, Implementation.SIMPLE));
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a3.txt", runs, Implementation.BUFFERED));
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a3.txt", runs, Implementation.MEMORYBUFFER));
		//System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a3.txt", runs, Implementation.MAPPED));
		
	}

}
