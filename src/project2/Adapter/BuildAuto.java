package project2.Adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Properties;

import project2.exceptions.MissingModelException;
import project2.exceptions.MissingSetException;
import project2.model.Automobile;
import project2.util.FileUtil;
import server.AutoServer;

public class BuildAuto extends ProxyAutomobile implements
CreateAuto, UpdateAuto, AutoServer,DeleteAuto{

	
	@Override
	public  void updateOptionSetName(String Modelname, String OptionSetname,
			String newName) {
		System.out.println("****updating "+OptionSetname+" to "+newName);
		try {
			Automobile auto = getAuto(Modelname);
			auto.updateOptSet(OptionSetname,newName);
			
		} catch (MissingModelException e) {
			System.err.println("no model existing for "+Modelname);
			
			System.err.println(e.getMessage());
			return;
		} catch (MissingSetException e) {
			System.err.println(e.getMessage());
			return;
		}
		
	}
	@Override
	public  ArrayList<String> listModelNames() {
		ArrayList<String> ret = new ArrayList<String>();
		System.out.println("requring list");
		Set<String> set = models.keySet();
		for(String each: set){
			System.out.println("key is :"+each);
			ret.add(each);
		}
		return ret;
		//return new HashSet<String>( models.keySet() );

		
	}
	public  ArrayList<Automobile> listModels() {
		ArrayList<Automobile> ret = new ArrayList<Automobile>();
		System.out.println("requring list");
		Set<Entry<String, Automobile>> set = models.entrySet();
		for(Entry<String, Automobile> each: set){
			System.out.println("key is :"+each);
			ret.add(each.getValue());
		}
		return ret;
		//return new HashSet<String>( models.keySet() );

		
	}
	

	@Override
	public  void updateOptionPrice(String Modelname, String Optionset,
			String Optionname, float newprice) {
		Automobile auto;
		try {
			auto = getAuto(Modelname);
			auto.updateOpt(Optionset, Optionname, newprice);
			System.out.println("****updated "+Optionset+":"+Optionname+" to "+newprice);
			
		} catch (MissingModelException e) {
			System.err.println("no model existing for "+Modelname);
			
			e.printStackTrace();
			System.exit(0);
		} catch (MissingSetException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		
		
		
	}

	@Override
	public void BuildAuto(String filename) {
		System.out.println("-------build auto-------------");
		FileUtil fu= new FileUtil();
		Automobile auto = new Automobile();
		fu.buildAutoObject(filename, auto);
		addAuto(auto);
		
		
	}
	@Override
	public Automobile createForClient(Properties props) {
		if(props==null)
			return null;
		String CarMake = props.getProperty("CarMake");
		Automobile auto = new Automobile();
		
		Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
		for (int i = 1;; i++) {
			String optionSetName = props.getProperty("Option" + i);
			if (optionSetName != null) {
				auto.addOptionSet(optionSetName);
				for (int j = 0;; j++) {
					String optionName = props.getProperty("OptionValue" + i
							+ String.valueOf(Character.toChars(97 + j)));
					if (optionName == null) {
						break;
					}
					float price = Integer
							.valueOf(props.getProperty("OptionPrice" + i
									+ String.valueOf(Character.toChars(97 + j))));
					auto.addOpt(optionSetName, optionName, price);
				}
			} 
			else {
				break;
			}
		}

		
		if (props.getProperty("Make") != null) {
			auto.setMake(props.getProperty("Make"));
		}

		if (props.getProperty("Model") != null) {
			auto.setModel(props.getProperty("Model"));
		}

		if (props.getProperty("Base Price") != null) {
			auto.setBasePrice(Integer.parseInt(props
					.getProperty("Base Price")));
		}
		addAuto(auto);
		return auto;
	}
	@Override
	public void printAuto(String Modelname) {
		System.out.println("-------entering printAuto method-------------");
		Automobile auto;
		try {
			auto = getAuto(Modelname);
			auto.print();
		} catch (MissingModelException e) {
			System.err.println("no model existing for "+Modelname);
			System.exit(0);
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void deleteAuto(String model) {
		Automobile auto;
		try {
			auto = getAuto(model);
			models.remove(model);
			System.out.println("****deleted "+model);
			
			
		} catch (MissingModelException e) {
			System.err.println("no model existing for "+model);
			
			e.printStackTrace();
			System.exit(0);
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} 
		
		
	}



	
}