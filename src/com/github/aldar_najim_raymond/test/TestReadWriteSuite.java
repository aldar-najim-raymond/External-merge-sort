package com.github.aldar_najim_raymond.test;

import com.github.aldar_najim_raymond.test.TestReadSpeed.Implementation;

public class TestReadWriteSuite {

	public static void main(String[] args) {
		//BigInteger n = new BigInteger("100000000");
		int integers = 120000;
		int runs = 1;
		
		System.out.println(TestWriteSpeed.testReaderWriterSimple_Write("a1.txt", integers,runs));
		System.out.println(TestWriteSpeed.testReaderWriterBuffered_Write("a2.txt", integers,runs));
		System.out.println(TestWriteSpeed.testReaderWriterMemoryBuffer_Write("a3.txt", 8192,integers,runs));
		System.out.println(TestWriteSpeed.testReaderWriterMapped_Write("a4.txt", integers*4*8*8,integers,runs));
		
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a1.txt", runs, Implementation.SIMPLE));
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a2.txt", runs, Implementation.BUFFERED));
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a3.txt", runs, Implementation.MEMORYBUFFER));
		System.out.println(TestReadSpeed.testReaderWriterSimple_Read("a4.txt", runs, Implementation.MAPPED));
		
	}

}
