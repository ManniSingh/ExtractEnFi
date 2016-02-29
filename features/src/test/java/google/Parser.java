package google;

import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileWriter;
import java.io.IOException; 


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Parser {
	public static void parse(String key) throws Exception { 
		Document htmlFile = null;
        try {
        	htmlFile = Jsoup.parse(new File("/Users/manni/Google Drive/SIM/bigdata/group/data/"+key), "UTF-8");
        } catch (IOException e) {
        	e.printStackTrace();
        } 
        BufferedWriter out = new BufferedWriter(new FileWriter("/Users/manni/Google Drive/SIM/bigdata/group/data/"+key+".links"));
        //Elements results = htmlFile.getElementsByClass("g");
        Elements refClasses = htmlFile.getElementsByClass("r");
        String a = null;
        //final int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
        for (Element refParent: refClasses) {  
        	for (Element refChild : refParent.getElementsByTag("a")){
        		a=refChild.attr("href");
        		out.write(a);
        		out.write("\n"); 
        	} 
        }
        out.close();
        System.out.println("Parser end");
	}
}     