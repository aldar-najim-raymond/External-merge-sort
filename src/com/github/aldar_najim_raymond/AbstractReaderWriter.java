package com.github.aldar_najim_raymond;

public abstract class AbstractReaderWriter implements ReaderWriterInterface {
	private String fileName;
	private IOType type;
	
	public AbstractReaderWriter(String fileName, IOType type){
		this.fileName = fileName;
		this.type = type;
	}
	
	/*
	 * Getters/Setters
	 */
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public IOType getType() {
		return type;
	}
	public void setType(IOType type) {
		this.type = type;
	}
}
