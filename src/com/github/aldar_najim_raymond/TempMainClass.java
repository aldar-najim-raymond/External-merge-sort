package com.github.aldar_najim_raymond;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.Verifier;

public class TempMainClass {

	public static void main(String[] args) {
		//new FileCreator("t1.txt").generateFile(10);
		//new FileCreator("a.txt").generateFileBig(new BigInteger("100000"));
		//System.out.println(Verifier.verifySorted("t1.txt"));
		
		ReaderWriterSimple rw = new ReaderWriterSimple();
		rw.simpleWrite("t1.txt",5);
		rw.simpleRead("t1.txt");
	}
}
