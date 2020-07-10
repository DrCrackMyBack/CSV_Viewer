package Kata1;

import java.util.ArrayList;

public class Widthsgetter {
	
	public static int [] getWidthPerColoumn(ArrayList<String[]> separatedValuesPerLine) {
		int widths [] = new int [separatedValuesPerLine.get(0).length];
		
		for (String[] array : separatedValuesPerLine) {
// TODO foreach
			for (int index = 0; index < array.length; index++) {
				
				int width = array[index].length();				
				if ( width > widths[index]) {
					widths[index] = width;
				}
			}
		}
		return widths;
	}
}


