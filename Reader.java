import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

/**
 * 
 * Read & validate files from memory
 *
 */
public class Reader {

	final private String FAIL_NOT_LOADED = "File not loaded.";
	final private String FAIL_IS_DIR = "Enter the path to a valid .txt file";
	final private String FAIL_NOT_EXIST = "Could not find file specified.";
	final private String FAIL_READING = "Error reading file.";

	private String inputPath;
	private boolean loaded;
	private boolean read;
	private String failReason;

	private File inputFile;
	private Scanner inputScan;

	public Reader(String inputPath) {
		setPath(inputPath);
	}

	public void setPath(String newPath) {
		this.inputPath = newPath;
		this.loaded = false;
		this.failReason = FAIL_NOT_LOADED;
	}

	public boolean tryLoadFile() {

		inputFile = new File(inputPath);
		loaded = true;

		if (!inputFile.exists()) {
			loaded = false;
			failReason = FAIL_NOT_EXIST;
		}

		else if (inputFile.isDirectory()) {
			loaded = false;
			failReason = FAIL_IS_DIR;
		}

		else {
			readFile();
		}

		return loaded;
	}

	private boolean readFile() {

		if (isLoaded()) {
			try {

				inputScan = new Scanner(inputFile);
				read = true;
				return true;

			} catch (IOException e) {

				loaded = false;
				read = false;
				failReason = FAIL_READING;
			}
		}
		return false;
	}

	public boolean hasNextLine() {

		if (read && loaded && inputScan.hasNextLine()) {
			return true;
		}

		return false;
	}

	/**
	 * Example
	 *
	 * @param ex desc
	 * @return example
	 */
	public String getNextLine() {

		if (hasNextLine()) {
			return inputScan.nextLine();
		}

		else
			return "No next line to read.";
	}

	public boolean isLoaded() {
		return this.loaded;
	}

	public String getFailReason() {
		return failReason;
	}

}
