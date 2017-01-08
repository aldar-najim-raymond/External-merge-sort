package com.github.aldar_najim_raymond.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.github.aldar_najim_raymond.merge.FileCreator;
import com.github.aldar_najim_raymond.merge.MultiWayMerge;

public class TestMergeSort {

	public static void main(String[] args) {
		List<BigInteger> fileSizes = new ArrayList<BigInteger>();
		List<Integer> memorySizes = new ArrayList<Integer>();
		List<Integer> d_streams = new ArrayList<Integer>();

		/*
		 * On what values we are testing on
		 */
		//fileSizes.add(new BigInteger("250"));
		fileSizes.add(new BigInteger("2500"));
		//fileSizes.add(new BigInteger("25000"));
		//fileSizes.add(new BigInteger("250000"));
		//fileSizes.add(new BigInteger("2500000"));
		//fileSizes.add(new BigInteger("25000000"));
		//fileSizes.add(new BigInteger("250000000"));
		
		//memorySizes.add(100);
		//memorySizes.add(1000);
		//memorySizes.add(10000);
		memorySizes.add(393216); // 11*32768 + 32768
		//memorySizes.add(100000);
		//memorySizes.add(1000000);
		//memorySizes.add(10000000);
		//memorySizes.add(100000000);

		//d_streams.add(2);
		//d_streams.add(5);
		//d_streams.add(10);
		//d_streams.add(11);
		//d_streams.add(15);
		//d_streams.add(20);
		d_streams.add(30);
		//d_streams.add(50);
		//d_streams.add(100);
		//d_streams.add(1000);
		
		for (BigInteger fileSize : fileSizes){
			// create the file
			for (Integer memorySize : memorySizes){
				for (Integer stream_nr : d_streams){
					FileCreator.createRandomFile("mergeTest.txt", fileSize, 65536);
					long before = System.nanoTime();
					MultiWayMerge.startMultiWayMerge(memorySize, stream_nr, "mergeTest.txt");
					long after = (System.nanoTime() - before);
					System.out.println(fileSize.toString() + " " + memorySize + " " + stream_nr + 
							" " + after);
				}
			}
		}
		

	}

}
