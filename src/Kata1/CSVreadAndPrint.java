package Kata1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVreadAndPrint {
	public static void printCsv(String filePath, int numberOfOutputLines) {
		ArrayList<String> nonSeparatedLines = CSVreadAndPrint.readLines(filePath);
		ArrayList<String[]> separatedValuesPerLine = CSVreadAndPrint.splitLines(nonSeparatedLines);
		int[] widths = Widthsgetter.getWidthPerColoumn(separatedValuesPerLine);
		ConsoleOutput.print(separatedValuesPerLine, widths, numberOfOutputLines);
	}

	private static ArrayList<String> readLines(String filePath) {
		String file = filePath;
		ArrayList<String> nonSeparatedLines = new ArrayList<>();
		try (BufferedReader lineReader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = lineReader.readLine()) != null) {
				nonSeparatedLines.add(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nonSeparatedLines;
	}

	private static ArrayList<String[]> splitLines(ArrayList<String> nonSeparated) {
		ArrayList<String[]> sepValuesPerLine = new ArrayList<String[]>();
		for (String nonSepValue : nonSeparated) {
			String[] sepValues = nonSepValue.split(";");
			sepValuesPerLine.add(sepValues);
		}
		return sepValuesPerLine;
	}
}
