package google;

public class CleanGoogleData {
	public static String getCleaned(String para){
		StringBuilder build = new StringBuilder();
		for(String word:para.split(" ")){			
			build.append(word.replaceAll("\\W",""));
			build.append(" ");
		}
		return(build.toString());
	}
	public static String getCleaned2(String para){
		StringBuilder build = new StringBuilder();
		for(String word:para.split(" ")){			
			build.append(word.replaceAll("'",""));
			build.append(" ");
		}
		return(build.toString());
	}
}
