import java.util.Scanner;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 * 
 * @author Petter Sandas 2021
 *
 */
public class FilesInOut {

	final static String ARGUMENT_REMINDER = "Invalid arguments. Correct usage: java -jar [filename].jar (-u) inputPath outputPath";
	final static String INPUT_REMINDER = "Failed reading input path, try again or press enter to exit.";
	final static String OUTPUT_REMINDER = "Failed reading output path, try again or press enter to exit.";

	static Scanner inputRead = new Scanner(System.in); // Read user input

	static String inputPath;
	static String outputPath;
	static Reader inputProcess;
	static Writer outputProcess;
	static Formatter formatter = new Formatter();

	/*
	 * 
	 * To run (this works in Terminal) java -jar [file].jar inputpath outputpath
	 * The program will also ask for input / output if you prefer.
	 * 
	 */
	
	public static void main(String[] args) {

		if (args.length > 0) {

			boolean success = loadFromArgs(args);

			if (!success) {
				return; // Code red. Abort mission
			}

		} else {
			loadFromUser(); // Ask for input instead
		}

		inputRead.close();

		while (inputProcess.hasNextLine()) {

			String nextThing = formatter.formatString(inputProcess.getNextLine());
			outputProcess.addLine(nextThing);
		}

		outputProcess.commitChanges();
		System.out.println("Succesfully wrote output file to: " + outputPath);

	}

	/**
	 * Get input from user. Will not terminate until input has been provided
	 *
	 * @param prompt What to tell the user before reading input
	 * @return A string with user input
	 */
	public static String requestInput(String prompt) {

		String response;

		System.out.println(prompt);

		while (!inputRead.hasNextLine()) {
		}
		response = inputRead.nextLine();

		return response;

	}

	// Load user input interactively (is that a word?)
	private static void loadFromUser() {

		inputPath = requestInput("Please enter an input path:");
		inputProcess = new Reader(inputPath);

		//Keep asking for input until cancelled or success
		while (!inputProcess.isLoaded()) {
			inputPath = requestInput(INPUT_REMINDER + " Error: " + inputProcess.getFailReason());
			if (inputPath.isEmpty()) {
				System.out.println("Process aborted.");
				return;
			}
			inputProcess.setPath(inputPath);
			inputProcess.tryLoadFile();
		}

		outputPath = requestInput("Please enter an output path:");
		outputProcess = new Writer(outputPath);

		//Keep asking for output until cancelled or success
		while (!outputProcess.isLoaded()) {
			outputPath = requestInput(OUTPUT_REMINDER + " Error: " + outputProcess.getFailReason());
			if (outputPath.isEmpty()) {
				System.out.println("Process aborted.");
				return;
			}
			outputProcess.setPath(outputPath);
			outputProcess.tryLoadFile();

		}

		//Uppercase check
		String upperCase = requestInput("Format all in uppercase? (y/n)");
		upperCase = upperCase.toLowerCase();

		while ((!upperCase.equals("y")) && (!upperCase.equals("n"))) {
			upperCase = requestInput("Invalid response. Type y or n.");
		}
		if (upperCase.equals("y")) {
			formatter.toggleUpperCase();
		}

	}

	// Load user input from arguments provided when running the file
	private static boolean loadFromArgs(String[] args) {

		//To support uppercase functionality
		if (args[0].equals("-u") && args.length == 3) {

			inputPath = args[1];
			outputPath = args[2];

			formatter.toggleUpperCase();

		} else if (args.length == 2) {

			inputPath = args[0];
			outputPath = args[1];

		} else {
			System.out.println(ARGUMENT_REMINDER);
			return false;
		}

		inputProcess = new Reader(inputPath);
		outputProcess = new Writer(outputPath);

		if (inputProcess.isLoaded() && outputProcess.isLoaded()) {
			return true;
		}

		else {

			if (!inputProcess.isLoaded()) {
				System.out.println("Failed reading file: " + inputProcess.getFailReason());
			} else if (!outputProcess.isLoaded()) {
				System.out.println("Failed writing to file: " + outputProcess.getFailReason());
			}

			return false;

		}

	}

}
