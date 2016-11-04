package com.github.aldar_najim_raymond;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.Verifier;

public class TempMainClass {

	public static void main(String[] args) {
		//new FileCreator("t1.txt").generateFile(10);
		//new FileCreator("a.txt").generateFileBig(new BigInteger("100000"));
		//System.out.println(Verifier.verifySorted("t1.txt"));
		
		ReaderWriterSimple rw_simple = new ReaderWriterSimple();
		ReaderWriterBuffered rw_buff = new ReaderWriterBuffered();
		
		long before, time_taken;
		
		before = System.currentTimeMillis();
		rw_simple.write("simple.dat", new BigInteger("10000000"));
		time_taken = System.currentTimeMillis() - before;
		System.out.println("Simple took : " + time_taken);
		
		before = System.currentTimeMillis();
		rw_buff.write("buffered.dat", new BigInteger("10000000"));
		time_taken = System.currentTimeMillis() - before;
		System.out.println("Simple took : " + time_taken);
	}
}
