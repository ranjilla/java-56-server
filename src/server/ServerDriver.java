package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import socket.SocketClientConstants;


public class ServerDriver {

	//static IO carIO = new BuildAuto();
	//static CRUD carCRUD = (CRUD) carIO;
	
	public ServerDriver(Socket clientSocket){
		
	}

	public static void main(String[] args)  {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(SocketClientConstants.AUTO_PORT);
			
			while (true) {
				try {
					System.out.println("Listening...");
					Socket clientSocket = serverSocket.accept();
					System.out.println("accepted"+clientSocket);
					DefaultSocketClient client = new DefaultSocketClient(clientSocket);
					client.start();;
				} catch (IOException e) {
					
					System.err.println("Accept failed");
					System.exit(1);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

	

}