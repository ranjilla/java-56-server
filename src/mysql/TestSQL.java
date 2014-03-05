package mysql;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.htmlunit.corejs.javascript.ScriptableObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import project2.Adapter.BuildAuto;
import project2.model.Automobile;
import client.CarModelOptionsIO;
import client.SelectCarOption;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
/*
 * Ying Li liying@cmu.edu
 * 
 */
public class TestSQL {
	String baseURLi  = "http://dinebombaygarden.com/";
	//String baseURL = "http://allspicerestaurant.com/";
	String menuURL= null;
	String db = "junkdb";
	MySQL ms = null;
	ArrayList<String> menus= new ArrayList<String>();
	private String contactURL;
	private String title;
	private String address;
	private String phone;
	private int rid;
	
	public static void main(String[] args){
		
	}
	
	public TestSQL(){
		this.ms = new MySQL();
		
		
	}
	 public void handleSession(){
		 BufferedReader inputReader = null;

			inputReader = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				try {
					System.out.println("\n***********interaction console***********");
					System.out.println("choose 1a or 1b: \n" +
							"(1a) add a model\n" +
							"(1b) list model names and set choise\n"+
							"(1c) list models\n");
					System.out.print("$>");
					String input = inputReader.readLine();
					 
						
					
					 if(input.equals("1a")){
						CarModelOptionsIO modelIO= new CarModelOptionsIO(client);
						System.out.println("entery your file name: ");
						String file = inputReader.readLine();
						modelIO.loadModelToServer(file);
					 }
					 else if(input.equals("1b")){
						SelectCarOption so = new SelectCarOption(client);
						//so.listModels();
						so.selectOptions();
					 }
					 else if(input.equals("1c")){
							SelectCarOption so = new SelectCarOption(client);
							//so.listModels();
							 ArrayList<Automobile> list = so.getListModels();
							 for(Automobile each: list){
								 System.out.println(each.getModel());
								 
							 }
						 }
					 else{
						 System.out.println("invalid, try again");
							continue;
					 }
				}catch(Exception e){
					e.printStackTrace();
				}
			}
	}

	
	
	
}

	

