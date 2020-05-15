package goldbach;

import java.util.Scanner;

public class Goldbach {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Goldbach goldbach = new Goldbach();
		String temp;
		int number = 0;
		do {
			System.out.println("Please type in a new number");
			temp = scanner.next();
			try {
				PrimeNumbers result = goldbach.goldbachAlgorithm((Integer.parseInt(temp)));
				for(int i = 0; i < PrimeNumbers.NUMBEROFPRIMES; i++)  {
					System.out.println(result.getPrime(i));
				}
			} catch (NumberFormatException e) {
				System.out.println("This is not a number!");
			} catch (NullPointerException e) {
				
			}

		} while (temp != "x");

	}

	private PrimeNumbers goldbachAlgorithm(int start) {
		PrimeNumbers primes = new PrimeNumbers(start);
		try {
		if(start <= 7) {
			throw new IllegalArgumentException();
		}
		} catch(IllegalArgumentException e) {
			System.out.println("Bitte geben Sie eine Zahl > 7 ein!");
			return null;
		}
		int counter = 0;
		int primeNumber = findClosestPrime(start);

		while (true) {
			primes.setPrime(counter, primeNumber);
			counter++;
			if (counter == PrimeNumbers.NUMBEROFPRIMES - 1 && isPrime(primes.getRemainder())
					&& primes.getSum() + primes.getRemainder() == start) {
				primes.setPrime(counter, primes.getRemainder());
				break;
			} else if (primes.getRemainder() <= 2 || primes.getRemainder() < (PrimeNumbers.NUMBEROFPRIMES - counter) * 2) {
				primeNumber = findClosestPrime(primes.getPrime(--counter) - 1);
				continue;
			} else {
				primeNumber = findClosestPrime(primes.getRemainder());
			}
		}
		return primes;
	}

	private int findClosestPrime(int n) {
		while (!isPrime(n--));
		return n+1;
	}

	private boolean isPrime(int n) {
		if(n == 1) {
			return false;
		}
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
