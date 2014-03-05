package server;

import java.util.Properties;

import project2.Adapter.BuildAuto;
import project2.model.Automobile;
import project2.util.FileIO;
import project2.util.FileUtil;

public class BuildCarModelOptions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties props = FileIO.readFileToProperties("./car.properties");
		BuildAuto ba = new BuildAuto();
		Automobile auto = ba.createForClient(props);
		ba.addAuto(auto);
		auto.print();
	}
	
	public Automobile createForClient(Properties props){
		BuildAuto ba = new BuildAuto();
		Automobile auto = ba.createForClient(props);
		System.out.println("model made: ");
		auto.print();
		return auto;
	}

}
