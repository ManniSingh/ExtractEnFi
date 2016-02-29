import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileWriter;
import java.io.IOException; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Parser {
	//public static void main(String args[]) throws Exception { 
	public static void parse(String key) throws Exception { 
		Document htmlFile = null;
        try {
        	htmlFile = Jsoup.parse(new File("gresults/"+key), "UTF-8");
        } catch (IOException e) {
        	e.printStackTrace();
        } 
        String title = htmlFile.title();
        BufferedWriter out = new BufferedWriter( new FileWriter("out/"+key+".doc"));
        out.write("Haku sana : " + title +"\n\n");
        //Elements results = htmlFile.getElementsByClass("g");
        Elements refClasses = htmlFile.getElementsByClass("r");
        Elements snipClasses = htmlFile.getElementsByClass("st");
        String snip = null;
        String link = null;
        String a = null;
        int i=0;
        final int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
        for (Element refParent: refClasses) {  
        	//System.out.println("notmatch: "+i);
        	for (Element refChild : refParent.getElementsByTag("a")){
        		a=refChild.text();
        		if (i<snipClasses.size()){
        			snip=snipClasses.get(i).text();
        			Pattern find = Pattern.compile("(\\w*\\s*"+key+"\\s\\w+)|(\\w+\\s"+key+"\\W?)",flags);
        			Matcher smatch = find.matcher(snip);
        			//System.out.println("otsikko: "+a);
        			//System.out.println("val: "+smatch.find());
        			if (smatch.find()){
        				//System.out.println("match: "+i);
            			//System.out.println("matched_snip: "+snip);
            			//System.out.println("trimmed_snip: "+smatch.group());
            			//System.out.println("English: "+ Refinery.refine(smatch.group()));
        				if (Refinery.refine(smatch.group(),key)){
        				i++; 
            			continue;
        				}
        				else {
        					out.write((i+1) + "\n");
                    		out.write("Otsikko: "+a+"\n\n");
                    		writeDate(snip,out);
                    		link = refChild.attr("href");
                    		String link2 = java.net.URLDecoder.decode(link, "UTF-8");
                    		String ref = link2.replace("/url?q=", "").split("&sa=")[0];
                    		//out.write(ParaParse.pParse(ref)+"\n\n");
                    		out.write("Viite: "+ref+"\n\n");
                    		out.write("Genre: "+getGenre(link2)+"\n\n\n\n");
        				}
        			}
        			//System.out.println("unmatched_snip: "+snip);        			
        		}   
        		i++; 
        	}  
        	//out.write("\n\n");
        }       
        out.close();
        System.out.println("Parser end");
	}
	
	public static String getGenre(String q){
		if (q.contains("blog")){
			return "blog";
		}
		else if (q.contains("keskustelu")){
			return "keskustelu";
		} 
		else if (q.contains("facebook")){
			return "keskustelu";
		} 
		else if (q.contains("suomi24")){
			return "keskustelu";
		} 
		else if (q.contains("uutiset")){
			return "uutiset";
		} 
		else {
			return "other";
		}
	}

    public static void writeDate(String snip, BufferedWriter out) throws Exception{
    	Pattern gDate = Pattern.compile("\\.\\.\\.\\s\\w+");
		Pattern iDate = Pattern.compile("\\d+\\.\\d+\\.\\d+");
		Matcher gMatch = gDate.matcher(snip);
		Matcher iMatch = iDate.matcher(snip);
        //System.out.println(m.find());
		if (gMatch.find()){
		out.write("Päivä: "+snip.split("\\.\\.\\.")[0]+"\n\n");
		//System.out.println("Päivä: "+snip.split("\\.\\.\\.")[0]+"\n\n");
		}
		else if (iMatch.find()){
			//System.out.println(iMatch.group()+"\n\n");
			out.write("Päivä: "+iMatch.group()+"\n\n");
		}
		else {
		out.write("Päivä: Ei tiedossa"+"\n\n"); 
		}
		out.write(snip+"\n\n");
    }

}     