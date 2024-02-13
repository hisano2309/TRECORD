package com.example.app.domain;

import java.util.Random;

import lombok.Data;

@Data
public class Gacha {
	private Integer number;
	
	private static final int[] PRIZES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	
	public static Integer GachaNumber() {
		Random rand = new Random();
		Integer number = PRIZES[rand.nextInt(PRIZES.length)];
		return number;
	}
	
}
