package com.github.aldar_najim_raymond;

import java.io.IOException;

import com.github.aldar_najim_raymond.readerWriterPackage.IOType;
import com.github.aldar_najim_raymond.readerWriterPackage.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriterPackage.ReaderWriterMapped;
import com.github.aldar_najim_raymond.readerWriterPackage.ReaderWriterMemoryBuffer;

public class TempMainClass {

	public static void main(String[] args) {

		int ns = 1000000;

		long before, after;

		// 1.1
		/*
		before = System.currentTimeMillis();
		ReaderWriterSimple rws = new ReaderWriterSimple("t1.txt", IOType.WRITE);
		try {
			for (int i = 0; i < ns; i++) {
				rws.writeInt(i);
			}
			rws.closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		after = System.currentTimeMillis() - before;
		System.out.println(after);*/

		// 1.2
		before = System.currentTimeMillis();
		ReaderWriterBuffered rwmb = new ReaderWriterBuffered("t2.txt", IOType.WRITE);
		try {
			for (int i = 0; i < ns; i++) {
				rwmb.writeInt(i);
			}
			rwmb.closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		after = System.currentTimeMillis() - before;
		System.out.println(after);

		// 1.3
		before = System.currentTimeMillis();
		ReaderWriterMemoryBuffer rwm = new ReaderWriterMemoryBuffer("t3.txt", IOType.WRITE, 1000);
		try {
			for (int i = 0; i < ns; i++) {
				rwm.writeInt(i);
			}
			rwm.closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		after = System.currentTimeMillis() - before;
		System.out.println(after);

		// 1.4
		before = System.currentTimeMillis();
		ReaderWriterMapped rwb = new ReaderWriterMapped("t4.txt", IOType.WRITE, ns * 4 * 8 * 8);
		try {
			for (int i = 0; i < ns; i++) {
				rwb.writeInt(i);
			}
			rwb.closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		after = System.currentTimeMillis() - before;
		System.out.println(after);

	}
}
