/**
 * 20.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 2 - Goldbach primes
 * @author Cecilia Casarella & Leo Peters
 */

package goldbach;

/**
 * Overloaded class for {@link PrimeNumbers} to use long instead of int. All
 * documentation can be found in {@link PrimeNumbers}.
 * 
 * @see PrimeNumbers
 * @author Leo
 *
 */
public class PrimeNumbersLong {
	private long[] primes = new long[GoldbachLong.NUMBEROFPRIMES];
	private long result;
	
	public PrimeNumbersLong(long result) {
		for(int i = 0; i < primes.length; i++) {
			primes[i] = 0;
		}
		this.result = result;
	}
	
	/**
	 * Find documentation here: {@link PrimeNumbers}
	 * 
	 * @see Goldbach
	 */
	public long getSum() {
		long sum = 0;
		for(long p : primes) {
			sum += p;
		}
		return sum;
	}
	
	/**
	 * Find documentation here: {@link PrimeNumbers}
	 * 
	 * @see Goldbach
	 */
	public long getRemainder() {
		return result - getSum();
	}	
	
	/**
	 * Find documentation here: {@link PrimeNumbers}
	 * 
	 * @see Goldbach
	 */
	public boolean isValidResult() {
		for(long p : primes) {
			if(p == 0) {
				return false;
			}
		}
		return true;
	}
	
	public long getPrime(int index) {
		return primes[index];
	}
	
	public void setPrime(int index, long value) {
		primes[index] = value;
	}
}
