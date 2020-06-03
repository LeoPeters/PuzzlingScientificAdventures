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
 * This class is a solution for the chessboard task. (For more information see
 * "Ant on a chessboard.pdf"). You will be asked to input a number (in the
 * "chessboard.txt" file), that will determine how many steps the algorithm will
 * calculate.
 * 
 * @author Leo
 *
 */
public class Chessboard {

	private int[] array = new int[4]; // N = 0, E = 1, S = 2, W = 3
	private int numberOfSteps;
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
				if (value == 0) {
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

	/**
	 * Reset variables after the algorithm ran through its scheme
	 */
	private void reset() {
		countSteps = 2;
		counterAlgorithm = 1;
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	/**
	 * This is a wrapper for the algorithmStep() and will catch the
	 * FinishedAlgorithmException thrown by countArray
	 * 
	 * @param n
	 */
	private void doAlgorithmNTimes(int n) {
		reset();
		numberOfSteps = n;

		try {
			while (true) {
				algorithmStep();
			}
		} catch (FinishedAlgorithmException e) {
		}
		System.out
				.println("Input: " + n + "\t\tOutput: " + (array[1] - array[3] + 1) + " " + (array[0] - array[2] + 1));
	}

	/**
	 * This will make one cycle of steps until the start of the next cycle.
	 * 
	 * @throws FinishedAlgorithmException
	 */
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

	/**
	 * This method will count the steps of the ant one by one and checks if we have
	 * reached the determined number of steps. If so it will throw a
	 * FinishedAlgorithmException which can be caught to break out of the loop.
	 * 
	 * @param index - the index that should be counted up (see declaration of array
	 *              for more info)
	 * @throws FinishedAlgorithmException
	 */
	private void countArray(int index) throws FinishedAlgorithmException {
		array[index]++;
		if (countSteps++ == numberOfSteps) {
			throw new FinishedAlgorithmException();
		}
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}
}
