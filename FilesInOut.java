import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 * @author Petter Sandas 2021
 *
 */
public class FilesInOut {

	final static String INPUT_PATH = "/Users/Petter/Desktop/Test/test.txt";
	final static String OUTPUT_PATH = "/Users/Petter/Desktop/Test/testoutput.txt";

	public static void main(String[] args) {

		Reader inputProcess = new Reader(INPUT_PATH);
		Writer outputProcess = new Writer(OUTPUT_PATH);

		if (inputProcess.isLoaded() && outputProcess.isLoaded()) {
			while (inputProcess.hasNextLine()) {
				String nextThing = inputProcess.getNextLine();
				//System.out.println(nextThing);
				outputProcess.addLine(nextThing);
			}
			outputProcess.commitChanges();
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
