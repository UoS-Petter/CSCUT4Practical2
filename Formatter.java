import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Read input string and return formatted string
 *
 */
public class Formatter {

	String[] splitString;
	int length;

	private String[] splitString(String inputString) {

		splitString = inputString.split(" ");
		length = splitString.length;

		return splitString;

	}

	public String formatString(String inputString) {

		String[] formatted = splitString(inputString);
		String returnString = "";
		int names = 2;

		for (int i = 0; i < length; i++) {
			if (i < names) {
				returnString += capitalize(formatted[i]) + " ";
			} else {
				returnString += parseDate(formatted[i]);
			}

		}
		return returnString;
	}

	public String parseDate(String dateString) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDate dateTime = LocalDate.parse(dateString, formatter);
		String formattedDate = dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear();

		return formattedDate;

	}

	public String capitalize(String input) {

		String output = "";

		if (!input.isEmpty()) {
			output = Character.toUpperCase(input.charAt(0)) + input.substring(1);
		}
		return output;
	}
}
