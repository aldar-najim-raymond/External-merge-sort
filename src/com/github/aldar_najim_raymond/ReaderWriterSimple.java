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

public class ReaderWriterSimple {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private OutputStream os;

	/*
	 * simpleWrite with n-integer random integers
	 */
	public void simpleWrite(String file, int integers) {
		try {
			os = new FileOutputStream(file);
			for (int i = 0; i < integers; i++) {
				os.write(UtilisationClass.IntToByteArray(UtilisationClass.randomNumber()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * simpleWrite with n-BigInteger random integers
	 */
	public void simpleWrite(String file, BigInteger integers) {
		try {
			os = new FileOutputStream(file);
			for (BigInteger i = BigInteger.valueOf(1); i.compareTo(integers) <= 0; i = i.add(BigInteger.ONE)){
				os.write(UtilisationClass.IntToByteArray(UtilisationClass.randomNumber()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * simpleWrite with n-ArrayList integers
	 */
	public void simpleWrite(String file, ArrayList<Integer> integers) {
		try {
			os = new FileOutputStream(file);
			for (Integer i : integers){
				os.write(UtilisationClass.IntToByteArray(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
