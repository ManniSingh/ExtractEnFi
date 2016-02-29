package project.features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.TypedDependency;



public class Parser{
	static LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz","-maxLength", "80", "-retainTmpSubcategories");
	
	
	public static void putParas(String reviews,LexicalizedParser lp) throws Exception{
		Scanner scan = new Scanner(new File(reviews));
		scan.useDelimiter(",");
		String s = new String();
		String o = new String();
		int i=1;
		while(scan.hasNext()){
			BufferedWriter pos = new BufferedWriter(new FileWriter("/Users/manni/Google Drive/SIM/bigdata/group/data/pos/"+Integer.toString(i),true));
			BufferedWriter para = new BufferedWriter(new FileWriter("/Users/manni/Google Drive/SIM/bigdata/group/data/para_ns/"+Integer.toString(i),true));
			BufferedWriter para_o = new BufferedWriter(new FileWriter("/Users/manni/Google Drive/SIM/bigdata/group/data/para/"+Integer.toString(i),true));
			//s = Stopwords.removeStopWords(scan.next());
			o = scan.next().toLowerCase();
			para_o.write(o);
			s = getStopRemoved(o);
			para.write(s);
			para.write("\n\n");
			for(TypedDependency rel:PosTagger.getRelation(s,lp)){
				pos.write(rel.toString());
				pos.write("\n");
			}
			para_o.close();
			pos.close();
			para.close();
			s = null;
			o=null;
			System.out.println("pos done");
			i++;
        }
        scan.close();       
	}
	
	
	
	public static void putParasDB(String reviews,LexicalizedParser lp) throws Exception{
		Scanner scan = new Scanner(new File(reviews));
		scan.useDelimiter(",");
		String s = new String();
		String o = new String();
		int i=1;
		while(scan.hasNext()){
			StringBuilder poser = new StringBuilder();
			StringBuilder query = new StringBuilder();
			o = scan.next().toLowerCase();
			s = getStopRemoved(o);
			for(TypedDependency rel:PosTagger.getRelation(s,lp)){
				poser.append(rel.toString() + "\n");
			}
			query.append("insert into pos values ("+Integer.toString(i)+", '"+o+"', '"+s+"', '"+google.CleanGoogleData.getCleaned2(poser.toString())+"')");
			System.out.println(query);
			Postgres.putTupple(query.toString());
			s = null;
			o=null;
			System.out.println("pos done");
			i++;			
		}
		
		scan.close();
		
	}
	public static String[] stopwords = {"a","the","is","but","as","an","be","been","am","and","but","yet"};
	public static List<String> stop_words = Arrays.asList(stopwords);
	
	public static String getStopRemoved(String para){
		ArrayList<String> tokenList = new ArrayList<String>(Arrays.asList(para.split(" ")));
		tokenList.removeAll(stop_words);
		StringBuilder build = new StringBuilder();
		for(String s:tokenList){
			build.append(s+" ");
		}
		return(build.toString());	
	}
	
	
	public static void main(String args[]) throws Exception{
		Postgres.getConnection();
		Postgres.createTable("create table pos (id int, para varchar(1000000), para_ns varchar(1000000), pos varchar(1000000))");
		putParasDB("/Users/manni/Google Drive/SIM/bigdata/group/data/rtext",lp);		
		Postgres.st.close();
		Postgres.closeConnection();
	}
}
