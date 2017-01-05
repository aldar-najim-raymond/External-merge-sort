package com.github.aldar_najim_raymond.merge;

import java.io.IOException;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;

public class InputStream {

	private int bufferSize;
	private ReaderWriterMapped rwm;
	
	private int currentInt;
	private boolean eof;
	
	public InputStream(int bufferSize){
		this.bufferSize = bufferSize;
	}
	
	public void openStream(String fileName){
		rwm = new ReaderWriterMapped(fileName, IOType.READ, this.bufferSize);
		this.eof = false;
	}
	
	public int getCurrentInt(){
		return this.currentInt;
	}
	
	public void getNextInt(){
		try {
			this.currentInt = this.rwm.readInt();
		} catch (IOException e) {
			this.eof = true;
		}
	}
	
	public boolean isEOF(){
		return this.eof;
	}
	
	public void closeStream(){
		try {
			this.rwm.closeStream();
		} catch (IOException e) {
		}
	}
	
}
