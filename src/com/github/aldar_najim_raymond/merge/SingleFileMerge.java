package com.github.aldar_najim_raymond.merge;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMemoryBuffer;

public class SingleFileMerge {
	/*
	 * Merging large file n into m sorted files
	 */
	public static ArrayList<String> mergeFilePartly(String fileName, int memory) {
		/*
		 * contains names of m sorted files
		 */
		ArrayList<String> sortedFiles = new ArrayList<String>();

		ReaderWriterMemoryBuffer reader = new ReaderWriterMemoryBuffer(fileName, IOType.READ, memory / 3);
		int splitNumber = 1;

		int numbers[] = new int[memory / 12];
		int counter = 0;
		try {
			while (true) {
				numbers[counter] = reader.readInt();
				counter++;
				if (counter == numbers.length - 1) {
					counter = 0;
					sortAndWriteToFile(fileName + "." + splitNumber + ".sorted", numbers, memory / 3);
					sortedFiles.add(fileName + "." + splitNumber + ".sorted");
					splitNumber++;
				}
			}
		} catch (EOFException e) {
			/*
			 * still data in memory not written to disk yet
			 */
			if (counter != 0) {
				/*
				 * ignore numbers from previous round
				 */
				int tmp[] = new int[counter];
				System.arraycopy(numbers, 0, tmp, 0, counter);
				try {
					sortAndWriteToFile(fileName + "." + splitNumber + ".sorted", tmp, memory / 3);
					sortedFiles.add(fileName + "." + splitNumber + ".sorted");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (IOException e) {

		}

		return sortedFiles;
	}

	private static void sortAndWriteToFile(String fileName, int[] numbers, int memory) throws IOException {
		ReaderWriterMemoryBuffer writer = new ReaderWriterMemoryBuffer(fileName, IOType.WRITE, memory);
		Arrays.sort(numbers);
		try {
			for (int i = 0; i < numbers.length; i++) {
				writer.writeInt(numbers[i]);
			}
		} finally {
			writer.closeStream();
		}
	}
}
