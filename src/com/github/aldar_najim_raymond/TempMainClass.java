package com.github.aldar_najim_raymond;

import java.math.BigInteger;

import com.github.aldar_najim_raymond.test.Verifier;

public class TempMainClass {

	public static void main(String[] args) {
		//new FileCreator("t1.txt").generateFile(10);
		//new FileCreator("a.txt").generateFileBig(new BigInteger("100000"));
		//System.out.println(Verifier.verifySorted("t1.txt"));
		
		
		ReaderWriterMemoryBuffer rwmb = new ReaderWriterMemoryBuffer(12);
		rwmb.write("a.txt", new BigInteger("5"));
		rwmb.read("a.txt");
		
		/*
        Class<?>[] parameterTypes = {String.class};
        Object[] parameterValues = {new String("t1.txt")};
		long time = UtilisationClass.getRuntime("read", ReaderWriterBuffered.class, parameterTypes,parameterValues);
		System.out.println("time taken: " + time);
		*/

	}
}
