package Kata1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVreadAndPrint {
	/**
	 * Prints the CSV file.
	 * 
	 * @param filepath the path of the CSV file.
	 * @param number   of Outputlines defines how many lines of the table are shown
	 *                 per page.
	 * @delimiter delimiter that separates the values in the CSV file.
	 * 
	 */
	public static void printCsv(String filePath, int numberOfOutputLines, String delimiter) {
		List<String[]> separatedValuesPerLine = readAndSeparateLines(filePath, delimiter);
		int[] widths = Widthsgetter.getWidthPerColoumn(separatedValuesPerLine);
		ConsoleOutput.print(separatedValuesPerLine, widths, numberOfOutputLines);
	}

	private static List<String[]> readAndSeparateLines(String filePath, String delimiter) {

		try {
			Stream<String> rows = Files.lines(Paths.get(filePath));
			List<String[]> separatedValuesPerLine = rows.map(x -> x.split(delimiter)).collect(Collectors.toList());
			rows.close();
			return separatedValuesPerLine;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
