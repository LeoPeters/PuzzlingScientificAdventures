/**
 * 27.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 2 - plain text attack
 * @author Cecilia Casarella & Leo Peters
 */
package chessboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Leo
 *
 */
public class Chessboard {
	/*
	 * n, e, s, e, nn, ww n, eee, sss, e, nnnn, wwww n, eeeee, sssss, e, nnnnnn,
	 * wwwwww
	 */
	private int[] array = new int[4]; // N = 0, E = 1, S = 2, W = 3
	private int numberOfSteps = 20;
	private int countSteps = 2;
	private int counterAlgorithm = 1;

	public static void main(String[] args) {
		try {
			Chessboard chessboard = new Chessboard();
			File file = new File("chessboard.txt");
			Scanner scanner = new Scanner(file);
			String temp;
			int value;

			while (scanner.hasNextLine()) {
				temp = scanner.nextLine();
				value = Integer.parseInt(temp);
				if(value == 0) {
					break;
				}
				chessboard.doAlgorithmNTimes(value);
			}

			scanner.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void reset() {
		countSteps = 2;
		counterAlgorithm = 1;
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	private void doAlgorithmNTimes(int n) {
		reset();
		numberOfSteps = n;
		
		try {
			while (true) {
				algorithmStep();
			}
		} catch (FinishedAlgorithmException e) {
		}
		System.out.println("Input: " + n + "\t\tOutput: " + (array[1] - array[3] + 1) + " " + (array[0] - array[2] + 1));
	}

	public void algorithmStep() throws FinishedAlgorithmException {
		countArray(0);
		for (int i = 0; i < counterAlgorithm; i++) {
			countArray(1);
		}
		for (int i = 0; i < counterAlgorithm; i++) {
			countArray(2);
		}
		countArray(1);
		for (int i = 0; i < counterAlgorithm + 1; i++) {
			countArray(0);
		}
		for (int i = 0; i < counterAlgorithm + 1; i++) {
			countArray(3);
		}
		counterAlgorithm += 2;
	}

	private void countArray(int index) throws FinishedAlgorithmException {
		array[index]++;
		if (countSteps++ == numberOfSteps) {
			throw new FinishedAlgorithmException();
		}
	}
	
//	private void mathematicalSolution(int n) {
//		int sqrt = (int) Math.sqrt(n);
//		int upperBound = sqrt+1;
//		if(sqrt % 2 == 0) {
//			
//		} else {
//			n - (int)Math.pow(sqrt, 2);
//		}
//	}
	
	
	

	public int getNumberOfSteps() {
		return numberOfSteps;
	}
}
