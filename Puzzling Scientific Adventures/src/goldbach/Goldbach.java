/**
 * 20.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 2 - Goldbach primes
 * @author Cecilia Casarella & Leo Peters
 */

package goldbach;

import java.util.Scanner;

/**
 * This class will for any given integer > (NUMBEROFPRIMES * 2) calculate
 * NUMBEROFPRIMES prime numbers that sum the given number. NUMBEROFPRIMES can be
 * set in {@link PrimeNumbers} and will determine how many prime numbers the
 * result will be composed of. The algorithm assumes that the Goldbach
 * assumption is true, otherwise it will run endlessly.
 * 
 * @see PrimeNumbers
 * @author Leo
 *
 */
public class Goldbach {
	public static final int NUMBEROFPRIMES = 4;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Goldbach goldbach = new Goldbach();
		String temp;
		do {
			System.out.println("Please type in a new number, type 'x' to exit");
			temp = scanner.next();
			try {
				PrimeNumbers result = goldbach.goldbachAlgorithm((Integer.parseInt((temp))));
				for (int i = 0; i < NUMBEROFPRIMES; i++) {
					System.out.println(result.getPrime(i));
				}
			} catch (NumberFormatException e) {
				System.out.println("This is not a number!");
			} catch (NullPointerException e) {

			}
		} while (temp != "x");
	}

	/**
	 * The main algorithm. It starts from the parameter start and searches for the
	 * next closest prime that is less than start. It saves the number and does the
	 * same for the remainder (start - closestPrime). This repeats until either all
	 * primes are found and sum up to start or the remainder is too low to go on. If
	 * the remainder is too low the prime from one step before will be reduced to
	 * the next lowest prime. Primes can at minimum be reduced to 2 at which point
	 * the prime one step further back will be reduced. This repeats until a
	 * solution is found.
	 * 
	 * @param start
	 * @return
	 */
	private PrimeNumbers goldbachAlgorithm(int start) {
		PrimeNumbers primes = new PrimeNumbers(start);
		// The Goldbach assumption only works for numbers > NUMBEROFPRIMES - 1
		try {
			if (start <= NUMBEROFPRIMES * 2 - 1) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Please insert a number > " + (NUMBEROFPRIMES * 2 - 1) + " !");
			return null;
		}
		int counter = 0;
		// Find first prime
		int primeNumber = findClosestPrime(start);

		while (true) {
			// Set current prime
			primes.setPrime(counter, primeNumber);
			// Search for next prime
			counter++;

			// Condition for solution
			if (counter >= NUMBEROFPRIMES - 1
					&& (isPrime(primes.getRemainder()) || (primes.getRemainder() == 0))
					&& primes.getSum() + primes.getRemainder() == start && primes.isValidResult()) {

				// Set last prime
				if (counter != NUMBEROFPRIMES) {
					primes.setPrime(counter, primes.getRemainder());
				}
				break;

				// No solution can be found with previously picked primes
			} else if (primes.getRemainder() < 2
					|| primes.getRemainder() < (NUMBEROFPRIMES - counter) * 2) {
				primeNumber = findClosestPrime(primes.getPrime(--counter) - 1);
				continue;

				// Solution can be found. Find next closest prime to remainder and continue
			} else {
				primeNumber = findClosestPrime(primes.getRemainder());
			}
		}
		return primes;
	}

	/**
	 * Finds the closest prime starting from n-1
	 * 
	 * @param n - the number where you want to start searching
	 * @return - the prime number closest to n
	 */
	private int findClosestPrime(int n) {
		while (!isPrime(n--))
			;
		return n + 1;
	}

	/**
	 * Calculates if a number is prime or not
	 * 
	 * @param n - the number to check the prime condition for
	 * @return - true if n is prime, false else
	 */
	private boolean isPrime(int n) {
		if (n == 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
