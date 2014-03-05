package project2.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import project2.exceptions.MissingPriceException;
import project2.model.Automobile;


public class FileUtil {

 public static void main(String[] args) {
		// TODO Auto-generated method stub
	String name  ="C:\\Users\\lily\\workspace\\Smart Phone\\config" ;
	 
	//readFile(name);
	}
 
 
 private static void readLine(Automobile auto, String line, BufferedReader buffer) throws MissingPriceException{
	 StringTokenizer st = new StringTokenizer(line,":");
	 
		 String key = st.nextToken();
		 if(key.equals("#")){
			 key = st.nextToken();
			 int num = Integer.parseInt(st.nextToken());
	        //System.out.println("############"+key+":"+num);
	        auto.addOptionSet(key, num);
	        String set = key;
	         for(int i=0; i < num; i++){
	        	try {
					line =  buffer.readLine();
					st = new StringTokenizer(line,":");
					
					key = st.nextToken();
					int price = Integer.parseInt(st.nextToken());
					//String val = st.nextToken();
					auto.addOpt(set, key,price );
					//System.out.println("\toption  "+key+":"+val);
				}catch(NoSuchElementException e){
					System.err.println("Error reading file, line is-- "+line);
					throw new MissingPriceException("no price in file for option "+key);
				}
	        	catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.err.println("Format Error reading input file");
					System.exit(0);
				}
	         }
	         
		 }
		 
		 
		 else{
			
			 String val = st.nextToken();
			// System.out.println("£¡£¡basic "+key+":"+val);
			 if(key.equalsIgnoreCase("Model Name"))
				 auto.setModel(val);
			 if(key.equalsIgnoreCase("Base Price"))
				 auto.setBasePrice(Integer.parseInt(val));
		 }
		 
			 
 		
	 
	 /*
     if(data[MODEL_NAME].equalsIgnoreCase("Model Name"))
     {   
         model.setName(data[NAME]);
     }
     else if(data[BASE_PRICE].equalsIgnoreCase("Base Price"))
     {
         model.setPrice(Integer.parseInt(data[PRICE]));
     }
     else if(data[OPTION].equalsIgnoreCase("Option"))
     {
         int numberOfoptions = Integer.parseInt(data[NUM_OF_OPTIONS].trim());
         model.addOptionSet(data[OPTION_SET], numberOfoptions);
         for(int i = 0; i < numberOfoptions; i++)
         {   
             try
             {   
                 String [] option = buffer.readLine().split(":");
                 model.addOptionToSet(data[OPTION_SET], 
                                      option[OPTION_NAME].trim(), 
                                      Integer.parseInt(option[OPTION_PRICE].trim()));
             } 
             catch (Exception e)
             {
                 System.out.println("Error 1 -- " + e.toString());
             }
         }
     }
 }*/
 
 }
public Automobile buildAutoObject( String filename,Automobile auto) 
 {
 //Reads the file and builds an Automotive.
 //Open the file using FileReader Object.
 //In a loop read a line using readLine method.
 //Tokenize each line using StringTokenizer Object
 //Each token is converted from String to Integer using parseInt method
 //Value is then saved in the right property of Automotive Object.
	BufferedReader br = null;   
	 try {
	    	br = new BufferedReader(new FileReader(filename));
	  	    String line = br.readLine();
	        int count=0;
	        while (line != null) {
	        	readLine(auto,line,br);
	        	
	            line = br.readLine();
	            
	        }
	        
	    } 
	 	catch(ArrayIndexOutOfBoundsException e){
	    	e.printStackTrace();
	    } catch(FileNotFoundException e){
			System.err.println("file : "+filename+" not found, program terminated");
			System.exit(0);
		}
	 	catch (MissingPriceException e) {
			System.err.println(e.getMessage());
			return null;
		}	
	 	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    
	// auto.print();
     return auto;
 }
/*
 public static void main(String [] args) {
	 String file = "C:\\Users\\lily\\Desktop\\input.txt";
	 Student a [] = new Student[40];
	 try {
		readFile(file,a);
	} catch (Exception e) {
		e.printStackTrace();
	}
	 for(int i=0; i<a.length; i++){
		  System.out.println("No. "+i);
   	  for(int j=0;j<5;j++){
   		  System.out.println(a[i].getSID()+"\t");
   	  }
	  }
 }
 */

}
