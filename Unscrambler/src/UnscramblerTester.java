import java.util.*;

public class UnscramblerTester {
	public static void main(String[] args)
	{
		Unscrambler unscrambler = new Unscrambler();
		
		unscrambler.useDictionary("../words.txt");
		
		Set<String> unscrambledWords = unscrambler.unscramble("apple");
		List<String> list = new ArrayList<String>();
		list.addAll(unscrambledWords);
		Collections.sort(list, new StringLengthComparator());
		
		for (String unscrambled : list)
			System.out.println(unscrambled);
	}
}
