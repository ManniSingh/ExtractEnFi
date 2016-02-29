import java.io.IOException; 
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ParaParse {
	public static String pParse(String ref) throws Exception { 
		Document page = null;
		String para = null;
        try {
        	System.out.println("here"+ref);
        	URL url = new URL(ref);
        	page = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(3000).get();
        } catch (IOException e) {
        	System.out.println("here"+ref);
        	e.printStackTrace();
        } 
        Elements parags = page.getElementsByTag("p");
        for (Element parag : parags) {
        	para += parag.data() +"\n";
        }       
        return para;
        
	}
}
