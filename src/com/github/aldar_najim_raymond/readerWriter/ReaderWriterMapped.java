package com.github.aldar_najim_raymond.readerWriter;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ReaderWriterMapped extends AbstractReaderWriter {

	private File file;
	private RandomAccessFile randomAccessFile;
	private FileChannel fileChannel;
	private MappedByteBuffer buffer;
	private int bufferPosition;

	// BufferSize in terms of how many bytes to hold
	private int bufferSize;
	private long fileBytesLeft;
	private long mappedFilePosition;

	// Array in which integers will be stored before writing to memory
	private int[] memoryBuffer;

	public ReaderWriterMapped(String fileName, IOType type, int bufferSize) {
		super(fileName, type);

		if ((bufferSize % 4) != 0 || bufferSize < 0) {
			throw new IllegalArgumentException("Buffer size must be non negative multiple of 4");
		}
		
		this.file = new File(fileName);

		/*
		 * Initializing read buffer
		 */
		if (this.getType() == IOType.READ) {
			try {
				this.randomAccessFile = new RandomAccessFile(file, "r");
				this.fileChannel = randomAccessFile.getChannel();
				this.bufferSize = bufferSize;
				// we reduce the bufferSize when it is bigger than the file to
				// be read
				if (this.bufferSize > fileChannel.size()) {
					this.bufferSize = (int) fileChannel.size();
				}
				this.fileBytesLeft = fileChannel.size() - this.bufferSize;
				this.mappedFilePosition = 0;

				this.buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, this.mappedFilePosition, this.bufferSize);
				this.randomAccessFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.bufferPosition = 0;
			/*
			 * Initializing write buffer
			 */
		} else if (this.getType() == IOType.WRITE) {

			this.file.delete();
			this.bufferSize = bufferSize;
			this.mappedFilePosition = 0;
			this.memoryBuffer = new int[this.bufferSize / 4];
			this.bufferPosition = 0;

		} else {
			throw new UnsupportedOperationException(this.getType() + " not supported");
		}
	}

	@Override
	public int readInt() throws IOException {
		// Check if EOF has been reached
		if (this.bufferPosition >= this.bufferSize) {
			// map next part of the file
			if (this.fileBytesLeft > 0) {
				// jump to next starting point of mapped file
				this.mappedFilePosition += this.bufferSize;
				// is next mapped part smaller than the bufferSize?
				if (this.bufferSize > this.fileBytesLeft) {
					this.bufferSize = (int) this.fileBytesLeft;
				}
				this.fileBytesLeft -= this.bufferSize;

				this.randomAccessFile = new RandomAccessFile(file, "r");
				this.fileChannel = randomAccessFile.getChannel();
				this.buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, this.mappedFilePosition, this.bufferSize);
				this.randomAccessFile.close();

				this.bufferPosition = 0;
			} else {
				throw new EOFException("Mapped ReaderWriter has reached EOF.");
			}
		}
		this.bufferPosition += 4;
		return buffer.getInt();
	}

	@Override
	public void writeInt(int number) throws IOException {
		this.memoryBuffer[this.bufferPosition] = number;
		this.bufferPosition++;

		// Buffer is full -> write integers to file
		if (this.memoryBuffer.length <= this.bufferPosition) {
			this.randomAccessFile = new RandomAccessFile(file, "rw");
			this.fileChannel = this.randomAccessFile.getChannel();
			this.buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, this.mappedFilePosition, bufferSize);
			this.randomAccessFile.close();
			for (int i = 0; i < this.memoryBuffer.length; i++) {
				this.buffer.putInt(this.memoryBuffer[i]);
			}
			this.mappedFilePosition += bufferSize;
			this.bufferPosition = 0;
		}
	}

	@Override
	public void closeStream() throws IOException {
		//check if there are still integers in the buffer to write
		if (this.getType() == IOType.WRITE){
			if (this.bufferPosition > 0){
				this.randomAccessFile = new RandomAccessFile(file, "rw");
				this.fileChannel = this.randomAccessFile.getChannel();
				this.buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, this.mappedFilePosition, this.bufferPosition*4);
				this.randomAccessFile.close();
				for (int i = 0; i < this.bufferPosition; i++) {
					this.buffer.putInt(this.memoryBuffer[i]);
				}
			}
		}
		this.fileChannel.close();
	}
}
