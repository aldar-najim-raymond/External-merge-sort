package com.github.aldar_najim_raymond;

import java.io.IOException;
import java.math.BigInteger;

public class TempMainClass {

	public static void main(String[] args) {


		ReaderWriterSimple rws = new ReaderWriterSimple("t1.txt", IOType.WRITE);
		for (int i =0; i < 10; i ++){
			try {
				rws.writeInt(11*i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ReaderWriterSimple rw = new ReaderWriterSimple("t1.txt", IOType.READ);
		for (int i = 0; i < 10; i++){
			try {
				System.out.println(rw.readInt());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
