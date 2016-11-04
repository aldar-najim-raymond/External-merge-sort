package com.github.aldar_najim_raymond;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;

public class ReaderWriterBuffered {

	private OutputStream os;
	private BufferedOutputStream bos;
	
	/*
	 * buffered read
	 */
	public void read(String file) {
		InputStream is;
		try {
			is = new FileInputStream(new File(file));
			BufferedInputStream bis = new BufferedInputStream( is );
			DataInputStream ds = new DataInputStream(bis);
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

	/*
	 * buffered write with a buffer
	 */
	public void write(String file, int integers) {
		try {
			os = new FileOutputStream(file);
			bos = new BufferedOutputStream(os);
			for (int i = 0; i < integers; i++) {
				bos.write(UtilisationClass.IntToByteArray(UtilisationClass
						.randomNumber()));
			}
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * buffered write with n-BigInteger random integers
	 */
	public void write(String file, BigInteger integers) {
		try {
			os = new FileOutputStream(file);
			bos = new BufferedOutputStream(os);
			for (BigInteger i = BigInteger.valueOf(1); i.compareTo(integers) <= 0; i = i
					.add(BigInteger.ONE)) {
				bos.write(UtilisationClass.IntToByteArray(UtilisationClass
						.randomNumber()));
			}
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * buffered write with n-ArrayList integers
	 */
	public void write(String file, ArrayList<Integer> integers) {
		try {
			os = new FileOutputStream(file);
			bos = new BufferedOutputStream(os);
			for (Integer i : integers) {
				bos.write(UtilisationClass.IntToByteArray(i));
			}
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
