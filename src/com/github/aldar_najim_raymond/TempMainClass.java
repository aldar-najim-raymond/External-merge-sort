package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import com.github.aldar_najim_raymond.merge.FileCreator;
import com.github.aldar_najim_raymond.merge.InputStream;
import com.github.aldar_najim_raymond.merge.MultiWayMerge;
import com.github.aldar_najim_raymond.merge.SingleFileMerge;
import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.test.TestWriteSpeed;


public class TempMainClass {
	
	

	/*
	static class PQsort implements Comparator<Integer> {
		 
		public int compare(Integer one, Integer two) {
			return two - one;

		}
	}*/
	
	public static void main(String[] args) throws Exception {
		
		BigInteger integers = new BigInteger("100000");
		FileCreator.createRandomFile("1.txt", integers, 65536);

		MultiWayMerge.startMultiWayMerge(10000, 10, "1.txt");
		
		/*
		ArrayList<String> files = SingleFileMerge.mergeFilePartly("1.txt", 36000);
		
		System.out.println("start merging of " + files.size() + " length");
		
		MultiWayMerge.doMultiWayMerge(65536, files, "merged.txt");
		
		System.out.println(SingleFileMerge.isFileSorted("merged.txt", 65536));
		*/
		/*
		BigInteger integers = new BigInteger("250000");
		
		FileCreator.createRandomFile("test.txt", integers, 65536);
		
		InputStream is = new InputStream(65536);
		is.openStream("test.txt");
		
		while(!is.isEOF()){
			is.getNextInt();
			System.out.println(is.getCurrentInt());
		}
		is.closeStream();
		*/
		/*
		int[] ia = { 1, 10, 5, 3, 4, 7, 6, 9, 8 };
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
 
		// use offer() method to add elements to the PriorityQueue pq1
		for (int x : ia) {
			pq1.offer(x);
		}
 
		System.out.println("pq1: " + pq1);
		while (pq1.size() != 0)
        {
            System.out.println(pq1.remove());
        }
 
		PQsort pqs = new PQsort();
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(10, pqs);
		// In this particular case, we can simply use Collections.reverseOrder()
		// instead of self-defined comparator
		for (int x : ia) {
			pq2.offer(x);
		}
 
		System.out.println("pq2: " + pq2);
 
		// print size
		System.out.println("size: " + pq2.size());
		// return highest priority element in the queue without removing it
		System.out.println("peek: " + pq2.peek());
		// print size
		System.out.println("size: " + pq2.size());
		// return highest priority element and removes it from the queue
		System.out.println("poll: " + pq2.poll());
		// print size
		System.out.println("size: " + pq2.size());
 
		System.out.print("pq2: " + pq2);
		*/
	}
}
