import java.io.*;
import java.util.*;

/**
 * 
 * Read & validate files from memory
 *
 */
public class Reader {

	// Constants for readability
	final private String FAIL_NOT_LOADED = "File not loaded.";
	final private String FAIL_IS_DIR = "Enter the path to a valid .txt file";
	final private String FAIL_NOT_EXIST = "Could not find file specified.";
	final private String FAIL_READING = "Error reading file.";

	final private String NO_FAIL = "File was loaded succesfully.";
	final public String NO_NEXT_LINE = "No next line to read.";

	private String inputPath; // Input path currently used
	private String failReason; // Reason why loading/reading failed

	private boolean loaded; // Is a file loaded

	private File inputFile; // File variable to store input file
	private Scanner inputScan; // Scanner to read line by line

	/**
	 * Reader constructor. Automatically attempts to load file specified
	 *
	 * @param inputPath Path to .txt file to read from
	 */
	public Reader(String inputPath) {
		setPath(inputPath);
		tryLoadFile();
	}

	/**
	 * Update/set the path to .txt to read from
	 *
	 * @param newPath Path to .txt file to read from
	 */
	public void setPath(String newPath) {
		this.inputPath = newPath;
		this.loaded = false;
		this.failReason = FAIL_NOT_LOADED;
	}

	/**
	 * Check if file is valid and set up a Scanner to read it
	 *
	 * @return true is successful, false otherwise
	 */
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

	/**
	 * Check if a file is read and if the scanner has input left to read
	 *
	 * @return true if there is input to read, false otherwise
	 */
	public boolean hasNextLine() {

		if (loaded && inputScan.hasNextLine()) {
			return true;
		}

		return false;
	}

	/**
	 * Check if there is input to read and return it.
	 *
	 * @return the next line to read, or error message otherwise
	 */
	public String getNextLine() {

		if (hasNextLine()) {
			return inputScan.nextLine();
		}

		else
			return NO_NEXT_LINE;
	}

	/**
	 * Is input loaded from path specified
	 *
	 * @return true or false
	 */
	public boolean isLoaded() {
		return this.loaded;
	}

	/**
	 * Check why loading failed, assuming it did
	 *
	 * @return a string with reason for failure
	 */
	public String getFailReason() {
		if (loaded = false) {
			return failReason;
		} else
			return NO_FAIL;
	}

	private void readFile() {

		if (isLoaded()) {
			try {

				inputScan = new Scanner(inputFile);

			} catch (IOException e) {

				loaded = false;
				failReason = FAIL_READING;
			}
		}
	}

}
