package google;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.nodes.Document; 

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ExtractReview {
	
	//File name
	//static int i=1;
	
	
	
	public static void getLinks()throws Exception{
		BufferedReader reviews = new BufferedReader(new FileReader("/Users/manni/Google Drive/SIM/bigdata/group/data/reviews.links"));
		while(reviews.readLine()!=null){
			putReviews(reviews.readLine());		
		}
		
	}
	
	public static int putReviews(String link) throws Exception{
		try{
		HttpURLConnection con = (HttpURLConnection)new URL(link).openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:33.0) Gecko/20100101 Firefox/33.0");
	    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");	    
	    if(con.getResponseCode()!=200){
	    	System.out.println("not connected");
	    	return(1);
	    }
	    BufferedWriter out = null;
	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    Document doc = Jsoup.parseBodyFragment(org.apache.commons.io.IOUtils.toString(in));	    
	    for (Element paras : doc.getElementsByTag("p")){
	    	out = new BufferedWriter(new FileWriter("/Users/manni/Google Drive/SIM/bigdata/group/data/rtext",true));
	    	out.write(CleanGoogleData.getCleaned(paras.text().toLowerCase()));
	    	out.write(",");
	    	out.close();
	    }
	    System.out.println("page done");
	    in.close();
		return(0);
		//System.exit(0);
		}catch (Exception e) {
	        return(0);
	    } 
	}
	
	
	
	
}
