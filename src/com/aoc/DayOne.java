package com.aoc;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class DayOne {
	
	static final String PATH = "C:\\Users\\alig6\\eclipse-workspace\\AdventOfCode2023\\res\\dayOne.txt";
	static BufferedReader inputStream = null;
	
	static final Map<String, String> wordNums;
	static {
		wordNums = new HashMap<>();
		wordNums.put("one", "1");wordNums.put("two", "2");wordNums.put("three", "3");
		wordNums.put("four", "4");wordNums.put("seven", "7");wordNums.put("six", "6");
		wordNums.put("five", "5");wordNums.put("eight", "8");wordNums.put("nine", "9");	
	}	
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
			boolean firstNumFound = false;
			boolean lastNumFound = false;
			int totalCalNumber = 0;

			while ((line = inputStream.readLine()) != null) {
				String calNumber = "";
				String firstNum = "";
				String lastNum = "";
				firstNumFound = false;
				lastNumFound = false;			
				
				for (int i = 0; i < line.length(); i++) {
					int j = line.length() - i;
					int firstCounter = 1;
					int lastCounter = 1;
					String firstTestString = null;
					String lastTestString = null;
					
					if (!firstNumFound) {
						/*Looking for digits*/
						if (numbers.contains(Character.toString(line.charAt(i)))) {
							firstNum = Character.toString(line.charAt(i));
							firstNumFound = true;
						}
					}
					if (!firstNumFound) {					
						/*Looking for numbers as words*/
						do {
							if (i + firstCounter <= line.length()) {
								firstTestString = line.substring(i, i + firstCounter);
								firstCounter++;	
							}
							if (isFullMatch(firstTestString)) {
								firstNum = wordNums.get(firstTestString);
								firstNumFound = true;
							}
						} while (isPartialMatch(firstTestString) && !firstNumFound);
					}

					if (!lastNumFound) {
						/*Looking for digits*/
						if (numbers.contains(Character.toString(line.charAt(j - 1)))) {
							lastNum = Character.toString(line.charAt(j - 1));
							lastNumFound = true;
						}
					}
					if (!lastNumFound) {
						/*Looking for numbers as words*/
						do {
							if (j - lastCounter >= 0) {
								lastTestString = line.substring(j - lastCounter, j);
								lastCounter++;	
							}
							if (isFullMatch(lastTestString)) {
								lastNum = wordNums.get(lastTestString);
								lastNumFound = true;
							}
						} while (isPartialReverseMatch(lastTestString) && !lastNumFound);
					}
					if (firstNumFound && lastNumFound) {
						calNumber += firstNum;
						calNumber += lastNum;
						System.out.println(calNumber);
						totalCalNumber += Integer.parseInt(calNumber);
						break;
					}
				}
			}
	
			System.out.println("Calibration Number: " + totalCalNumber);		
			
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
	}
	
	private static boolean isPartialMatch(String input) {
		if (input != null && input.length() > 0) {		
			return wordNums.keySet()
					.stream()
					.filter(key -> (key.length() >= input.length()))
					.anyMatch(key -> (key.substring(0, input.length()).equals(input)));
		}
		return false;
	}
	
	private static boolean isFullMatch(String input) {
		return wordNums.containsKey(input);
	}
	
	private static boolean isPartialReverseMatch(String input) {
		if (input != null && input.length() > 0) {
			return wordNums.keySet()
					.stream()
					.filter(key -> (input.length() <= key.length()))
					.anyMatch(key -> (key.substring(key.length() - input.length(), key.length()).equals(input)));
		}
		return false;
	}
}
