import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 
 * Write files into memory
 *
 */
public class Writer {

	// Constants for readability
	final private String FAIL_NOT_LOADED = "File not loaded.";
	final private String FAIL_IS_DIR = "Enter the path to a valid .txt file";
	final private String FAIL_EXIST = "File already exists.";
	final private String PARENT_NOT_EXIST = "Target directory does not exist.";
	final private String FAIL_READING = "Error reading file.";
	final private String NO_FAIL = "File was loaded succesfully.";

	private String outputPath; // path to write to
	private String failReason; // store reason why reading failed

	private boolean loaded; // has a file been loaded

	private File outputFile; // File variable to store output file

	private PrintWriter writer; // printwriter to write to file

	/**
	 * Writer constructor
	 *
	 * @param outputPath Path to .txt file to read from
	 */
	public Writer(String outputPath) {
		setPath(outputPath);
		tryLoadFile();
	}

	/**
	 * Update/set the path to .txt to write to
	 *
	 * @param newPath Path to .txt file to write to
	 */
	public void setPath(String newPath) {
		this.outputPath = newPath;
		this.loaded = false;
		this.failReason = FAIL_NOT_LOADED;
	}

	/**
	 * Check if file is valid and initalize a PrintWriter to write to it
	 *
	 * @return true is successful, false otherwise
	 */
	public boolean tryLoadFile() {

		outputFile = new File(outputPath);
		loaded = true;

		if (outputFile.exists()) {

			// check if want to overwrite
			if (!overwrite()) {
				loaded = false;
				failReason = FAIL_EXIST;
			}

		}

		if (outputFile.getParent() == null) {

			loaded = false;
			failReason = PARENT_NOT_EXIST;

		}

		if (outputFile.isDirectory()) {
			loaded = false;
			failReason = FAIL_IS_DIR;
		}

		if (loaded) {
			open();
		}

		return loaded;
	}

	/**
	 * Add a line to buffer
	 *
	 * @return true is successful, false otherwise
	 */
	public boolean addLine(String toWrite) {

		if (loaded) {
			writer.write(toWrite + "\n");
			return true;
		}
		return false;

	}

	/**
	 * Write buffer to file
	 */
	public void commitChanges() {

		writer.flush();
		writer.close();

		loaded = false;
		failReason = FAIL_NOT_LOADED;
	}

	/**
	 * Check why loading failed, assuming it did
	 *
	 * @return a string with reason for failure
	 */
	public String getFailReason() {
		if (!loaded) {
			return failReason;
		} else
			return NO_FAIL;
	}

	/**
	 * Is input loaded from path specified
	 *
	 * @return true or false
	 */
	public boolean isLoaded() {
		return loaded;
	}

	private void open() {

		try {
			writer = new PrintWriter(outputFile);

		} catch (FileNotFoundException e) {
			loaded = false;
			failReason = FAIL_READING;
		}
	}

	private boolean overwrite() {

		String string = FilesInOut.requestInput("File already exists. Overwrite? (y/n)");
		string = string.toLowerCase();

		while ((!string.equals("y")) && (!string.equals("n"))) {
			string = FilesInOut.requestInput("Invalid response. Type y or n.");
		}
		if (string.equals("y")) {
			return true;
		}
		return false;

	}
}
