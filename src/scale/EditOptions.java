package scale;

import project2.Adapter.BuildAuto;

public class EditOptions implements Runnable {
	String model;
	String OptSet;
	String Opt;
	float price;
	String newSetName;
	BuildAuto ba;
	boolean sleep;
	/**
	 * can be used to edit Options for a given model in its own thread
	 * @param args
	 */
	
	public EditOptions(boolean sleep,BuildAuto ba,String model, String OptSet,String newSetName, String opt,float newprice){
		this.sleep = sleep;
		this.ba = ba;
		this.model = model;
		this.OptSet = OptSet;
		this.Opt = opt;
		this.newSetName = newSetName;
		this.price = newprice;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("------- in thread "+Thread.currentThread());
		synchronized (ba){
		ba.updateOptionPrice(model, OptSet, Opt, price);
			if(sleep){
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("sleeping for 100 sec");
				
			}
		}
		//ba.updateOptionSetName(model, OptSet, newSetName);
		
		
	}

}
