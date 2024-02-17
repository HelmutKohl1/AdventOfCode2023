package com.aoc;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
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
		colours.add("red"); colours.add("green"); colours.add("blue");
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
				for (int i = 0; i < lineMaxes.length; i++) {
					System.out.println("line maxes " + lineMaxes[i]);	
				}
			}
			
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

	}

	private static int[] parseGame(String line) {
		/**Results are in format Red, Green, Blue*/
		int[] gameMaxes = {0, 0, 0};
		while (!peek(line).equals("\n")) {
			int[] drawMaxes = parseDraw(line);
			
			for (int i = 0; i <drawMaxes.length; i++) {
				if (drawMaxes[i] > gameMaxes[i]) {
					gameMaxes[i] = drawMaxes[i];
				}
			}
		}
		
		return gameMaxes;
	}
	
	private static int[] parseDraw(String line) {
		int[] result = {0, 0, 0};
		Map<String, Integer> localRes = new HashMap<>();
		while (!peek(line).equals(";")) {
			counter++;
			int qty = parseNum(line);
			String colour = parseColour(line);
			localRes.put(colour, qty);
			System.out.println("stuck");
		}
		
		for (String colour : localRes.keySet()) {
			switch (colour) {
			case "Red":
				result[0] = localRes.get(colour);
				break;
			case "Green":
				result[1] = localRes.get(colour);
				break;
			case "Blue":
				result[2] = localRes.get(colour);
				break;
			}
		}
		
		return result;
	}
	
	private static String parseColour(String line) {
		System.out.println("parsecol");
		
		String testChar = line.substring(counter, counter+1);
		System.out.println("testChar (col) " + testChar);
		while (!isLetter(testChar)) {		
			if(counter < line.length() - 2) counter++;
			testChar = line.substring(counter, counter+1);
			System.out.println("testChar (isSpace, col) " + testChar);
		}
		
		int start = counter;
		String testString = line.substring(start, counter+1);
		System.out.println("testString (col):" + testString);
		while (isColour(testString) && !isSpace(line.substring(counter+1, counter+2))) {
			testString = line.substring(start, counter+2);
			System.out.println("testString (col):" + testString);
			if(isColour(testString) && counter < line.length() - 2) {
				counter++;
			} else {
				return testString;
			}
			System.out.println("counter:" + counter);
		}
		
		return testString;
	}
	
	private static int parseNum(String line){
		System.out.println("parsenum");
		String testChar = line.substring(counter, counter+1);
		System.out.println("testChar:" + testChar);

		while (!isWordNumber(testChar)) {		
			if(counter < line.length() - 2) counter++;
			testChar = line.substring(counter, counter+1);
			System.out.println("testChar (isSpace): " + testChar);
		}
		
		int start = counter;
		while (isWordNumber(testChar)) {
			System.out.println("testChar:" + testChar);
			if(isWordNumber(line.substring(counter, counter+1)) && counter < line.length() - 2) { 
				counter++;
				System.out.println("counter " + counter);
				testChar = line.substring(start, counter);
			} else {
				return Integer.parseInt(testChar);
			}
			System.out.println("testChar:" + testChar);
		}
		
		return Integer.parseInt(testChar);
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
		return colours.stream().anyMatch((colour) -> (colour.startsWith(input)));
	}
	
	private static boolean isSpace(String input) {
		List<String> ignore = new ArrayList<>(); 
		ignore.add(" "); ignore.add(":"); ignore.add(",");
		return ignore.contains(input);
	}
	
	private static String peek(String line) {
		return line.substring(counter, counter+1);
	}
}
