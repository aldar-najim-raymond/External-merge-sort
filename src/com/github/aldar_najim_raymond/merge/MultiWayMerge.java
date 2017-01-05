package com.github.aldar_najim_raymond.merge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MultiWayMerge {

	static class ISsort implements Comparator<InputStream> {
		/*
		 * ascending sorting of InputStreams
		 */
		@Override
		public int compare(InputStream o1, InputStream o2) {
			if (o1.getCurrentInt() < o2.getCurrentInt()){
				return -1;
			} else if(o1.getCurrentInt() > o2.getCurrentInt()){
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static void doMultiWayMerge(int memoryBuffer, ArrayList<String> fileNames) {

		ISsort iss = new ISsort();
		PriorityQueue<InputStream> pq = new PriorityQueue<InputStream>(fileNames.size(), iss);

		for (String s : fileNames) {
			InputStream is = new InputStream(memoryBuffer);
			is.openStream(s);
			is.getNextInt();
			pq.offer(is);
		}

		while (pq.size() != 0) {
			System.out.println("size: " + pq.size() + ", " + pq.peek().getCurrentInt() + " " + pq.peek().getFileName());

			InputStream tmp = pq.remove();
			tmp.getNextInt();
			if (!tmp.isEOF()) {
				pq.offer(tmp);
			}
		}

	}

}
