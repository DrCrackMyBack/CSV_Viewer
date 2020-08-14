package Kata1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
	public static String askUserForAction(int currentPage, int totalPages) {
		System.out.println("");
		System.out.println("Page "+ currentPage +" of " + totalPages);
		System.out.println("N(ext page, P(revious page, F(irst page, L(ast page, J(ump to page, S)ort, eX(it");
		BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String userInput = userInputReader.readLine();
			return userInput;
			
		} catch (IOException e) {
			e.printStackTrace();
			// Logger.log(LoggingLevel.CRITICAL, e.getMessage());
		}
		return "";
	}
	
	public static boolean numberOrNot(String userInput){
	    try{
	        Integer.parseInt(userInput);
	    } catch(NumberFormatException ex) {
	        return false;
	    }
	    return true;
	}
}
