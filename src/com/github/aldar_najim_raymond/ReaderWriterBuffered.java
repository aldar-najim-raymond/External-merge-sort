package com.github.aldar_najim_raymond;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReaderWriterBuffered extends AbstractReaderWriter{

	private InputStream is;
	private BufferedInputStream bis;
	private DataInputStream ds;
	
	private OutputStream os;
	private BufferedOutputStream bos;
	
	public ReaderWriterBuffered(String file, IOType type) {
		super(file, type);
		
		/*
		 * Initializing read streams
		 */
		if (this.getType() == IOType.READ) {
			try {
				is = new FileInputStream(new File(file));
				bis = new BufferedInputStream( is );
				ds = new DataInputStream(bis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		/*
		 * 	Initializing write streams
		 */
		} else if (this.getType() == IOType.WRITE) {
			try {
				os = new FileOutputStream(file);
				bos = new BufferedOutputStream(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new UnsupportedOperationException(this.getType() + " not supported");
		}
	}

	@Override
	public int readInt() throws IOException {
		return ds.readInt();
	}

	@Override
	public void writeInt(int number) throws IOException {
		bos.write(UtilisationClass.IntToByteArray(number));
	}
	
	@Override
	public void closeStream() throws IOException {
		if (this.getType() == IOType.READ) {
			ds.close();
		} else if (this.getType() == IOType.WRITE) {
			bos.close();
		}
	}
}
