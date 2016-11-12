package com.github.aldar_najim_raymond;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReaderWriterSimple implements ReaderWriterInterface {

	private String file;
	private IOType type;

	private OutputStream os;
	private InputStream is;
	private DataInputStream ds;

	public ReaderWriterSimple(String file, IOType type) {
		this.file = file;
		this.type = type;

		/*
		 * Initializing read streams
		 */
		if (this.type == IOType.READ) {
			try {
				is = new FileInputStream(new File(this.file));
				ds = new DataInputStream(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		/*
		 * 	Initializing write streams
		 */
		} else if (this.type == IOType.WRITE) {
			try {
				os = new FileOutputStream(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new UnsupportedOperationException(this.type + " not supported");
		}
	}

	@Override
	public int readInt() throws IOException {
		return this.ds.readInt();
	}

	@Override
	public void writeInt(int number) throws IOException {
		os.write(UtilisationClass.IntToByteArray(number));

	}
}
