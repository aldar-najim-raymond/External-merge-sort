package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.IOException;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;


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
