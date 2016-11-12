package com.github.aldar_najim_raymond;

import java.io.IOException;

public class TempMainClass {

	public static void main(String[] args) {

		ReaderWriterBuffered rwb = new ReaderWriterBuffered("t1.txt", IOType.WRITE);

		try {
			for (int i = 0; i < 10; i++) {
				rwb.writeInt(i * 12);
			}
			rwb.closeStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReaderWriterBuffered rwb2 = new ReaderWriterBuffered("t1.txt", IOType.READ);

		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(rwb2.readInt());
			}
			rwb2.closeStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
