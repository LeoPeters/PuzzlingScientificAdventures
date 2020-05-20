/**
 * 20.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 2 - Goldbach primes
 * @author Cecilia Casarella & Leo Peters
 */

package goldbach;

/**
 * PrimeNumbers are used by the Goldbach algorithm ({@link Goldbach}). The class
 * functions as utility to get f.e. the remainder or the sum of all primes
 * already found.
 * 
 * @see Goldbach
 * @author Leo
 *
 */
public class PrimeNumbers {
	private int[] primes = new int[Goldbach.NUMBEROFPRIMES];
	private int result;

	public PrimeNumbers(int result) {
		for (int i = 0; i < primes.length; i++) {
			primes[i] = 0;
		}
		this.result = result;
	}

	/**
	 * Sum of all primes already found.
	 * 
	 * @return - the sum of all primes
	 */
	public int getSum() {
		int sum = 0;
		for (int p : primes) {
			sum += p;
		}
		return sum;
	}

	/**
	 * The remainder of the sum of all primes subtracted from the result
	 * 
	 * @return - the remainder
	 */
	public int getRemainder() {
		return result - getSum();
	}

	/**
	 * This function will determine if any of the primes is 0. This is needed since
	 * the algorithm will check for completion by comparing the result to the sum.
	 * But if atleast one prime is 0 no solution is found yet.
	 * 
	 * @return - false if there is atleast one prime with value 0, true else
	 */
	public boolean isValidResult() {
		for (int p : primes) {
			if (p == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int getPrime(int index) {
		return primes[index];
	}

	public void setPrime(int index, int value) {
		primes[index] = value;
	}
}
