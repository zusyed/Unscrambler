import java.util.*;
import java.io.*;

public class Unscrambler {
	Map<String, Set<String>> words;

	public Unscrambler() {
		words = new TreeMap<String, Set<String>>();
	}

	public void useDictionary(String wordList) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(wordList));

			String line;
			while ((line = reader.readLine()) != null) {
				String sorted = sortWords(line);

				if (words.containsKey(sorted))
					words.get(sorted).add(line);
				else {
					Set<String> anagrams = new HashSet<String>();
					anagrams.add(line);
					words.put(sorted, anagrams);
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("General I/O exception: " + e.getMessage());
		}
	}

	public Set<String> getAllWords(String word) {
		String wordSorted = sortWords(word);
		Set<String> answers = new HashSet<String>();

		if (words.containsKey(wordSorted)) {
			answers = words.get(wordSorted);
		}

		return answers;
	}

	public Set<String> unscramble(String word) {
		String sub;
		Set<String> substrings = new HashSet<String>();
		Set<String> correctWords = new HashSet<String>();

		int i, c, length;

		length = word.length();

		for (c = 0; c < length; c++) {
			for (i = 1; i <= length - c; i++) {
				sub = word.substring(c, c + i);
				substrings.add(sub);
			}
		}

		for (String substring : substrings) {
			for (String correctWord : getAllWords(substring)) {
				correctWords.add(correctWord);
			}
		}

		return correctWords;
	}

	public String sortWords(String str) {
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}
