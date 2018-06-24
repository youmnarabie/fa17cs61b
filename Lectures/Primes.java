public class Primes {

	public static void main(String[] args) {
		printPrimes(Integer.parseInt(args[0]));
	}


	/** Prints a list of primes from 2 to limit, 10 to a line **/
	public static void printPrimes(int limit) {
		if (limit > 1) {
			int np = 0;
			for (int p = 2; p <= limit; p += 1) {
				if (isPrime(p)) {
					System.out.print(p + " ");
					np += 1;
					if (np % 10 == 0) {
						System.out.println();
					}
				}
			}
		}
	}

	/** Return TRUE if the integer X is prime. */
	public static boolean isPrime(int x) {
		if (x <= 1) {
			return false;
		} else {
			return !isDivisible(x, 2);
		}
	}

	/** Return TRUE if X is divisible by any number >= K and < X given that K < X. **/
	public static boolean isDivisible(int x, int k) {
		if (k >= x) {
			return false;
		} else {
			if (x % k == 0) {
				return true; 
			} else {
				return isDivisible(x, k + 1); 
			}
		}
	}

}