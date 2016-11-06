// TODO: modify the write methods as they contain the same code
// TODO: read data with a buffer

package com.github.aldar_najim_raymond;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;

public class ReaderWriterMemoryBuffer {

	private OutputStream os;
	private int memoryBufferSize;

	/*
	 * constructors with/without buffer size specified
	 */
	public ReaderWriterMemoryBuffer() {
		this.memoryBufferSize = 4;
	}

	public ReaderWriterMemoryBuffer(int bufferSize) {
		this.builderSetMemoryBufferSize(bufferSize);
	}

	/*
	 * Set buffer size as builder pattern method
	 */
	public ReaderWriterMemoryBuffer builderSetMemoryBufferSize(int size) {
		if (size < 1) {
			throw new IllegalArgumentException("Memory buffer size must be larger than 0.");
		}
		/*
		 * only allow multiples of 4 to make the write/read methods easier ...
		 */
		else if ((size % 4) != 0) {
			throw new IllegalArgumentException("Buffer size must be multiple of 4");
		}
		this.memoryBufferSize = size;
		return this;
	}

	/*
	 * simple read
	 */
	public void read(String file) {
		InputStream is;
		try {
			is = new FileInputStream(new File(file));
			DataInputStream ds = new DataInputStream(is);
			try {
				while (true) {
					System.out.println(ds.readInt());
				}
			} catch (EOFException e) {
				ds.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * simple write with n-integer random integers
	 */
	public void write(String file, int integers) {
		byte[] buffer;
		int bufferCounter = 0;
		try {
			os = new FileOutputStream(file);
			buffer = new byte[this.memoryBufferSize];
			for (int i = 0; i < integers; i++) {

				byte[] number = UtilisationClass.IntToByteArray(UtilisationClass.randomNumber());

				System.arraycopy(number, 0, buffer, bufferCounter, 4);
				/*
				 * increase counter by 4 as one number is 4 bytes
				 */
				bufferCounter += 4;
				/*
				 * Buffer is full -> write data to file
				 */
				if (bufferCounter >= this.memoryBufferSize) {
					os.write(buffer);
					bufferCounter = 0;
				}
			}

			/*
			 * check if there is still data in the buffer not yet written to the
			 * file
			 */
			if (bufferCounter != 0) {
				os.write(buffer, 0, bufferCounter);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * simple write with n-BigInteger random integers
	 */
	public void write(String file, BigInteger integers) {
		byte[] buffer;
		int bufferCounter = 0;
		try {
			os = new FileOutputStream(file);
			buffer = new byte[this.memoryBufferSize];
			for (BigInteger i = BigInteger.valueOf(1); i.compareTo(integers) <= 0; i = i.add(BigInteger.ONE)) {
				
				byte[] number = UtilisationClass.IntToByteArray(UtilisationClass.randomNumber());

				System.arraycopy(number, 0, buffer, bufferCounter, 4);
				/*
				 * increase counter by 4 as one number is 4 bytes
				 */
				bufferCounter += 4;
				/*
				 * Buffer is full -> write data to file
				 */
				if (bufferCounter >= this.memoryBufferSize) {
					os.write(buffer);
					bufferCounter = 0;
				}
			}
			
			/*
			 * check if there is still data in the buffer not yet written to the
			 * file
			 */
			if (bufferCounter != 0) {
				os.write(buffer, 0, bufferCounter);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * simple write with n-ArrayList integers
	 */
	public void write(String file, ArrayList<Integer> integers) {
		byte[] buffer;
		int bufferCounter = 0;
		try {
			os = new FileOutputStream(file);
			buffer = new byte[this.memoryBufferSize];
			for (Integer i : integers) {
				
				byte[] number = UtilisationClass.IntToByteArray(i);

				System.arraycopy(number, 0, buffer, bufferCounter, 4);
				/*
				 * increase counter by 4 as one number is 4 bytes
				 */
				bufferCounter += 4;
				/*
				 * Buffer is full -> write data to file
				 */
				if (bufferCounter >= this.memoryBufferSize) {
					os.write(buffer);
					bufferCounter = 0;
				}
			}
			
			/*
			 * check if there is still data in the buffer not yet written to the
			 * file
			 */
			if (bufferCounter != 0) {
				os.write(buffer, 0, bufferCounter);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Buffer size get/set
	 */
	public void setMemoryBufferSize(int size) {
		this.builderSetMemoryBufferSize(size);
	}

	public int getMemoryBufferSize() {
		return this.memoryBufferSize;
	}
	
}
