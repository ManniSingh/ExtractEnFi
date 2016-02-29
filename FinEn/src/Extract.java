import java.io.*;
import java.net.*;


public class Extract {

	public static void main(String[] args) throws Exception {
		String url="https://www.google.fi/search?q=";
	    String charset="UTF-8";
	    BufferedReader keys = new BufferedReader(new FileReader("input"));
	    String key;
	    while ((key = keys.readLine()) != null) {
	    //String key="pliis"; 
	    String params =" -sanakirja -wikipedia -youtube -translation";
	    String query = String.format("%s",URLEncoder.encode(key+params, charset));
	    String addr = null;
	    int i = 0;	    
	    BufferedWriter out = null;
	    while (i<=900){
	    	addr = url+ query +"&num=100&lr=lang_fi&start="+Integer.toString(i);
	    URLConnection con = new URL(addr).openConnection();
	    con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:33.0) Gecko/20100101 Firefox/33.0");
	    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	    System.out.println(addr);
	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
	    out = new BufferedWriter(new FileWriter("gresults/"+key,true));
	      String inputLine;
	      while ((inputLine = in.readLine()) != null) {
	      out.write(inputLine);
	      //out.write("\n");
	      }
	    i+=100;
	    in.close();
	    out.close();
	    
	    }	         
	      //Parser.parse("gresults/"+key);
	      System.out.println("key: "+key);
	      System.out.println("end");

	    }
	    keys.close();
	}
}
