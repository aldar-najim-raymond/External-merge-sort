package com.github.aldar_najim_raymond;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.Verifier;

public class TempMainClass {

	public static void main(String[] args) {
		// new FileCreator("t1.txt").generateFile(10);
		// new FileCreator("a.txt").generateFileBig(new BigInteger("100000"));
		// System.out.println(Verifier.verifySorted("t1.txt"));

		/*
		 * ReaderWriterMemoryBuffer rwmb = new ReaderWriterMemoryBuffer(400);
		 * ReaderWriterSimple rws = new ReaderWriterSimple();
		 * rwmb.write("a.txt", new BigInteger("5")); rws.read("a.txt");
		 * System.out.print('\n'); rwmb.read("a.txt");
		 */

		ReaderWriterMemoryBuffer rwmb = new ReaderWriterMemoryBuffer(400);
		long before, time;
		before = System.currentTimeMillis();
		rwmb.write("t1.txt", 1000000);

		time = System.currentTimeMillis() - before;
		System.out.println("time taken: " + time);

		ReaderWriterSimple rws = new ReaderWriterSimple();
		before = System.currentTimeMillis();
		rws.write("t2.txt", 1000000);

		time = System.currentTimeMillis() - before;
		System.out.println("time taken: " + time);
	}
}
