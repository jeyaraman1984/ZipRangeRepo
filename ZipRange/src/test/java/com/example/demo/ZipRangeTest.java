package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ZipRangeTest  {
	
	int[][] inputs;
	int[][] expected;
	int[][] result;

	@BeforeEach
	protected void setUp() throws Exception {

	}

	@AfterEach
	protected void tearDown() throws Exception {

		this.inputs = null;
		this.expected = null;
		this.result = null;
		
	}
	
	@Test
	void testNoOverlapZipRange() {
		ZipRange zipRange = new ZipRange();
		this.inputs =  new int[][] { new int[] { 94133,94133 }, new int[] {94200,94299}, new int[] {94600,94699 } };
		this.expected =  new int[][] { new int[] { 94133,94133 }, new int[] { 94200,94299 }, new int[] { 94600,94699 } };
		this.result = zipRange.normalizeZipCodeRanges(inputs);
		assertArrayEquals(expected, result);
	}

	@Test
	void testOverlapZipRange() {
		ZipRange zipRange = new ZipRange();
		this.inputs =  new int[][] { new int[] { 94133,94133 }, new int[] { 94200,94299 }, new int[] { 94226,94399 } };
		this.expected =  new int[][] { new int[] { 94133,94133 }, new int[] { 94200,94399 } };
		this.result = zipRange.normalizeZipCodeRanges(inputs);
		assertArrayEquals(expected, result);
	}
	
	
	@Test
	void testInconsistentZipRange() {
		ZipRange zipRange = new ZipRange();
		this.inputs =  new int[][] { new int[] { 94133,94133 }, new int[] { 94200,94299 }, new int[] { 94250,94249 }, new int[] { 94226,94399 } };
		this.expected =  new int[][] { new int[] { 94133,94133 }, new int[] { 94200,94399 } };
		this.result = zipRange.normalizeZipCodeRanges(inputs);
		assertArrayEquals(expected, result);
	}
	
	@Test
	void testInconsistentZipRanges() {
		ZipRange zipRange = new ZipRange();
		this.inputs =  new int[][] { new int[] { 94133,94138 }, new int[] { 94200,94299 }, new int[] { 94250,94249 }, new int[] { 94226,94399 }, new int[] { 94284,94281 }, new int[] { 94286,94420 } };
		this.expected =  new int[][] { new int[] { 94133,94138 }, new int[] { 94200,94420 } };
		this.result = zipRange.normalizeZipCodeRanges(inputs);
		assertArrayEquals(expected, result);
	}
	
	
	@Test
	void testMultipleOverlapZipRange() {
		ZipRange zipRange = new ZipRange();
		this.inputs =  new int[][] { new int[] { 94133,94140 }, new int[] { 94138,94145 }, new int[] { 94142,94259 }, new int[] { 94286,94399 } };
		this.expected =  new int[][] { new int[] { 94133,94259 }, new int[] { 94286,94399 } };
		this.result = zipRange.normalizeZipCodeRanges(inputs);
		assertArrayEquals(expected, result);
	}
	
	

}
