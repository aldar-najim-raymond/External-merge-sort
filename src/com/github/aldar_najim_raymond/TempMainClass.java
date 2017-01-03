package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;


public class TempMainClass {

	public static void main(String[] args) throws Exception {

        //Create file object
        File file = new File("a3.txt");
         
        //Get file channel in readonly mode
        FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
         
        //Get direct byte buffer access using channel.map() operation
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
         
        // the buffer now reads the file as if it were loaded in memory. 
        System.out.println(buffer.isLoaded());  //prints false
        System.out.println(buffer.capacity());  //Get the size based on content size of file
        System.out.println(buffer.limit()/4);
        //You can read the file from this buffer the way you like.
        for (int i = 0; i < buffer.limit()/4; i++)
        {
            System.out.println(i+ " " + buffer.getInt()); //Print the content of file
        }
		/*
		int integers = 10;

		ReaderWriterMapped writer = new ReaderWriterMapped("asd.txt", IOType.WRITE, integers * 4 * 8 * 8);
		ReaderWriterMapped reader = new ReaderWriterMapped("asd.txt", IOType.READ);

		try {
			for (int i = 0; i < integers; i++) {
				writer.writeInt(i);
			}
			writer.closeStream();
			try {
				while(true) {
					System.out.println(reader.readInt());
				}
			} catch(EOFException ex) {
				System.out.println(ex.toString());
				reader.closeStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}

}
