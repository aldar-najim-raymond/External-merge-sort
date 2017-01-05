package com.github.aldar_najim_raymond.merge;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.test.TestReadWriteSuite;

public class MultiWayMerge {

	static class ISsort implements Comparator<InputStream> {
		/*
		 * ascending sorting of InputStreams
		 */
		@Override
		public int compare(InputStream o1, InputStream o2) {
			if (o1.getCurrentInt() < o2.getCurrentInt()) {
				return -1;
			} else if (o1.getCurrentInt() > o2.getCurrentInt()) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static void startMultiWayMerge(int max_memory, int d_max_streams, String initialFileName) {

		int singleMemory = max_memory - (max_memory % 12);
		if (max_memory < 12) {
			System.out.println("Need at least 12 bytes for the initial sorting phase");
			max_memory = 12;
		}
		List<String> files = SingleFileMerge.mergeFilePartly(initialFileName, singleMemory);
		System.out.println("1. stage mem: " + singleMemory);

		// check if max_memory is okay with d_max_streams, i.e. 20 memory with
		// 30 streams
		if ((long) max_memory / (long) d_max_streams < 4) {
			int new_d_streams = 0;
			while ((new_d_streams) * 4 < max_memory) {
				new_d_streams++;
			}
			System.out.println(
					"Need at least 4 bytes for each stream, changed " + d_max_streams + " streams to " + new_d_streams);
			d_max_streams = new_d_streams;
		}

		// do the multi merge until there is only one file left
		while (true) {
			// take maximal d files
			List<String> tmpFiles = files.subList(0, Math.min(d_max_streams, files.size()));

			System.out.println("files :" + files.size() + ", tmpFiles: " + tmpFiles.size());

			String merged = tmpFiles.get(0) + ".merged";

			int mergeMemory = max_memory - max_memory % ((tmpFiles.size() + 1) * 4);
			doMultiWayMerge(mergeMemory, tmpFiles, merged);

			System.out.println("merge mem: " + mergeMemory);

			// delete files which have been merged to larger file
			for (int i = 0; i < Math.min(d_max_streams, files.size()); i++) {
				TestReadWriteSuite.deleteFile(files.get(i));
			}
			// delete reference to merged miles
			files = files.subList(Math.min(d_max_streams, files.size()), files.size());

			// add reference to larger merged file
			files.add(merged);
			if (files.size() < 2) {
				break;
			}
		}
		System.out.println("all sorted to " + files.get(0));
		System.out.println(SingleFileMerge.isFileSorted(files.get(0), 65536));
	}

	private static void doMultiWayMerge(int memoryBuffer, List<String> fileNames, String outputName) {

		// limit input memory to 65536 and give the rest to the writer
		int inputStreamMemory = memoryBuffer / (fileNames.size() + 1);
		if (inputStreamMemory > 65536) {
			inputStreamMemory = 65536;
		}
		int outputStreamMemory = memoryBuffer - (inputStreamMemory * fileNames.size());

		ISsort iss = new ISsort();
		PriorityQueue<InputStream> pq = new PriorityQueue<InputStream>(fileNames.size(), iss);

		for (String s : fileNames) {
			InputStream is = new InputStream(inputStreamMemory);
			is.openStream(s);
			is.getNextInt();
			pq.offer(is);
		}

		// Output stream
		ReaderWriterMapped writer = new ReaderWriterMapped(outputName, IOType.WRITE, outputStreamMemory);

		try {
			while (pq.size() != 0) {
				// Write lowest integer in queue to file
				writer.writeInt(pq.peek().getCurrentInt());

				InputStream tmp = pq.remove();
				tmp.getNextInt();
				if (!tmp.isEOF()) {
					pq.offer(tmp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.closeStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
