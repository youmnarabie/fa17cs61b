public class wordSort {
	public static void main(String[] words) {
		sort(words, 0, words.length - 1);
		print(words);
	}

	public static void sort(String[] words, int startIndex, int endIndex) {
		while (startIndex < endIndex) {
			int k = indexOfLargest(words, startIndex, endIndex);
			String temp = words[k]; words[k] = words[endIndex]; words[endIndex] = temp;
			endIndex -= 1;
		}
	}

	public static void print(String[] words) {
		for (String s : words) {
			System.out.print(s + " ");
		}
	}

	public static int indexOfLargest(String[] words, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return endIndex;
		} else {
			int k = indexOfLargest(words, startIndex + 1, endIndex);
			if (words[startIndex].compareTo(words[k]) > 0) {
				return startIndex;
			} else {
				return k;
			}
		}
	}
}