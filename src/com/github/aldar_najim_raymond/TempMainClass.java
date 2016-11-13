package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

import com.github.aldar_najim_raymond.merge.SingleFileMerge;
import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMemoryBuffer;

public class TempMainClass {

	public static void main(String[] args) {

		int ns = 1000;

		long before, after;

		// 1.3
		before = System.currentTimeMillis();
		ReaderWriterMemoryBuffer rwm = new ReaderWriterMemoryBuffer("t1.txt", IOType.WRITE, 1000);
		try {
			for (int i = 0; i < ns; i++) {
				rwm.writeInt(UtilisationClass.randomNumber());
			}
			rwm.closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		after = System.currentTimeMillis() - before;
		System.out.println(after);

		ArrayList<String> sorted = SingleFileMerge.mergeFilePartly("t1.txt", 1200);

		for (String s : sorted) {
			System.out.println(s);
		}

		ReaderWriterMemoryBuffer reader = new ReaderWriterMemoryBuffer(sorted.get(sorted.size() - 1), IOType.READ);
		try {
			for (int i = 0; i < ns; i++) {
				System.out.println(reader.readInt());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.closeStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
