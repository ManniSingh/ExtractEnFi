package project.features;


import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJson {
	public static void readJson() throws Exception{
		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(new FileReader("/Users/manni/Google Drive/SIM/bigdata/group/dataset/reviews.json"));

		  for (Object o : a)
		  {
		    JSONObject id = (JSONObject) o;

		    String review = (String) id.get("reviewText");
		    System.out.println(review);
		  }
	}
	public static void main(String args[]) throws Exception{
		readJson();
	}
	

}
