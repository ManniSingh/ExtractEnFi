import java.io.BufferedReader;
import java.io.FileReader;


public class test {

	public static void main(String[] args) throws Exception {
		BufferedReader keys = new BufferedReader(new FileReader("input"));
		String key;
		while ((key = keys.readLine()) != null) {
			Parser.parse(key);
		}		
		keys.close();
		//System.out.println(Refinery.refine("rumors about questionable"));
	}

}
