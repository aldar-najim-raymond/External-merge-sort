package com.github.aldar_najim_raymond.test;

import java.io.IOException;
import java.util.ArrayList;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterSimple;

public class TestKReadStreams {

	/*
	 * Testing how many simultaneously opened read streams are supported
	 */
	public static void main(String[] args) {
		int counter = 0;

		ArrayList<ReaderWriterSimple> streams = new ArrayList<ReaderWriterSimple>();

		try {
			while (true) {
				// Create new file
				ReaderWriterSimple writer = new ReaderWriterSimple(counter + ".txt", IOType.WRITE);
				writer.writeInt(counter);
				writer.closeStream();
				
				// create read stream
				ReaderWriterSimple reader = new ReaderWriterSimple(counter + ".txt", IOType.READ);
				reader.readInt();
				streams.add(reader);
				
				counter++;
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				for (int i = 0; i < streams.size()-1; i++) {
					streams.get(i).closeStream();
					TestReadWriteSuite.deleteFile(i + ".txt");
				}
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}

	}

}
