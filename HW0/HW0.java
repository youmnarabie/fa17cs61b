public class HW0 {
	public static void main(String[] args) {
		int[] one = {1, 2, 3, 4, 20};
		int[] two = {5, 1, 0, 3, 6};
		int[] three = {-6, 3, 10, 200};
		int[] four = {8, 2, -1, 15};
		int[] five = {-6, 2, 5};
		int[] six = {-6, 2, 4}; 
		System.out.println(max(one)); //20
		System.out.println(threeSum(two)); //true
		System.out.println(threeSum(three)); //true
		System.out.println(threeSum(four)); //true
		System.out.println(threeSum(five)); //false
		System.out.println(threeSumDistinct(three)); //false
		System.out.println(threeSumDistinct(four)); //false
		System.out.println(threeSumDistinct(six)); //true
	}


	/** Return the maximum value of the integer array NUMBERS. */
	public static int max(int[] numbers) {
		int highest = -1000000;
		for (int i = 0; i < numbers.length; i += 1) {
			if (numbers[i] > highest) {
				highest = numbers[i];
			}
		}
		return highest;
	}

	/** Return true if there are three values in VALUES that add up to 0. */
	public static boolean threeSum(int[] values) {
		for (int i : values) {
			for (int j : values) {
				for (int k : values) {
					if (i + j + k == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/** Return TRUE if there are three distinc values in VALUES that add up to 0. */ 
	public static boolean threeSumDistinct(int[] values) {
		for (int i : values) {
			for (int j : values) {
				for (int k : values) {
					if ((i + j + k == 0) && (i != j && j != k && i != k)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}