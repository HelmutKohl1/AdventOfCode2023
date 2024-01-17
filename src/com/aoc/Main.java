package com.aoc;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		System.out.println("Advent of Code 2023");
		try {
			DayTwo.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
