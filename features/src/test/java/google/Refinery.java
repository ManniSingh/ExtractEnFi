package google;


import org.apache.commons.lang3.text.WordUtils;

import  me.champeau.ld.UberLanguageDetector;



public class Refinery {
	public static boolean refine(String addr,String key) throws Exception {
		UberLanguageDetector detector = UberLanguageDetector.getInstance();
		
			addr = addr.toString();
			addr = addr.replaceAll("[^A-Za-z0-9 ]", "");			
			key = key.replaceAll("[^A-Za-z0-9 ]", "");
			addr = addr.replaceAll(key," ");
			addr = WordUtils.capitalize(addr, null);
			key = WordUtils.capitalize(key, null);
			addr = addr.replaceAll(key," ");
			//key = java.net.URLDecoder.decode(key, "UTF-8");
			//addr = addr.replaceAll(key.toUpperCase()," ");
			String language = detector.detectLang(addr);
			System.out.println(language+"  "+addr);
			if (language.equals("fi"))
				return false;
				return true;
			
		/*String url= "https://translate.google.com/#fi/en/";
		String charset="UTF-8";
		String key = null;
		String query = String.format("%s",URLEncoder.encode(key, charset));
		URLConnection con = new URL(url+query).openConnection();
	    con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:33.0) Gecko/20100101 Firefox/33.0");
	    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	    */
	}
	
}

