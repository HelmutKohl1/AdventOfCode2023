package com.aoc;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Set;
import java.util.HashSet;

public class DayOne {
	public static void main(String[] args) throws IOException {
		
		final String PATH = "C:\\Users\\alig6\\eclipse-workspace\\AdventOfCode2023\\res\\dayOne.txt";
		BufferedReader inputStream = null;
		final Set<String> numbers = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			numbers.add(Integer.toString(i));
		}
		
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
					if (!firstNumFound && numbers.contains(Character.toString(line.charAt(i)))) {
						firstNum = Character.toString(line.charAt(i));
						firstNumFound = true;
					}
					if (!lastNumFound && numbers.contains(Character.toString(line.charAt(line.length() - i - 1)))) {
						lastNum = Character.toString(line.charAt(line.length() - i - 1));
						lastNumFound = true;
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
			System.out.println(totalCalNumber);			
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
	}
	
}
