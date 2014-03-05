package project2.Adapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import project2.exceptions.MissingModelException;
import project2.model.*;
public abstract class ProxyAutomobile {
	protected static LinkedHashMap<String,Automobile> models = new LinkedHashMap<String,Automobile>();

	public  Automobile getAuto(String name) throws MissingModelException {
		if(name==null || !models.containsKey(name))
			throw new MissingModelException("no model existing for "+name);
		else
			return models.get(name);
	}
	
	public  void  addAuto(Automobile a1) {
		models.put(a1.getModel(), a1);
		Set<String> set = models.keySet();
		for(String each: set){
			System.out.println("---key is :"+each);
			//ret.add(each);
		}
	}

	
	
}



