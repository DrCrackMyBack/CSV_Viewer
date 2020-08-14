package Kata1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVreadAndPrint {
	/**
	 * Prints the CSV file.
	 * 
	 * @param filepath            the path of the CSV file.
	 * @param numberofOutputlines defines how many lines of the table are shown per
	 *                            page.
	 * @param delimiter			  delimiter that separates the values in the CSV file.
	 * 
	 */
	public static void printCsv(String filePath, int numberOfOutputLines, String delimiter) {
		List<String> unseparatedLines = readLines(filePath);
		List<String[]> separatedHeader = separateHeader(unseparatedLines, delimiter);
		List<String[]> separatedLinesWithoutHeader = separateLinesWithoutHeader(unseparatedLines, delimiter);
		int[] widths = getWidthPerColoumn(separatedLinesWithoutHeader, separatedHeader);
		ConsoleOutput.print(separatedHeader,separatedLinesWithoutHeader, widths, numberOfOutputLines);
	}

	private static List<String> readLines(String filePath) {
		try {			
			return Files.lines(Paths.get(filePath)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

	private static List<String[]> separateHeader(List<String> unseparatedLines, String delimiter) {

		List<String> manipulatedLines = manipulateHeader(unseparatedLines, delimiter);
		List<String[]> separatedHeader = manipulatedLines
				.stream()
				.limit(1)
				.map(x -> x.split(delimiter))
				.collect(Collectors.toList());
		return separatedHeader;
	}

	private static List<String[]> separateLinesWithoutHeader(List<String> unseparatedLines, String delimiter) {
		List<String> manipulatedLines = manipulateLines(unseparatedLines, delimiter);
		List<String[]> separatedValuesPerLine = manipulatedLines
				.stream()
				.map(x -> x.split(delimiter))
				.collect(Collectors.toList());
		return separatedValuesPerLine;
	}
	
	private static int [] getWidthPerColoumn(List<String[]> separatedLinesWithoutHeader, List<String[]> separatedHeader) {
		int widthsOfLinesWoHeader [] = new int [separatedLinesWithoutHeader.get(0).length];
		//merge header and body to scan as a whole for the needed widths
		separatedLinesWithoutHeader.add(separatedHeader.get(0)); 
		
		for (String[] array : separatedLinesWithoutHeader) {
// TODO foreach
			for (int index = 0; index < array.length; index++) {
				
				int width = array[index].length();				
				if ( width > widthsOfLinesWoHeader[index]) {
					widthsOfLinesWoHeader[index] = width;
				}
			}
		}
		//separate header and body again for further processing
		separatedLinesWithoutHeader.remove(separatedLinesWithoutHeader.size()-1); 
		return widthsOfLinesWoHeader;
	}

	private static List<String> manipulateHeader(List<String> unseparatedLines, String delimiter) {
		List<String> manipulatedHeader = unseparatedLines.stream()
				.limit(1)
				.map(x -> "No." + delimiter + x)
				.collect(Collectors.toList());
		return manipulatedHeader;
	}
	
	private static List<String> manipulateLines(List<String> unseparatedLines, String delimiter){
		AtomicInteger rowCount = new AtomicInteger(1);
		List<String> manipulatedLines = unseparatedLines
				.stream()
				.skip(1)
				.map(x -> {
					int rowInt = rowCount.intValue();
					String rowCountString = Integer.toString(rowInt);
					String modifiedRow = rowCountString + "."+ delimiter + x;
					rowCount.addAndGet(1);
					return modifiedRow;		})
				.collect(Collectors.toList());
		return manipulatedLines;
	}
}
