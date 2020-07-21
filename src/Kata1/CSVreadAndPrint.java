package Kata1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CSVreadAndPrint {
	/** Prints the CSV file.
	 * 
	 * @param filepath the path of the CSV file.
	 * @param separatedValuesPerLine a List of String Arrays. Each Array represents
	 *                               one line from the table. The values of each line, previously
	 *                               separated by a delimiter in the CSV file, are
	 *                               now separated.
	 * @param widths                 array which represents the width each column of
	 *                               the table should have from left to right. The
	 *                               value is derived from the item that needs the
	 *                               most space (has the most characters) in each
	 *                               column.
	 */
	public static void printCsv(String filePath, int numberOfOutputLines) {
		List<String[]> separatedValuesPerLine = readLinesAndSeperate(filePath);
		int[] widths = Widthsgetter.getWidthPerColoumn(separatedValuesPerLine);
		ConsoleOutput.print(separatedValuesPerLine, widths, numberOfOutputLines);
	}

	private static List<String[]> readLinesAndSeperate(String filePath) {
		Stream<String> rows;
		try {
			rows = Files.lines(Paths.get(filePath));
			List<String[]> seseparatedValuesPerLine = rows
					.map(x->x.split(";"))
					.collect(Collectors.toList());
			rows.close();
			return seseparatedValuesPerLine;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;	
	}
}
	