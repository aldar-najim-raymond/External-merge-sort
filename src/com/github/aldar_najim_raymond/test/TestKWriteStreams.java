package com.github.aldar_najim_raymond.test;

import java.io.IOException;
import java.util.ArrayList;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterSimple;

public class TestKWriteStreams {

	/*
	 * Testing how many simultaneously opened write streams are supported
	 */
	public static void main(String[] args) {
		int counter = 0;

		ArrayList<ReaderWriterSimple> streams = new ArrayList<ReaderWriterSimple>();

		try {
			while (true) {
				// Create new file
				ReaderWriterSimple rws = new ReaderWriterSimple(counter + ".txt", IOType.WRITE);
				streams.add(rws);

				streams.get(counter).writeInt(counter);
				
				counter++;
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				for (int i = 0; i < streams.size(); i++) {
					streams.get(i).closeStream();
					TestReadWriteSuite.deleteFile(i + ".txt");
				}
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}

}
