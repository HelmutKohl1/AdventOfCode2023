package com.aoc;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
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
	static final int[] limits = {12, 13, 14};
	static Map<Integer, Boolean> result = new HashMap<>();
	
	
	public static void run() throws IOException {
		try {
			inputStream = new BufferedReader(new FileReader(PATH));
			
			String line = null;
			int id = 0;
			int[] maxes = {};
			
			while ((line = inputStream.readLine()) != null) {
				line = line.substring(5); // Strip out 'Game: '
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
		String testString = "-1";
		
		while (isWordNumber(Character.toString(line.charAt(counter)))) {
			counter++;
			testString = line.substring(0, counter);
		}
		
		return Integer.parseInt(testString);
	}
	
	private static boolean isWordNumber(String input) {
		if (input != null && input.length() > 0) {
			return numbers.contains(input) || input.isBlank();
		}
		return false;
	}

}
