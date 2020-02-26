package com.example.demo;

public class ZipRangeMain {

	public static void main(String[] args) {
		ZipRange zipRange = new ZipRange();
		
		int[][] inputZips;
				// Passing input zipCode Ranges
		inputZips = new int[][] { new int[] { 94133,94138 }, new int[] { 94200,94299 }, new int[] { 94250,94249 }, new int[] { 94226,94399 }, new int[] { 94284,94281 }, new int[] { 94286,94420 } };
		zipRange.normalizeZipCodeRanges(inputZips);

	}

}
