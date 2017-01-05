package com.github.aldar_najim_raymond.merge;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;

public class SingleFileMerge {
	/*
	 * Merging large file n into m sorted files Memory is split into 3 parts: -
	 * Input buffer - Output buffer - Integer Array which holds the read data
	 */
	public static ArrayList<String> mergeFilePartly(String fileName, int memory) {
		/*
		 * contains names of m sorted files
		 */
		
		if (memory % 12 != 0 || memory < 0){
			throw new IllegalArgumentException("Buffer size must be non negative multiple of 12");
		}
		
		ArrayList<String> sortedFiles = new ArrayList<String>();

		ReaderWriterMapped reader = new ReaderWriterMapped(fileName, IOType.READ, memory / 3);
		int splitNumber = 1;

		int numbers[] = new int[memory / 12];
		int counter = 0;
		try {
			while (true) {
				// fill the buffer
				numbers[counter] = reader.readInt();
				counter++;
				// buffer is full
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
		ReaderWriterMapped writer = new ReaderWriterMapped(fileName, IOType.WRITE, memory);
		Arrays.sort(numbers);
		try {
			for (int i = 0; i < numbers.length; i++) {
				writer.writeInt(numbers[i]);
			}
		} finally {
			writer.closeStream();
		}
	}

	// Check if the integers in the file are being sorten
	public static boolean isFileSorted(String fileName, int memory) {
		ReaderWriterMapped reader = new ReaderWriterMapped(fileName, IOType.READ, memory);

		try {
			int prev = reader.readInt();
			int curr;
			// read until EOF
			while (true) {
				curr = reader.readInt();
				if (prev > curr) {
					return false;
				}
				prev = curr;
			}
		} catch (EOFException e) {
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
