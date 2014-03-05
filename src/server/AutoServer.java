package server;

import java.util.List;
import java.util.Properties;

import project2.model.Automobile;

public interface AutoServer {
	//Socket clientSocket, ObjectOutputStream oos, ObjectInputStream ois
	public Automobile createForClient(Properties prop);
	public List<String> listModelNames();	
}
