package project2.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import project2.model.Automobile;

public class FileIO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static Properties readFileToProperties(String filename)
			{
		Properties props = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(filename);
			props.load(in); //This loads the entire file in memory.
			String CarMake = props.getProperty("CarMake");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
/*
		HashSet<String> validnames = new HashSet<String>();
		validnames.addAll(Arrays.asList("Make", "Name", "Base Price",
				"Color", "Transmission", "Brakes", "Side Impact Air Bags",
				"Power Moonroof"));
		int optionNum = 0;
		while (inputFile.hasNext()) {
			String line = inputFile.nextLine();
			String[] info = line.split(":");
			String optionSetName = info[0].trim();
			if (validnames.contains(optionSetName)) {
				if (optionSetName.equals("Make")
						|| optionSetName.equals("Name")
						|| optionSetName.equals("Base Price")) {
					props.setProperty(optionSetName, info[1].trim());
				}

				else {
					optionNum++;
					props.setProperty("Option" + optionNum, optionSetName);
					String[] optStrs = info[1].trim().split(",");
					for (int i = 0; i < optStrs.length; i++) {
						String[] namePrice = optStrs[i].trim().split("/");
						if (namePrice.length != 2) {
							throw new ExceptionHandler(3,
									"The option format should be optionName/Price");
						}
						String optionName = namePrice[0];
						int price;
						try {
							price = Integer.valueOf(namePrice[1]);
						} catch (Exception e) {
							throw new ExceptionHandler(4,
									"Option price shall be an integer");
						}
						props.setProperty("OptionValue" + optionNum
								+ String.valueOf(Character.toChars(97 + i)),
								optionName);
						props.setProperty("OptionPrice" + optionNum
								+ String.valueOf(Character.toChars(97 + i)),
								String.valueOf(price));
					}
				}
			}
		}*/
		return props;
	}

	public static void serializeAuto(Automobile FordZTW,String serOut){
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(serOut));
			out.writeObject(FordZTW);
			out.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error: can not find serialization out path-"+serOut);
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: IOException "+e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	public static Automobile DeserializeAuto(String serOut){
		Automobile autoRead = null;
	try {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(serOut));
		
		autoRead = (Automobile) in.readObject();
	} catch (ClassNotFoundException e) {
		System.err.println("Error: "+e.getMessage());
		//e.printStackTrace();
		System.exit(0);
	} catch (IOException e) {
		System.err.println("Error: "+e.getMessage());
		//e.printStackTrace();
		System.exit(0);
	}
	return autoRead;
	
	}
}
