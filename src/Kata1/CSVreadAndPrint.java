package Kata1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVreadAndPrint {
	/**
	 * Prints the CSV file.
	 * 
	 * @param filepath the path of the CSV file.
	 * @param numberofOutputlines defines how many lines of the table are shown
	 *                 per page.
	 * @delimiter delimiter that separates the values in the CSV file.
	 * 
	 */
	public static void printCsv(String filePath, int numberOfOutputLines, String delimiter) {
		List<String> unseparatedLines = readLines(filePath);
		List<String[]> separatedHeader = createHeader (unseparatedLines, delimiter);
		List<String[]> separatedValuesPerLine = separateLines(unseparatedLines, delimiter);
		int[] widths = Widthsgetter.getWidthPerColoumn(separatedValuesPerLine);
		ConsoleOutput.print(separatedValuesPerLine, widths, numberOfOutputLines);
	}
	
	private static List<String[]> createHeader (List<String> unseparatedLines, String delimiter) {
			
			List<String[]> separatedHeader = unseparatedLines
					.stream()
					.limit(1)
					.map(x->"No.;" + x)
					.map(x -> x.split(delimiter))
					.collect(Collectors.toList());
			
		return separatedHeader;
	}
	
	private static List<String> readLines(String filePath){
		try {
			Stream<String> rows = Files.lines(Paths.get(filePath));
			List<String> unseparatedLines = rows
					.collect(Collectors.toList());
			rows.close();
			return unseparatedLines;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<String[]> separateLines(List<String> unseparatedLines, String delimiter) {
			List<String[]> separatedValuesPerLine = unseparatedLines.stream()
					.skip(1)
					.map(x -> x.split(delimiter))
					.collect(Collectors.toList());
			return separatedValuesPerLine;
	}
}
