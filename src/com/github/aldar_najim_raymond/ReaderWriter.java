package com.github.aldar_najim_raymond;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;

public class ReaderWriter {
	public void simpleRead(String file) {
		InputStream is;
		try {
			is = new FileInputStream(new File(file));
			DataInputStream ds = new DataInputStream(is);
			try {
				while (true) {
					System.out.println(ds.readInt());
				}
			} catch (EOFException e) {
				ds.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private OutputStream os;

	/*
	 * simpleWrite with n-integer random integers
	 */
	public void simpleWrite(String file, int integers) {
		this.createOutPutStream(file);
		for (int i = 0; i < integers; i++) {
			this.simpleWrite(os, UtilisationClass.IntToByteArray(UtilisationClass.randomNumber()));
		}
	}
	
	/*
	 * simpleWrite with n-BigInteger random integers
	 */
	public void simpleWrite(String file, BigInteger integers) {
		this.createOutPutStream(file);
		for (BigInteger i = BigInteger.valueOf(1); i.compareTo(integers) <= 0; i = i.add(BigInteger.ONE)){
			this.simpleWrite(os, UtilisationClass.IntToByteArray(UtilisationClass.randomNumber()));
		}
	}
	
	/*
	 * simpleWrite with n-ArrayList integers
	 */
	public void simpleWrite(String file, ArrayList<Integer> integers) {
		this.createOutPutStream(file);
		for (Integer i : integers){
			this.simpleWrite(os, UtilisationClass.IntToByteArray(i));
		}
	}

	/*
	 * 
	 */
	private void createOutPutStream(String file) {
		try {
			os = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * writing one block at a time
	 */
	private void simpleWrite(OutputStream os, byte[] data) {
		try {
			os.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
