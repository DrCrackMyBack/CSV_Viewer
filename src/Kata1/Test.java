package Kata1;

import java.util.ArrayList;
import java.util.Collections;

public class Test {

	public static void main(String[] args) {
		int i = 3;
		int j = 9;
		int k = 123;
		String s = "Hallo;meni;name;ist;Thomas";
		ArrayList<Integer> testlist = new ArrayList<>();

		testlist.add(i);
		testlist.add(j);
		testlist.add(k);

		int maxValue = Collections.max(testlist);

		String [] x = s.split(";");
		System.out.println(x[0]);
	}

}
