package server;
import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.io.*;

import project2.Adapter.BuildAuto;
import project2.exceptions.MissingModelException;
import project2.model.Automobile;

import socket.SocketClientConstants;
import socket.SocketClientInterface;
public class 
    DefaultSocketClient 
    extends Thread implements SocketClientInterface,
                              SocketClientConstants {

	ObjectInputStream reader;
    ObjectOutputStream writer;
    Socket sock;
   
    public DefaultSocketClient(Socket sock) {       
            this.sock = sock;
            System.out.println("constructor--"+sock);
            try {
            	//InputStream in = sock.getInputStream();
            	//System.out.println("input is "+in);
            	this.writer = new  ObjectOutputStream(sock.getOutputStream());
              	 
            	this.reader = new ObjectInputStream(sock.getInputStream());
            	 System.out.println("--get in out done");
            	
            }
       	  catch (Exception e){
       	     if (DEBUG) System.err.println
       	       ("Unable to obtain stream to/from " );
       	     
       	  }
            System.out.println("constructer done");
    }//constructor

    public void run(){
    	boolean state=true;
       while(state){System.out.println("starting run....");
          handleSession();
          state=false;
          closeSession();
       }
    }//run
    public boolean openConnection(){

    	   
    	   
    	  return true;
    	}
    public void handleSession(){
    	System.out.println("creating thread for client");
		
		try {
			System.out.println("@@@@@@@@@\nwaiting to cmd");
			Object data;
			while((data= reader.readObject())!=null){
			String cmd = (String)data;
			
			System.out.println("cmd is "+cmd);
			if(cmd.equals("1a")) {
				makeModel();
			}

			else if(cmd.equals("1b")){
				System.out.println("!!!!!!\n\nrecving "+cmd);;
				selectAndConfig( );
			}
			
			else {
				System.out.println("!!!!!!\nrecving "+cmd);;
				BuildAuto ba = new BuildAuto();
				ArrayList<Automobile> list = ba.listModels();
				try {
					writer.writeObject(list);
					System.out.println("writing to writer: list size "+list.size());
					
				} 
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		//i++;	
		}catch(Exception e) {
			e.getStackTrace();
			closeSession();
		}
		/*
		finally{
			try {
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	
	
}

public void makeModel( ) {
	BuildCarModelOptions buildCarModelOptions = new BuildCarModelOptions();
	try {
		System.out.println("here");
		Properties props = (Properties) reader.readObject();
		//System.out.println(props.getProperty("Make"));
		Automobile auto = buildCarModelOptions.createForClient(props);
		
		writer.writeObject(auto);
		
		//carCRUD.putAutomotive(buildCarModelOptions.receiveFromMake(clientSocket, oos, ois));
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
}

public void selectAndConfig(){
	BuildAuto ba = new BuildAuto();
	ArrayList<String> models = ba.listModelNames();
	System.out.println("e.g. model-> \n\n\n"+models.get(0));
	try {
		writer.writeObject(models);
		String model = (String)reader.readObject();
		Automobile auto = ba.getAuto(model);
		writer.writeObject(auto);
	} 
	
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*
	carIO.sendAllAutomotiveNamesToUser(oos);
	String modelName = carIO.getSelectedModelNameFromUser(ois);
	System.out.println(modelName);
	carIO.sendOptionSetsToUser(modelName, oos);
	HashMap<String, String> optionChoices = carIO.getOptionChoicesFromUser(ois);
	CRUD carCRUD = (CRUD)carIO;
	carCRUD.setOptionChoices(modelName, optionChoices);
	Integer totalPrice = carCRUD.getTotalPrice(modelName);
	carIO.sendTotalPriceToUser(totalPrice, oos);*/ catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MissingModelException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		try {
			writer.writeObject("model not found");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}


    	public void sendOutput(String out){
    	  try {
    	    writer.writeObject(out);
    	  }
    	  catch (IOException e){
    	    if (DEBUG) System.out.println 
    	               ("Error writing ");
    	  }
    	}
        public void handleInput(String strInput){
            System.out.println(strInput);
    }       

    public void closeSession(){
       try {
          writer = null;
          reader = null;
          sock.close();
       }
       catch (IOException e){
         if (DEBUG) System.err.println
          ("Error closing socket to " );
       }       
    }

   
    
    

}// class DefaultSocketClient

