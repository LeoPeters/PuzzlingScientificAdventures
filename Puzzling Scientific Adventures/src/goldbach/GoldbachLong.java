/**
 * 20.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 2 - Goldbach primes
 * @author Cecilia Casarella & Leo Peters
 */

package goldbach;

import java.util.Scanner;

/**
 * Overloaded class for {@link Goldbach} to use long instead of int. All
 * documentation can be found in {@link Goldbach}.
 * 
 * @see Goldbach
 * @author Leo
 *
 */
public class GoldbachLong {
	public static final int NUMBEROFPRIMES = 4;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		GoldbachLong goldbach = new GoldbachLong();
		String temp;
		int number = 0;
		do {
			System.out.println("Please type in a new number, type 'x' to exit");
			temp = scanner.next();
			try {
				PrimeNumbersLong result = goldbach.goldbachAlgorithm((Long.parseLong(temp)));
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
	 * Find documentation here: {@link Goldbach}
	 * 
	 * @see Goldbach
	 */
	private PrimeNumbersLong goldbachAlgorithm(long start) {
		PrimeNumbersLong primes = new PrimeNumbersLong(start);
		try {
			if (start <= NUMBEROFPRIMES * 2 - 1) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Please insert a number > " + (NUMBEROFPRIMES * 2 - 1) + " !");
			return null;
		}
		int counter = 0;
		long primeNumber = findClosestPrime(start);

		while (true) {
			primes.setPrime(counter, primeNumber);
			counter++;
			if (counter >= NUMBEROFPRIMES - 1
					&& (isPrime(primes.getRemainder()) || (primes.getRemainder() == 0))
					&& primes.getSum() + primes.getRemainder() == start && primes.isValidResult()) {
				if (counter != NUMBEROFPRIMES) {
					primes.setPrime(counter, primes.getRemainder());
				}
				break;
			} else if (primes.getRemainder() < 2
					|| primes.getRemainder() < (NUMBEROFPRIMES - counter) * 2) {
				primeNumber = findClosestPrime(primes.getPrime(--counter) - 1);
				continue;
			} else {
				primeNumber = findClosestPrime(primes.getRemainder());
			}
		}
		return primes;
	}

	/**
	 * Find documentation here: {@link Goldbach}
	 * 
	 * @see Goldbach
	 */
	private long findClosestPrime(long n) {
		while (!isPrime(n--))
			;
		return n + 1;
	}

	/**
	 * Find documentation here: {@link Goldbach}
	 * 
	 * @see Goldbach
	 */
	private boolean isPrime(long n) {
		if (n == 1) {
			return false;
		}
		for (long i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
