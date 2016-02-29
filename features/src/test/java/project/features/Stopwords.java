package project.features;


import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Stopwords {
	
	public static String[] stopwords = {"a","the","is","but","as","an","be","been","am"};
			
	public static Set<String> stopWordSet = new HashSet<String>(Arrays.asList(stopwords));
	
	
	public static boolean isStopword(String word) {
		if(word.length() < 2) return true;
		if(word.charAt(0) >= '0' && word.charAt(0) <= '9') return true; //remove numbers, "25th", etc
		if(stopWordSet.contains(word)) return true;
		else return false;
	}
	
	
	public static String removeStopWords(String string) {
		String result = "";
		String[] words = string.split("\\s+");
		for(String word : words) {
			if(word.isEmpty()) continue;
			if(isStopword(string)) continue; //remove stopwords
			result += (word+" ");
		}
		return result;
	}	
	
}

