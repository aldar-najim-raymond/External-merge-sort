package com.github.aldar_najim_raymond.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Verifier {
	/*
	 * Verify if the values in a file are sorted
	 */
	public static boolean verifySorted(String filePath){
		try (Scanner file = new Scanner(new File(filePath))){
			
			int prev,curr;
			prev = Integer.parseInt(file.next());
			
			while(file.hasNext()){
				curr = Integer.parseInt(file.next());
				if (curr < prev) {
					return false;
				}
				prev = curr;
			}
			
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
}
