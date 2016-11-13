package com.github.aldar_najim_raymond.readerWriter;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.aldar_najim_raymond.UtilisationClass;

public class ReaderWriterSimple extends AbstractReaderWriter {

	private OutputStream os;
	private InputStream is;
	private DataInputStream ds;

	public ReaderWriterSimple(String file, IOType type) {
		super(file,type);

		/*
		 * Initializing read streams
		 */
		if (this.getType() == IOType.READ) {
			try {
				is = new FileInputStream(new File(this.getFileName()));
				ds = new DataInputStream(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		/*
		 * 	Initializing write streams
		 */
		} else if (this.getType() == IOType.WRITE) {
			try {
				os = new FileOutputStream(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new UnsupportedOperationException(this.getType() + " not supported");
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

	@Override
	public void closeStream() throws IOException {
		if (this.getType() == IOType.READ) {
			ds.close();
		} else if (this.getType() == IOType.WRITE) {
			os.close();
		}
	}
}
