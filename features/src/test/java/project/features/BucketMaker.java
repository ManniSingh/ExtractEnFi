package project.features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BucketMaker {
	
	public static void doMakeBuckets(String para_dir,ArrayList<String> features) throws IOException{
		String relation = new String();
        File dir = new File(para_dir);
        File[] files = dir.listFiles();
        int i=1;
        for (File f : files) {
        	String[] text; 
        	String file_text = "";
            if((f.isFile()) & (!f.isHidden())) {               
                Scanner inputStream = new Scanner(new FileReader(f));
                while (inputStream.hasNext()) {
                	file_text+=inputStream.nextLine().toLowerCase();	
                }
                inputStream.close();
            }
            text=file_text.split(" ");
            for(String feature:features){
            	if(file_text.contains(feature)){
            		BufferedWriter b = new BufferedWriter(new FileWriter("/Users/manni/Google Drive/SIM/bigdata/group/data/bucket",true));
                	relation=doParsePos(i,feature);
                	int index1=-1;
                    int index2=-1;
                    for(int j=0;j<text.length;j++){
                    	if(text[j].equals(relation)){
                    		index2=j;
                    	}
                    	if(text[j].equals(feature)){
                    		index1=j;
                    	}
                    }
                    if(index1==3){
                    	StringBuilder buck = new StringBuilder();
                    	buck.append(text[0]);
                    	buck.append(" ");
                    	buck.append(text[1]);
                    	buck.append(" ");
                    	buck.append(text[2]);
                    	buck.append(" ");
                    	buck.append(feature);
                    	buck.append(" ");
                    	b.write(buck.toString());
                    	b.write(",");
                    	b.write(feature);
                    	b.write(",");
                    	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                    }
                    else if(index1==2){
                    	StringBuilder buck = new StringBuilder();
                    	buck.append(text[0]);
                    	buck.append(" ");
                    	buck.append(text[1]);
                    	buck.append(" ");
                    	buck.append(feature);
                    	buck.append(" ");
                    	b.write(buck.toString());
                    	b.write(",");
                    	b.write(feature);
                    	b.write(",");
                    	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                    	
                    }
                    else if(index1==1){
                    	StringBuilder buck = new StringBuilder();
                    	buck.append(text[0]);
                    	buck.append(" ");
                    	buck.append(feature);
                    	buck.append(" ");
                    	b.write(buck.toString());
                    	b.write(",");
                    	b.write(feature);
                    	b.write(",");
                    	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                    	
                    }
                    else if(index1==0 & text.length>3){
                    	StringBuilder buck = new StringBuilder();
                    	buck.append(text[1]);
                    	buck.append(" ");
                    	buck.append(text[2]);
                    	buck.append(" ");
                    	buck.append(text[3]);
                    	buck.append(" ");
                    	buck.append(feature);
                    	buck.append(" ");
                    	b.write(buck.toString());
                    	b.write(",");
                    	b.write(feature);
                    	b.write(",");
                    	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                    	
                    }
                    else if(index1==text.length-4){
                    	StringBuilder buck = new StringBuilder();
                    	buck.append(feature);
                    	buck.append(" ");
                    	buck.append(text[index1+1]);
                    	buck.append(" ");
                    	buck.append(text[index1+2]);
                    	buck.append(" ");
                    	buck.append(text[index1+3]);
                    	buck.append(" ");
                    	b.write(buck.toString());
                    	b.write(",");
                    	b.write(feature);
                    	b.write(",");
                    	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                    	
                    }
                    else if(index1==text.length-3){
                    	StringBuilder buck = new StringBuilder();
                    	buck.append(feature);
                    	buck.append(" ");
                    	buck.append(text[index1+1]);
                    	buck.append(" ");
                    	buck.append(text[index1+2]);
                    	buck.append(" ");
                    	b.write(buck.toString());
                    	b.write(",");
                    	b.write(feature);
                    	b.write(",");
                    	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                    	
                    }
                    else if(index1==text.length-2){
                    	StringBuilder buck = new StringBuilder();
                    	buck.append(feature);
                    	buck.append(" ");
                    	buck.append(text[index1+1]);
                    	buck.append(" ");
                    	b.write(buck.toString());
                    	b.write(",");
                    	b.write(feature);
                    	b.write(",");
                    	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                    	
                    }
                    else if(index1==text.length-1 & text.length>2){
                    	StringBuilder buck = new StringBuilder();
                    	buck.append(feature);
                    	buck.append(" ");
                    	buck.append(text[index1-1]);
                    	buck.append(" ");
                    	buck.append(text[index1-2]);
                    	buck.append(" ");
                    	buck.append(text[index1-3]);
                    	buck.append(" ");
                    	b.write(buck.toString());
                    	b.write(",");
                    	b.write(feature);
                    	b.write(",");
                    	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                    	
                    }
                    else{
                    	if(text.length>6){
                    		StringBuilder buck = new StringBuilder();
                    		buck.append(text[0]);
                    		buck.append(" ");
                    		buck.append(text[1]);
                    		buck.append(" ");
                    		buck.append(text[2]);
                    		buck.append(" ");
                    		buck.append(feature);
                    		buck.append(" ");
                    		buck.append(text[index1+1]);
                    		buck.append(" ");
                    		buck.append(text[index1+2]);
                    		buck.append(" ");
                    		buck.append(text[index1+3]); 
                    		buck.append(" ");
                    		b.write(buck.toString());
                    		b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                    	}
                    }
                    ////Testing
                    System.out.println("done");
                    ///Testing end
                    if(relation!=null){
                    	if(index2==3){
                    		StringBuilder buck = new StringBuilder();
                        	buck.append(text[0]);
                        	buck.append(" ");
                        	buck.append(text[1]);
                        	buck.append(" ");
                        	buck.append(text[2]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        }
                        else if(index2==2){
                        	StringBuilder buck = new StringBuilder();
                        	buck.append(text[0]);
                        	buck.append(" ");
                        	buck.append(text[1]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        }
                        else if(index2==1){
                        	StringBuilder buck = new StringBuilder();
                        	buck.append(text[0]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        }
                        else if(index2==0 & text.length>3){
                        	StringBuilder buck = new StringBuilder();
                        	buck.append(text[1]);
                        	buck.append(" ");
                        	buck.append(text[2]);
                        	buck.append(" ");
                        	buck.append(text[3]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        }
                        else if(index2==text.length-4){
                        	StringBuilder buck = new StringBuilder();
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2+1]);
                        	buck.append(" ");
                        	buck.append(text[index2+2]);
                        	buck.append(" ");
                        	buck.append(text[index2+3]);
                        	buck.append(" ");
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        }
                        else if(index2==text.length-3){
                        	StringBuilder buck = new StringBuilder();
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2+1]);
                        	buck.append(" ");
                        	buck.append(text[index2+2]);
                        	buck.append(" ");
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        }
                        else if(index2==text.length-2){
                        	StringBuilder buck = new StringBuilder();
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2+1]);
                        	buck.append(" ");
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        }
                        else if(index2==text.length-1 & text.length>2){
                        	StringBuilder buck = new StringBuilder();
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2-1]);
                        	buck.append(" ");
                        	buck.append(text[index2-2]);
                        	buck.append(" ");
                        	buck.append(text[index2-3]);
                        	buck.append(" ");
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        }
                        else{
                        	if(text.length>6){
                        	StringBuilder buck = new StringBuilder();
                        	buck.append(text[0]);
                        	buck.append(" ");
                        	buck.append(text[1]);
                        	buck.append(" ");
                        	buck.append(text[2]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2+1]);
                        	buck.append(" ");
                        	buck.append(text[index2+2]);
                        	buck.append(" ");
                        	buck.append(text[index2+3]); 
                        	b.write(buck.toString());
                        	b.write(",");
                        	b.write(feature);
                        	b.write(",");
                        	b.write(Integer.toString(SentimentAnalyzer.findSentiment(buck.toString())));
                        	
                        	}
                        }
                    } 
                    b.write(" ");
                    b.write("\n");
                    b.close();
                }         	
            }
        i++;
        
        ///Testing
        //if(i==240) System.exit(0);
        ////Testing end.
        
        } 
	}
	
	public static void doMakeBuckets2(String para_dir,ArrayList<String> features) throws Exception{
		String relation = new String();
        File dir = new File(para_dir);
        File[] files = dir.listFiles();
        int i=1;
        for (File f : files) {
        	String[] text; 
        	String file_text = "";
            if((f.isFile()) & (!f.isHidden())) {               
                Scanner inputStream = new Scanner(new FileReader(f));
                while (inputStream.hasNext()) {
                	file_text+=inputStream.nextLine().toLowerCase();	
                }
                inputStream.close();
            }
            text=file_text.split(" ");
            for(String feature:features){
            	if(file_text.contains(feature)){
                	relation=doParsePos(i,feature);
                	int index1=-1;
                    int index2=-1;
                    for(int j=0;j<text.length;j++){
                    	if(text[j].equals(relation)){
                    		index2=j;
                    	}
                    	if(text[j].equals(feature)){
                    		index1=j;
                    	}
                    }
                    if(index1==3){
                    	StringBuilder buck = new StringBuilder();
                    	StringBuilder query = new StringBuilder();
                    	buck.append(text[0]);
                    	buck.append(" ");
                    	buck.append(text[1]);
                    	buck.append(" ");
                    	buck.append(text[2]);
                    	buck.append(" ");
                    	buck.append(feature);
                    	buck.append(" ");
                    	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                    	Postgres.putTupple(query.toString());
                    }
                    else if(index1==2){
                    	StringBuilder buck = new StringBuilder();
                    	StringBuilder query = new StringBuilder();
                    	buck.append(text[0]);
                    	buck.append(" ");
                    	buck.append(text[1]);
                    	buck.append(" ");
                    	buck.append(feature);
                    	buck.append(" ");
                    	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                    	Postgres.putTupple(query.toString());
                    	
                    }
                    else if(index1==1){
                    	StringBuilder buck = new StringBuilder();
                    	StringBuilder query = new StringBuilder();
                    	buck.append(text[0]);
                    	buck.append(" ");
                    	buck.append(feature);
                    	buck.append(" ");
                    	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                    	Postgres.putTupple(query.toString());
                    }
                    else if(index1==0 & text.length>3){
                    	StringBuilder buck = new StringBuilder();
                    	StringBuilder query = new StringBuilder();
                    	buck.append(text[1]);
                    	buck.append(" ");
                    	buck.append(text[2]);
                    	buck.append(" ");
                    	buck.append(text[3]);
                    	buck.append(" ");
                    	buck.append(feature);
                    	buck.append(" ");
                    	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");                    	
                    	Postgres.putTupple(query.toString());
                    }
                    else if(index1==text.length-4){
                    	StringBuilder buck = new StringBuilder();
                    	StringBuilder query = new StringBuilder();
                    	buck.append(feature);
                    	buck.append(" ");
                    	buck.append(text[index1+1]);
                    	buck.append(" ");
                    	buck.append(text[index1+2]);
                    	buck.append(" ");
                    	buck.append(text[index1+3]);
                    	buck.append(" ");
                    	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                    	Postgres.putTupple(query.toString());
                    }
                    else if(index1==text.length-3){
                    	StringBuilder buck = new StringBuilder();
                    	StringBuilder query = new StringBuilder();
                    	buck.append(feature);
                    	buck.append(" ");
                    	buck.append(text[index1+1]);
                    	buck.append(" ");
                    	buck.append(text[index1+2]);
                    	buck.append(" ");
                    	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                    	Postgres.putTupple(query.toString());
                    }
                    else if(index1==text.length-2){
                    	StringBuilder buck = new StringBuilder();
                    	StringBuilder query = new StringBuilder();
                    	buck.append(feature);
                    	buck.append(" ");
                    	buck.append(text[index1+1]);
                    	buck.append(" ");
                    	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                    	Postgres.putTupple(query.toString());
                    }
                    else if(index1==text.length-1 & text.length>2){
                    	StringBuilder buck = new StringBuilder();
                    	StringBuilder query = new StringBuilder();
                    	buck.append(feature);
                    	buck.append(" ");
                    	buck.append(text[index1-1]);
                    	buck.append(" ");
                    	buck.append(text[index1-2]);
                    	buck.append(" ");
                    	buck.append(text[index1-3]);
                    	buck.append(" ");
                    	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                    	Postgres.putTupple(query.toString());
                    }
                    else{
                    	if(text.length>6){
                    		StringBuilder buck = new StringBuilder();
                    		StringBuilder query = new StringBuilder();
                    		buck.append(text[0]);
                    		buck.append(" ");
                    		buck.append(text[1]);
                    		buck.append(" ");
                    		buck.append(text[2]);
                    		buck.append(" ");
                    		buck.append(feature);
                    		buck.append(" ");
                    		buck.append(text[index1+1]);
                    		buck.append(" ");
                    		buck.append(text[index1+2]);
                    		buck.append(" ");
                    		buck.append(text[index1+3]); 
                    		buck.append(" ");
                    		query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");                        	
                    		Postgres.putTupple(query.toString());
                    	}
                    }
                    if(relation!=null){
                    	if(index2==3){
                    		StringBuilder buck = new StringBuilder();
                    		StringBuilder query = new StringBuilder();
                        	buck.append(text[0]);
                        	buck.append(" ");
                        	buck.append(text[1]);
                        	buck.append(" ");
                        	buck.append(text[2]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                        	Postgres.putTupple(query.toString());
                        }
                        else if(index2==2){
                        	StringBuilder buck = new StringBuilder();
                        	StringBuilder query = new StringBuilder();
                        	buck.append(text[0]);
                        	buck.append(" ");
                        	buck.append(text[1]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");                        	
                        	Postgres.putTupple(query.toString());
                        }
                        else if(index2==1){
                        	StringBuilder buck = new StringBuilder();
                        	StringBuilder query = new StringBuilder();
                        	buck.append(text[0]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                        	Postgres.putTupple(query.toString());
                        }
                        else if(index2==0 & text.length>3){
                        	StringBuilder buck = new StringBuilder();
                        	StringBuilder query = new StringBuilder();
                        	buck.append(text[1]);
                        	buck.append(" ");
                        	buck.append(text[2]);
                        	buck.append(" ");
                        	buck.append(text[3]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                        	Postgres.putTupple(query.toString());
                        }
                        else if(index2==text.length-4){
                        	StringBuilder buck = new StringBuilder();
                        	StringBuilder query = new StringBuilder();
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2+1]);
                        	buck.append(" ");
                        	buck.append(text[index2+2]);
                        	buck.append(" ");
                        	buck.append(text[index2+3]);
                        	buck.append(" ");
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");                        	
                        	Postgres.putTupple(query.toString());
                        }
                        else if(index2==text.length-3){
                        	StringBuilder buck = new StringBuilder();
                        	StringBuilder query = new StringBuilder();
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2+1]);
                        	buck.append(" ");
                        	buck.append(text[index2+2]);
                        	buck.append(" ");
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                        	Postgres.putTupple(query.toString());
                        }
                        else if(index2==text.length-2){
                        	StringBuilder buck = new StringBuilder();
                        	StringBuilder query = new StringBuilder();
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2+1]);
                        	buck.append(" ");
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");
                        	
                        }
                        else if(index2==text.length-1 & text.length>2){
                        	StringBuilder buck = new StringBuilder();
                        	StringBuilder query = new StringBuilder();
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2-1]);
                        	buck.append(" ");
                        	buck.append(text[index2-2]);
                        	buck.append(" ");
                        	buck.append(text[index2-3]);
                        	buck.append(" ");
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");                        	
                        	Postgres.putTupple(query.toString());
                        }
                        else{
                        	if(text.length>6){
                        	StringBuilder buck = new StringBuilder();
                        	StringBuilder query = new StringBuilder();
                        	buck.append(text[0]);
                        	buck.append(" ");
                        	buck.append(text[1]);
                        	buck.append(" ");
                        	buck.append(text[2]);
                        	buck.append(" ");
                        	buck.append(feature);
                        	buck.append(" ");
                        	buck.append(text[index2+1]);
                        	buck.append(" ");
                        	buck.append(text[index2+2]);
                        	buck.append(" ");
                        	buck.append(text[index2+3]); 
                        	query.append("insert into bucket values ("+Integer.toString(i)+", '"+buck.toString()+"', '"+feature+"', '"+Integer.toString(SentimentAnalyzer.findSentiment(buck.toString()))+"')");                        	
                        	Postgres.putTupple(query.toString());
                        	}
                        }
                    } 
                }         	
            }
        i++;
        
        ///Testing
        //if(i==240) System.exit(0);
        ////Testing end.
        
        } 
	}
	
	
	public static String doParsePos(int i,String feature) throws IOException{		        
        File pos_file = new File("/Users/manni/Google Drive/SIM/bigdata/group/data/pos/"+Integer.toString(i));
        Pattern word1 = Pattern.compile("\\(\\w+");
        Pattern word2 = Pattern.compile("\\s\\w+");
        String line = new String();
        StringBuilder ns = new StringBuilder();
        String fs = new String();
        Scanner inputStream = new Scanner(new FileReader(pos_file));
        while (inputStream.hasNext()) {
           line=inputStream.nextLine();
           if(line.toLowerCase().startsWith("xcomp")){
        	   Matcher m1 = word1.matcher(line);
        	   Matcher m2 = word2.matcher(line);
        	   if(m1.find()){
        		   ns.append(m1.group(0));
        	   }
        	   if(m2.find()){
        		   ns.append(m2.group(0));
        	   }
        	   fs=ns.toString();
        	   fs=fs.replaceAll("[^\\w\\s]","");
        	   if(!fs.contains(feature)){
        		   inputStream.close();
        		   return(null);
        	   }
        	   //fs=fs.replaceAll(feature,"");
        	   inputStream.close();
        	   return(fs);
           }
           //xcomp(struggled-35, keep-37)
        }
        inputStream.close(); 
        return(null);
	}
	
	public static void main(String args[]) throws Exception{
		
		//Loading features..
		ArrayList<String> features = new ArrayList<String>();
        File feature_f = new File("src/test/java/features");
        Scanner featurestream = new Scanner(new FileReader(feature_f));
        while (featurestream.hasNext()) {
        	features.add(featurestream.nextLine().toLowerCase());     	
        }
        featurestream.close();
        Postgres.getConnection();
        Postgres.createTable("create table bucket (id int, para varchar(1000000), feature varchar, sentiment int)");       
        doMakeBuckets2("/Users/manni/Google Drive/SIM/bigdata/group/data/para/",features);
        Postgres.st.close();
        Postgres.db.close();
		Postgres.closeConnection();
        
	}
	
	
	
}
