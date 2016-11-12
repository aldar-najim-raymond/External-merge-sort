package com.github.aldar_najim_raymond;

public abstract class AbstractReaderWriter implements ReaderWriterInterface {
	private String file;
	private IOType type;
	
	public AbstractReaderWriter(String file, IOType type){
		this.file = file;
		this.type = type;
	}
	
	/*
	 * Getters/Setters
	 */
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public IOType getType() {
		return type;
	}
	public void setType(IOType type) {
		this.type = type;
	}
}
