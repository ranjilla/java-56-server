package project2.model;

import java.io.Serializable;
import java.util.ArrayList;



class OptionSet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3202608254778286360L;
	private  ArrayList<Option> opts;
	private Option choice = null;
	//private  Option[] opts;
	private String name;
	/*
	private void init(){
		for(int i=0;i<opts.length;i++)
		opts[i] = new Option();
	}
	*/
	protected OptionSet(String name, int optssize){
		opts= new  ArrayList<Option>(optssize); 
		setName(name);
	}
	protected OptionSet(String name){
		opts= new  ArrayList<Option>(); 
		setName(name);
	}
	protected void print() {
        System.out.println("Option :"+ name);
        for(Option o: opts){
            o.print();
        }
    }
	 protected void updateOpt(int i, String name, float price) {
	        try{
	            opts.set(i, new Option(name, price));
	        } 
	        catch (Exception e){
	        	e.printStackTrace();
	            System.err.printf("Error: %s", e);
	        }
	    }
	    
    
	/**
	    * Delete the option base on the specified index location. 
	    * @param i
	    */
	    protected void deleteOpt(int i){
	        try{
	        	opts.remove(i);
	        } 
	        catch (Exception e){
	        	e.printStackTrace();
	            System.err.printf("Error: %s", e);
	        }
	    }
	    /*
	    private void optsRemove(int index){
	    	Option[] n = new Option[opts.length - 1];
	        System.arraycopy(opts, index+1, n, index, opts.length - index-1);
	        System.out.println("%%%%% removed ");
	        opts=n;
	        for(Option a:opts)	
	        	System.out.println(a.toString());
		        
	    }*/
	    protected void setOpt(int i, String name, float price) 
	    {	
	    	try{
	    	opts.set(i, new Option(name, price));
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    /*
	    protected void putOpt(String name, float price)
	    {
	    	Option[] n = new Option[opts.length + 1];
	    	System.arraycopy(opts, 0, n, 0, opts.length );
	    	n[opts.length]=(new Option(name, price));
	    }*/
	    protected void addOpt(String name, float price){	
	    	opts.add(new Option(name, price));
	    }
	    protected ArrayList<Option> getOpts() {
			return opts;
		}

	    protected void setOpts(ArrayList<Option> opts) {
			this.opts = opts;
		}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	public Option getChoice() {
		return choice;
	}
	public void setChoice(Option choice) {
		this.choice = choice;
	}
}
/*
private void init(){
	for(int i=0;i<opset.length;i++){
		opset[i] = new OptionSet();
		for(int j=0;j<opset[i].opt.length;j++)
			opset[i].opt[j] = new Option();
	}
}
*/
class Option implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private float price;
	 protected Option() {
            
     }
        
    protected Option(String name){
        this.name = name;
    }
    protected void update(String name, float price){
    	this.name = name;
    	this.price = price;
    	
    }
    protected Option(float price){
        this.price = price;
    }
	protected Option(String name, float price) {
		this.name = name;
		this.price = price;
	}
	protected String getName() {
		return name;
	}
	
	protected void print(){
		System.out.println("["+name+","+price+"]");
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected float getPrice() {
		return price;
	}
	protected void setPrice(float price) {
		this.price = price;
	}
}
	