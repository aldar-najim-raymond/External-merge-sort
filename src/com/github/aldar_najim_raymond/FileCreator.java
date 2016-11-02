package com.github.aldar_najim_raymond;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * FileCreator is used to create arbitrary large text files with random
 * generated integers
 */
public class FileCreator {

	private String filePath;
	private List<Integer> data;
	private Random random;

	/**
	 * @param file
	 *            Name and path of the file. Will override already existing
	 *            files with the same name
	 */
	public FileCreator(String file) {
		this.filePath = file;
		this.random = new Random();
	}

	/**
	 * Generates a file with n integer lines <br>
	 * <br>
	 * Example usage: <br>
	 * new FileCreator("t1.txt").generateFile(10);
	 * 
	 * @param lines
	 *            number of lines wanted specified by a integer value
	 */
	public FileCreator generateFile(int lines) {
		/*
		 * lines must be higher than 0
		 */
		if (lines < 0) {
			throw new IllegalArgumentException("Lines must be higher than 0.");
		}

		data = new ArrayList<Integer>();
		for (int i = 0; i < lines; i++) {
			data.add(this.randomNumber());
		}
		this.writeListToFile();
		return this;
	}

	/**
	 * Generates a file with many (BigInteger) lines <br>
	 * <br>
	 * Example usage: <br>
	 * new FileCreator("a.txt").generateFileBig(new
	 * BigInteger("9999999999999"));
	 * 
	 * @param lines
	 *            number of lines wanted given by a BigInteger object
	 */
	public FileCreator generateFileBig(BigInteger lines) {

		if (lines == null) {
			throw new IllegalArgumentException("Lines must not be null.");
		}
		BigInteger bi0 = BigInteger.valueOf(0);
		if (lines.compareTo(bi0) != 1) {
			throw new IllegalArgumentException("BigInteger value must be higher than 0.");
		}

		data = new ArrayList<Integer>();
		/*
		 * Iterating the BigIntger line number
		 */
		for (BigInteger i = BigInteger.valueOf(1); i.compareTo(lines) <= 0; i = i.add(BigInteger.ONE)) {
			data.add(this.randomNumber());
		}
		this.writeListToFile();
		return this;
	}

	/*
	 * Writes the generated List of integers to the specified file
	 */
	private void writeListToFile() {
		try {
			PrintWriter writer = new PrintWriter(this.filePath, "UTF-8");
			for (Integer i : data) {
				writer.println(i);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Returns a random number between Integer.MIN and Integer.MAX
	 */
	private int randomNumber() {
		int randomHigh = this.random.nextInt(Integer.MAX_VALUE);
		int randomLow = this.random.nextInt(Integer.MAX_VALUE);
		return randomHigh - randomLow;
	}
}
