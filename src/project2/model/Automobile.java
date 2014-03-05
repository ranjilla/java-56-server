package project2.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;



import project2.exceptions.MissingChoiceException;
import project2.exceptions.MissingOptionException;
import project2.exceptions.MissingSetException;

public class Automobile implements Serializable{ //This class will represent the Model.
	private static final long serialVersionUID = -5105910834572049363L;
	private String model;
	private String make;
	private  float basePrice;
	private final float DEFAUL_BASE_PRICE =  18445;
	private ArrayList<OptionSet> opset ;
	/*
	public void init(){
		for(int i=0;i<opset.length;i++)
			opset[i] = new OptionSet();
			//for(int i=0;i<opt.length;i++)
			//opt[i] = new Option();
	}
	*/
	public Automobile(int OptionSetsize, String name)	{
	opset  = new ArrayList<OptionSet>(OptionSetsize);
	setModel(name);
	basePrice = DEFAUL_BASE_PRICE;
	}
	public Automobile(String name,int baseprice, int OptionSetsize){
		opset = new ArrayList<OptionSet>(OptionSetsize); 
		setModel(name);
		basePrice = baseprice;
	}
	
	
	public Automobile() {
		opset =new  ArrayList<OptionSet>(1);
		//init();*/
		
	}
	public ArrayList<OptionSet> getOpset(){
		return opset;
	}
	public void SetChoice(){
			try{
				System.out.println("\nThese are optionets, Please select one for each:");
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				for(OptionSet set:getOpset()){
					System.out.print(set.getName()+": ");
					String choice = stdIn.readLine().trim();
				
					setOptionChoice(set.getName(), choice);
				}
			System.out.println("!!!!total price: "+getTotalPrice());
			}
			catch (MissingSetException e) {
				System.err.println(e.getMessage());
				return;
			} catch (MissingChoiceException e) {
				System.err.println(e.getMessage());
				return;
			} catch (MissingOptionException e) {
				System.err.println(e.getMessage());
				return;
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	
	 public void deleteOptSet(int i){
	        try{
	        	opset.remove(i);
	        } 
	        catch (Exception e){
	        	e.printStackTrace();
	            System.err.printf("Error: %s", e);
	        }
	    }
	 
	/*
	 * Find
		i. Find OptionSet with name
		ii. Find Option with name (in context of OptionSet)
	 */
	public OptionSet findSet(String name){
		for(int i=0; i < opset.size();i++){
            if(opset.get(i).getName().equalsIgnoreCase(name)){
            	return opset.get(i);
            }
		}
        return  null;
	}
	public int findSetIndex(String name){
		for(int i=0; i < opset.size();i++){
            if(opset.get(i).getName().equalsIgnoreCase(name)){
            	return i;
            }
		}
        return  -1;
	}
	public Option findOpt(String setname,String optname) throws MissingSetException{
		OptionSet set = findSet(setname);
		if(set == null){
			throw new MissingSetException("no set found for "+setname);
		}
		ArrayList<Option> ops = set.getOpts();
		for(int i=0; i < ops.size();i++){
            if(ops.get(i).getName().equalsIgnoreCase(optname)){
            	return ops.get(i);
            }
		}
        return  null;

	}
	
	public String getOptionChoice(String setName) throws MissingSetException, MissingChoiceException{
		OptionSet set = findSet(setName);
		if(set==null)
			throw new MissingSetException("no set found for "+setName);
		Option choice = set.getChoice();
		if(choice==null)
			throw new MissingChoiceException("no choice set for "+setName);
		return choice.getName();
		
	}
	public float getOptionChoicePrice(String setName) throws MissingSetException, MissingChoiceException{
		OptionSet set = findSet(setName);
		if(set==null)
			throw new MissingSetException("no set found for "+setName);
		Option choice = set.getChoice();
		if(choice==null)
			throw new MissingChoiceException("no choice set for "+setName);
		
		return choice.getPrice();
	}
	public void setOptionChoice(String setname,String optname) throws MissingSetException, MissingOptionException{
		OptionSet set = findSet(setname);
		if(set == null){
			throw new MissingSetException("no set found for "+setname);
		}
		ArrayList<Option> ops = set.getOpts();
		for(int i=0; i < ops.size();i++){
            if(ops.get(i).getName().equalsIgnoreCase(optname)){
            	set.setChoice(ops.get(i));
            	System.out.println("set option "+set.getName()+"  to "+optname);
            	return;
            }
		}
        throw new MissingOptionException("no option found for name-"+optname+" in set "+setname);
	}
	public float getTotalPrice() throws MissingChoiceException{
		float sum=0;
		for(OptionSet set:opset){
			Option choice = set.getChoice();
			if(choice == null)
				throw new MissingChoiceException("no choice set for set:"+set.getName());
			sum += choice.getPrice();
		}
		return sum+basePrice;
	}
	
	private int findOptIndex(String setname,String optname){
		OptionSet set = findSet(setname);
		if(set == null){
			System.err.println("no set "+setname +" found");
			return -1;
		}
		ArrayList<Option> ops = set.getOpts();
		for(int i=0; i < ops.size();i++){
            if(ops.get(i).getName().equalsIgnoreCase(optname)){
            	return i;
            }
		}
        return  -1;

	}
	public void deleteOpt(String setname,String optname){
		OptionSet set = findSet(setname);
		if(set == null){
			System.err.println("no set "+setname +" found");
			return ;
		}
		ArrayList<Option> ops = set.getOpts();
		for(int i=0; i < ops.size();i++){
            if(ops.get(i).getName().equalsIgnoreCase(optname)){
            	ops.remove(i);
            	System.out.println(setname+"-"+optname+" removed");
            	return ;
            }
		}
       System.out.println("remove failed");
	}
	
	public synchronized float getBasePrice() {
		return basePrice;
	}
	public synchronized void setBasePrice(float price) {
		this.basePrice = price;
	}
	public synchronized OptionSet getOpSet (int index){
		if(index >=opset.size() || index <0)
			return null;
		return opset.get(index);

		
	}
	
	 public synchronized void addOptionSet(String name, int numOfOptions){   
		 opset.add(new OptionSet(name, numOfOptions));
	 }
	 public synchronized void addOptionSet(String name){   
		 opset.add(new OptionSet(name));
	 }
	 public synchronized void addOpt(String setName, String name, float price){
	       OptionSet set = findSet(setName);
	       if(set==null){
	    	   System.err.println("set not found->"+setName);
	    	   System.exit(1);
	       }
	       //System.err.println("set ->"+set);
	       //System.out.println("-: before add ,size now is "+set.getOpts().length);
	        set.addOpt(name, price);
	        //System.out.println("add success size now is "+set.getOpts().length);
	    }
	 public synchronized void addOpt(int setIndex, String name, float price){
	       OptionSet set = opset.get(setIndex);
	       if(set==null){
	    	   System.err.println("set not found-> index :"+setIndex);
	    	   System.exit(1);
	       }
	       //System.err.println("set ->"+set);
	       //System.out.println("-: before add ,size now is "+set.getOpts().length);
	        set.addOpt(name, price);
	        //System.out.println("add success size now is "+set.getOpts().length);
	    }
	 public synchronized void updateOpt(String setName, String optname, float price) throws MissingSetException{
	        OptionSet myset = findSet(setName);
	        Option myopt = findOpt(setName,optname);//.addOpt(name, price);
	        if(myopt == null){
	        	System.err.println("cannot find option "+optname);
	        	return;
	        }
	        myopt.update(optname, price);
	        System.out.println(optname+" updated->"+price);
	 }
	 public synchronized void updateOptSet(String setName,String newName) throws MissingSetException{
		 OptionSet myset = findSet(setName);
	        if(myset==null)
	        	throw new MissingSetException("no set "+setName);
	       
	        myset.setName(newName);
	 }
	 public  synchronized void print(){
		 
		 System.out.println("model :"+model+"\tbasePrice :"+basePrice);
		 for(OptionSet os:opset){
			 System.out.println("----------");
			 os.print();
		 }
	 }
	public synchronized void updateOpt(String setName, String optname, String price) throws MissingSetException {
		// TODO Auto-generated method stub
		try{
		int p= Integer.parseInt(price);
		updateOpt(setName,optname,p);
		}
		catch( NumberFormatException e){
			System.err.println("Error: invalid value of price, should be a number ");
		}
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	
}