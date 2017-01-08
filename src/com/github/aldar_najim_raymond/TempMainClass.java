package com.github.aldar_najim_raymond;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.merge.FileCreator;
import com.github.aldar_najim_raymond.merge.MultiWayMerge;
import com.github.aldar_najim_raymond.merge.SingleFileMerge;


public class TempMainClass {
	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(SingleFileMerge.isFileSorted("1.txt.33.merged", 65536));
		
		BigInteger integers = new BigInteger("2500000");
		FileCreator.createRandomFile("1.txt", integers, 65536);
		MultiWayMerge.startMultiWayMerge(100000, 10, "1.txt");
		
	}
}
