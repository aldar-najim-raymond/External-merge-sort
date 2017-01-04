package com.github.aldar_najim_raymond;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import com.github.aldar_najim_raymond.readerWriter.IOType;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterBuffered;
import com.github.aldar_najim_raymond.readerWriter.ReaderWriterMapped;
import com.github.aldar_najim_raymond.test.TestWriteSpeed;


public class TempMainClass {

	public static void main(String[] args) throws Exception {

		//TestWriteSpeed.testReaderWriterBuffered_Write("test.txt", 10, 1);
		
		// Create file object
		/*
        File file = new File("testwrite.txt");
         
        //Delete the file; we will create a new file
        file.delete();
 
        // Get file channel in readonly mode
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
 
        // Get direct byte buffer access using channel.map() operation
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4);
        buffer.putInt(1);
        buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 4, 4);
        buffer.putInt(1);*/

		
		/*
        File file = new File("test.txt");
        //Get file channel in readonly mode
        FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
         
        //Get direct byte buffer access using channel.map() operation
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, fileChannel.size()/2, fileChannel.size()/2);
         
        // the buffer now reads the file as if it were loaded in memory. 
        System.out.println(buffer.isLoaded());  //prints false
        System.out.println("capacity. " + buffer.capacity());  //Get the size based on content size of file
        System.out.println("limit: " + buffer.limit()/4);
        //You can read the file from this buffer the way you like.
        for (int i = 0; i < buffer.limit()/4; i++)
        {
            System.out.println(i+ " " + buffer.getInt()); //Print the content of file
        }*/
        
		
		
		int integers = 10;
		ReaderWriterMapped writer = new ReaderWriterMapped("testwrite1.txt", IOType.WRITE, 200);

		try {

			for (int i = 0; i < integers; i++) {
				writer.writeInt(i);
			}
			writer.closeStream();
			
			ReaderWriterMapped reader = new ReaderWriterMapped("testwrite1.txt", IOType.READ, 200);

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
		}
	}
}
