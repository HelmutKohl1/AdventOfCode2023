package com.aoc;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.standard.NumberUpSupported;

import java.io.BufferedReader;

public class DayTwo {
	
	static final String PATH = "C:\\Users\\alig6\\eclipse-workspace\\AdventOfCode2023\\res\\dayTwo.txt";
	static BufferedReader inputStream = null;
	static final Set<String> numbers;
	static {
		numbers = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			numbers.add(Integer.toString(i));
		}
	}
	
	
	public static void run() throws IOException {
		try {
			inputStream = new BufferedReader(new FileReader(PATH));
			
			String line = null;
			int id = 0;
			
			while ((line = inputStream.readLine()) != null) {
				line = line.substring(5);
				id = parseNum(line);
				System.out.println("id " + id);
				
				
			}
			
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

	}
	
	private static int parseNum(String line) {
		int counter = 0;
		String testString = "";
		
		do {
			if (counter > line.length() - 1) {
				testString = line.substring(0, counter);
				System.out.println("heelo");
				break;
			}			
			if (numbers.contains(testString)) {
				return Integer.parseInt(testString);
			}			
		} while (isPartialNumMatch(testString));	
		
		return -1;
	}
	
	private static boolean isPartialNumMatch(String input) {
		return numbers
		.stream()
		.filter((num) -> (num.length() <= input.length()))
		.anyMatch((num) -> (num.startsWith(input)));
	}

}
