import java.util.Scanner;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 * 
 * @author Petter Sandas 2021
 *
 */
public class FilesInOut {

	public static void main(String[] args) {

		/*
		 * 
		 * To run (this works in Terminal)
		 * java -jar testing23.jar inputpath outputpath
		 * 
		 */
		
		//java -jar testing23.jar /Users/Petter/Desktop/Test/input.txt /Users/Petter/Desktop/Test/inputm.txt
		
		String inputPath = "/Users/Petter/Desktop/Test/input.txt";
		String outputPath = "/Users/Petter/Desktop/Test/inputm.txt";

		if (args.length >= 2) {


			if(args[1].equals("-u") && args.length==3) {
			
			}
			else if(args.length==2) {
				
			}
			
			
			
			inputPath = args[0];
			outputPath = args[1];

			Reader inputProcess = new Reader(inputPath);
			Writer outputProcess = new Writer(outputPath);
			Formatter format = new Formatter();

			if (inputProcess.isLoaded() && outputProcess.isLoaded()) {
				while (inputProcess.hasNextLine()) {
					String nextThing = format.formatString(inputProcess.getNextLine());
					// System.out.println(nextThing);
					outputProcess.addLine(nextThing);
				}
				outputProcess.commitChanges();
			} else {
				System.out.println(inputProcess.getFailReason());
			}
		}
		
		else System.out.println("Invalid arguments");

	} // main
	
	public static String requestInput(String prompt) {
		
	    Scanner inputRead = new Scanner(System.in);  // Create a Scanner object
	    String response;
	    
	    System.out.println(prompt);
	    
	    while(!inputRead.hasNextLine()) {
	    }
	    
	    response = inputRead.nextLine();
	    
	    inputRead.close();
		return response;
		
	}
	
	
} // FilesInOut
