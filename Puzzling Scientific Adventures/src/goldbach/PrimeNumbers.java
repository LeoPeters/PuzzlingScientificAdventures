package goldbach;

public class PrimeNumbers {
	public static final int NUMBEROFPRIMES = 4;
	private int[] primes = new int[NUMBEROFPRIMES];
	private int result;
	
	public PrimeNumbers(int result) {
		for(int i = 0; i < primes.length; i++) {
			primes[i] = 0;
		}
		this.result = result;
	}
	
	public int getSum() {
		int sum = 0;
		for(int p : primes) {
			sum += p;
		}
		return sum;
	}
	
	public int getRemainder() {
		return result - getSum();
	}	
	
	public int getPrime(int index) {
		return primes[index];
	}
	
	public void setPrime(int index, int value) {
		primes[index] = value;
	}
}
