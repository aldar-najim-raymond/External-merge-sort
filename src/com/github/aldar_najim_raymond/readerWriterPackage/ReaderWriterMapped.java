package com.github.aldar_najim_raymond.readerWriterPackage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import com.github.aldar_najim_raymond.UtilisationClass;

public class ReaderWriterMapped extends AbstractReaderWriter {

	private File file;
	private RandomAccessFile randomAccessFile;
	private FileChannel fileChannel;
	private MappedByteBuffer buffer;

	public ReaderWriterMapped(String fileName, IOType type) {
		this(fileName, type, 0);
		if (type == IOType.WRITE) {
			throw new IllegalArgumentException(
					"Must specify how much data will be written. For one integer, the buffer size is 4 * 8 * 8 ");
		}
	}

	public ReaderWriterMapped(String fileName, IOType type, int bufferSize) {
		super(fileName, type);

		this.file = new File(fileName);

		/*
		 * Initializing read buffer
		 */
		if (this.getType() == IOType.READ) {
			try {
				this.randomAccessFile = new RandomAccessFile(file, "r");
				this.fileChannel = randomAccessFile.getChannel();
				this.buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
				this.randomAccessFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*
			 * Initializing write buffer
			 */
		} else if (this.getType() == IOType.WRITE) {
			try {
				this.file.delete();
				this.randomAccessFile = new RandomAccessFile(file, "rw");
				this.fileChannel = this.randomAccessFile.getChannel();
				this.buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, bufferSize);
				this.randomAccessFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new UnsupportedOperationException(this.getType() + " not supported");
		}
	}

	@Override
	public int readInt() throws IOException {
		return buffer.getInt();
	}

	@Override
	public void writeInt(int number) throws IOException {
		this.buffer.put(UtilisationClass.IntToByteArray(number));
	}

	@Override
	public void closeStream() throws IOException {
		this.fileChannel.close();
	}
}
