package com.github.aldar_najim_raymond.readerWriter;

import java.io.IOException;

public interface ReaderWriterInterface {
	public int readInt() throws IOException;
	public void writeInt(int number) throws IOException;
	public void closeStream() throws IOException;
}
