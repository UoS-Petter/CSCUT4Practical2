import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Read input string and return formatted string
 * It's not as nice as the other classes -- i know. i'm sorry ok
 *
 */
public class Formatter {

	String[] splitString;
	int length;
	boolean upperCase;

	/**
	 * Should names be uppercase only? Defaults to false
	 *
	 */
	public void toggleUpperCase() {
		this.upperCase = true;
	}
	
	/**
	 * Read a single line of input string and return it reformatted
	 *
	 * @param inputString a line of input from a properly formatted file
	 * @return a reformatted string
	 */
	public String formatString(String inputString) {

		String[] formatted = splitString(inputString);
		String returnString = "";
		int names = 2;

		//this is where things get messy
		
		//There's a middle initial
		if (length == 4) {
			names = 3;
		}

		for (int i = 0; i < length; i++) {
			if (i < names) {

				if (names == 3 && i == 1) { //this is to handle the middle initial
					returnString += formatted[i].toUpperCase() + ". ";
				} else {
					//Capitalize fully or partially
					returnString += capitalize(formatted[i], upperCase) + " ";
				}

			} else {
				//Process the string. Zeroes fall off
				returnString += parseDate(formatted[i]);
			}

		}
		return returnString;
	}
	
	private String[] splitString(String inputString) {

		splitString = inputString.split(" ");
		length = splitString.length;

		return splitString;

	}


	private String parseDate(String dateString) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDate dateTime = LocalDate.parse(dateString, formatter);
		String formattedDate = dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear();

		return formattedDate;

	}

	private String capitalize(String input, boolean allCaps) {

		String output = "";

		if (!input.isEmpty()) {
			if (allCaps) {
				output = input.toUpperCase();
			} else {
				output = Character.toUpperCase(input.charAt(0)) + input.substring(1);
			}
		}
		return output;
	}


}
