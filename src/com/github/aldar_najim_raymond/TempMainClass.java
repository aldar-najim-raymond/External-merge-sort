package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

import com.github.aldar_najim_raymond.merge.SingleFileMerge;
import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMemoryBuffer;
import com.github.aldar_najim_raymond.test.TestWriteSpeed;

public class TempMainClass {

	public static void main(String[] args) {

		int n = 120000;
		
		System.out.println(TestWriteSpeed.testReaderWriterSimple_Write("a1.txt", n,1));
		System.out.println(TestWriteSpeed.testReaderWriterBuffered_Write("a2.txt", n,1));
		System.out.println(TestWriteSpeed.testReaderWriterMemoryBuffer_Write("a3.txt", 8192,n,1));
		System.out.println(TestWriteSpeed.testReaderWriterMapped_Write("a4.txt", n*4*8*8,n,1));
		

	}
}
