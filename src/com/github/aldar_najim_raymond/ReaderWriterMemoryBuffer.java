// TODO: modify the write methods as they contain the same code

package com.github.aldar_najim_raymond;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReaderWriterMemoryBuffer extends AbstractReaderWriter {

	public static int defaultMemoryBufferSize = 4;

	private InputStream is;
	private DataInputStream ds;

	private OutputStream os;

	/*
	 * byte[] buffer
	 */
	private byte[] memoryBuffer;
	/*
	 * Points to the current value in the buffer
	 */
	private int bufferPointer;
	private int bufferLength;

	public ReaderWriterMemoryBuffer(String file, IOType type) {
		this(file, type, ReaderWriterMemoryBuffer.defaultMemoryBufferSize);
	}

	public ReaderWriterMemoryBuffer(String file, IOType type, int bufferSize) {
		super(file, type);
		this.setMemoryBufferSize(bufferSize);

		/*
		 * Initializing read streams
		 */
		if (this.getType() == IOType.READ) {
			try {
				is = new FileInputStream(new File(file));
				ds = new DataInputStream(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.bufferLength = -2;
			/*
			 * Initializing write streams
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
		this.bufferPointer = 0;
	}

	/*
	 * Set buffer size
	 */
	private void setMemoryBufferSize(int size) {
		if (size < 1) {
			throw new IllegalArgumentException("Memory buffer size must be larger than 0.");
		}
		/*
		 * only allow multiples of 4 to make the write/read methods easier ...
		 */
		else if ((size % 4) != 0) {
			throw new IllegalArgumentException("Buffer size must be multiple of 4");
		}
		this.memoryBuffer = new byte[size];
	}


	/*
	 * simple write with n-integer random integers
	 */
	
	public void write(String file, int integers) {
		byte[] buffer;
		int bufferCounter = 0;
		try {
			os = new FileOutputStream(file);
			//buffer = new byte[this.memoryBufferSize];
			for (int i = 0; i < integers; i++) {

				byte[] number = UtilisationClass.IntToByteArray(UtilisationClass.randomNumber());

				//System.arraycopy(number, 0, buffer, bufferCounter, 4);
				/*
				 * increase counter by 4 as one number is 4 bytes
				 */
				bufferCounter += 4;
				/*
				 * Buffer is full -> write data to file
				 */
				/*
				if (bufferCounter >= this.memoryBufferSize) {
					os.write(buffer);
					bufferCounter = 0;
				}*/
			}

			/*
			 * check if there is still data in the buffer not yet written to the
			 * file
			 */
			/*
			if (bufferCounter != 0) {
				os.write(buffer, 0, bufferCounter);
			}*/

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Read the input stream with a memory buffer
	 */
	@Override
	public int readInt() throws IOException {
		/*
		 * First read
		 */
		if (this.bufferLength == -2) {
			this.bufferLength = ds.read(this.memoryBuffer);
		} 
		/*
		 * Read n memory bytes from stream when the pointer is larger than the buffer
		 */
		else if (this.bufferPointer >= this.memoryBuffer.length){
			this.bufferLength = ds.read(this.memoryBuffer);
			this.bufferPointer = 0;
		}
		if (this.bufferLength == -1) {
			throw new EOFException();
		}
		/*
		 * Copy integer from the memory buffer at the pointer position
		 */
		byte[] number = new byte[4];
		System.arraycopy(this.memoryBuffer, this.bufferPointer, number, 0, 4);
		this.bufferPointer += 4;
		return UtilisationClass.ByteArrayToInt(number);
	}

	@Override
	public void writeInt(int number) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeStream() throws IOException {
		if (this.getType() == IOType.READ) {
			ds.close();
		} else if (this.getType() == IOType.WRITE) {
			//bos.close();
		}

	}

}
