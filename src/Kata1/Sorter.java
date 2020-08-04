package Kata1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorter {

	public static List<String[]> sortLines(List<String[]> separatedLinesWithoutHeader, String columnToSortBy, String[] header) {
		int indexToSortby = getIndex(header, columnToSortBy);
		 Collections.sort(separatedLinesWithoutHeader,new Comparator<String[]>() {
		            public int compare(String[] strings, String[] otherStrings) {
		                return strings[indexToSortby].compareTo(otherStrings[indexToSortby]);
		            }
		       	});
		return separatedLinesWithoutHeader;
	}

	private static int getIndex(String[] header, String columnToSortBy) {
		List<String> headerList = Arrays.stream(header).collect(Collectors.toList());
		int index = headerList.indexOf(columnToSortBy);
		return index;
	}
}
