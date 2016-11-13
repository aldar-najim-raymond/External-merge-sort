package com.github.aldar_najim_raymond;

import java.io.IOException;

public class TempMainClass {

	public static void main(String[] args) {

		ReaderWriterMapped rwb = new ReaderWriterMapped("t1.txt", IOType.WRITE,4*100*8*8);
		try {
			for (int i = 0; i < 100; i++) {
				rwb.writeInt(i);
			}
			rwb.closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ReaderWriterMapped rwm = new ReaderWriterMapped("t1.txt", IOType.READ);
		try {
			for (int i = 0; i < 100; i++) {
				System.out.println(rwm.readInt());
			}
			rwb.closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
