package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ZipRange {

	static Logger log = Logger.getLogger(ZipRange.class);

	static Long MIN_ZIP_RANGE = 10000L;
	static Long MAX_ZIP_RANGE = 99999L;

	/**
	 *  Normalizing the zip code range to determine zip code range to be shipped / delivered.
	 * 
	 * @param inputZips - collection zip code range.
	 * @return result of sorted/normalized zip ragne
	 */
	public int[][] normalizeZipCodeRanges(int[][] inputZips) {
		BasicConfigurator.configure();

		
		// Validate the inputs
		this.validateZipRange(inputZips);
		
		// Sorting input range values
		this.sortRange(inputZips);
				
		// Print after re-arrange input
		System.out.println("Rearranged zipcode ranges before normalized\n");
		this.printRanges(inputZips);

		// Method to call arrange minimum number of ranges
		int[][] resultRanges = this.getRanges(inputZips);

		System.out.println("\n Final Zip code Ranges:");

		// printing the Finalized zipCode ranges.
		this.printRanges(resultRanges);
		
		System.out.println("--------------------------------END--------------------------------------");
		
		return resultRanges;
	}
	
	/**
	 *  validating the input range. 
	 *  
	 * @param inputZips - Input zip ranges.
	 */
	private void validateZipRange(int[][] inputZips) {
		// Validating zipCode length is 5 digit or not?
		for (int i = 0; i < inputZips.length; i++) {
			for (int j = 0; j < inputZips[i].length; j++) {

				// Checking the range of the zip code
				if (inputZips[i][j] < MIN_ZIP_RANGE || inputZips[i][j] > MAX_ZIP_RANGE) {
					log.error("Invalid input. Zipcode length must be 5 digit number");
					log.error("Please check input zip code values");
					System.exit(0);
				}
			}
		}
	}
	
	

	/**
	 * swap/merge zip ranges based on min and max range of zip codes.
	 * 
	 * @param input - input array
	 * @return int[][] - new range array.
	 */
	private int[][] getRanges(int[][] inputZips) {
		int[][] outputArray = new int[inputZips.length][];
		int i = 0;
		int j;
		int k =-1;
		int min, max;
		boolean ignore = false;
		
		
		while (i < inputZips.length) {
			// invalid input. min range > max range
			if (inputZips[i] [0] > inputZips[i] [1]) {
				System.out.println("ERROR : Ignoring..Inconsistent Data ==> [" + inputZips[i][0] + ", " + inputZips[i][1] + "]");
				i++;
			}
			else {
				min = inputZips[i] [0];
				max = inputZips[i] [1];
				for (j = i + 1; j < inputZips.length; j++) {
					ignore = false;
					// invalid input. min range > max range
					if (inputZips[j] [0] > inputZips[j] [1]) {
						//Inconsistent data.. 
						System.out.println("ERROR : Ignoring..Inconsistent Data ==> [" + inputZips[j][0] + ", " + inputZips[j][1] + "]");
						i++;
						continue;
					}
					//[x,y] range within min and max limit.. ignore current.
					if (min <= inputZips[j][0] && max >= inputZips[j][1] ) {
						ignore = true;
					}
					//[x,y] current range min value is less than assigned min value. so updating min value and ignore current.
					if (inputZips[j][0] <= min ) {
						min = inputZips[j][0];
						ignore = true;
					}
					//[x,y] current range Max value is greater than assigned Max value. 
					if (inputZips[j][1] >= max ) {
						//[x,y] current range Min value is is less than assigned Max value, then update Max value and ignore current.
						if (inputZips[j][0] <= max) {
						max = inputZips[j][1];
						ignore = true;
						} else {
							// enough gap between Max and current min value.
							k++;
							outputArray[k] = new int[] { min, max};
							min = inputZips[j] [0];
							max = inputZips[j] [1];
							ignore = true;
						}
					}	
					//ignoring.. and while loop will skip this range
					if (ignore) {
						i++;
					}
				}
				//setting the correct index, so that while loop will go next element / exit on proper way.
				if (j == inputZips.length) {
					i++;
				}
				k++;
				outputArray[k] = new int[] { min, max};
			}
		}
		// removing null values in the array..(because of inconsistent data..)
		return Arrays.stream(outputArray).filter(Objects::nonNull).toArray(int[][]::new);
	}

	/**
	 * Method to print the zipCode ranges
	 * 
	 * @param printRange
	 */
	private void printRanges(int[][] printRange) {
		for (int i = 0; i < printRange.length; i++) {
			if (printRange[i] != null) {
				System.out.println("[" + printRange[i][0] + ", " + printRange[i][1] + "]");
			}
		}
	}

	/**
	 * Sorting input values
	 * 
	 * @param rangeToSort
	 */
	private void sortRange(int[][] rangeSort) {

		Arrays.sort(rangeSort, new Comparator<int[]>() {

			public int compare(int[] range1, int[] range2) {

				int min1 = range1[0];
				int min2 = range2[0];

				if (min1 > min2)
					return 1;

				if (min1 < min2)
					return -1;

				return 0;
			}

		});
	}

}
