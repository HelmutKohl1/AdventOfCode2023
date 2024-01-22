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
	static final Set<String> numbers= new HashSet<>();
	static {
		for (int i = 0; i < 10; i++) {
			numbers.add(Integer.toString(i));
		}
	}
	static final Set<String> colours = new HashSet<>();
	static {
		colours.add("Red"); colours.add("Green"); colours.add("Blue");
	}
	static enum COLOUR{
		RED,
		GREEN,
		BLUE
	}
	
	static final int[] limits = {12, 13, 14};
	static Map<Integer, Boolean> result = new HashMap<>();
	static int counter = 0;
	static String current = "";
	
	
	public static void run() throws IOException {
		try {
			inputStream = new BufferedReader(new FileReader(PATH));
			
			String line = null;
			int id = 0;
			int[] maxes = {};
			
			while ((line = inputStream.readLine()) != null) {
				counter = 0;
				current = line.substring(counter, counter + 1);
				line = line.substring(5); // Strip out 'Game: '
				id = parseNum(line);
				System.out.println("id " + id);			
				int[] lineMaxes = parseGame(line);			
			}
			
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

	}
	
	private static int[] parseGame(String line) {
		int[] result = {};
		
		while (!current.equals("\n")) {			
			int[] drawResult;
			if (current.equals(";")) {
				drawResult = parseDraw(line);
			} 
			
			if (counter < line.length()) {
				counter++;
			}
		}
		
		return result;
	}
	
	private static int[] parseDraw(String line) {
		int[] drawResult = {};
		Map<COLOUR, Integer> colorResult = new HashMap<>();
		
		while (!current.equals(";") && counter < line.length()) {
			colourResult = parseColour();
		}
		
		
		return drawResult;
	}
	
	private static int parseNum(String line) {
		String testString = "-1";
		
		while (isWordNumber(Character.toString(line.charAt(counter)))) {
			counter++;
			testString = line.substring(0, counter);
		}
		
		return Integer.parseInt(testString);
	}
	
	private static Map<COLOUR, Integer> parseColour(String line){
		Map<COLOUR, Integer> result = new HashMap<>();
		result.put(COLOUR.RED, 0);
		result.put(COLOUR.GREEN, 0);
		result.put(COLOUR.BLUE, 0);
		
		int quantity = parseNum(line);
		int secondCounter = counter;
		String testString = line.substring(secondCounter, secondCounter + 1);
		
		while (isColour(testString)) {
			if (secondCounter < line.length() - 1) {
				secondCounter++;
			}	
		}
		
		return result;
	}
	
	private static boolean isWordNumber(String input) {
		if (input != null && input.length() > 0) {
			return numbers.contains(input) || input.isBlank();
		}
		return false;
	}
	
	private static boolean isLetter(String input) {
		return Character.isAlphabetic(input.toCharArray()[0]);
	}

	private static boolean isColour(String input) {
		return colours.stream().anyMatch(colour -> (colour.startsWith(input)));
	}
	
	private static String peek(String line) {
		return line.substring(counter, counter+1);
	}
}
