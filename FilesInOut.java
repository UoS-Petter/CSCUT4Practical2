import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

	final static String INPUT_PATH = "/Users/Petter/Desktop/Test/test.txt";
	final static String OUTPUT_PATH = "/Users/Petter/Desktop/Test/testoutput.txt";

	public static void main(String[] args) {

		File inputFolder = new File(INPUT_PATH);
		File inputFile = new File(INPUT_PATH + "hello.txt");

		Reader inputProcess = new Reader(INPUT_PATH);

		if (inputProcess.tryLoadFile()) {
			while (inputProcess.hasNextLine()) {
				System.out.println(inputProcess.getNextLine());
			}
		} else {
			System.out.println(inputProcess.getFailReason());
		}

		// Replace this with statements to set the file name (input) and file name
		// (output).
		// Initially it will be easier to hardcode suitable file names. test.

		// Set up a new Scanner to read the input file.
		// Processing line by line would be sensible here.
		// Initially, echo the text to System.out to check you are reading correctly.
		// Then add code to modify the text to the output format.

		// Set up a new PrintWriter to write the output file.
		// Add suitable code into the above processing (because you need to do this line
		// by line also.
		// That is, read a line, write a line, loop.

		// Finally, add code to read the filenames as arguments from the command line.

	} // main

} // FilesInOut
