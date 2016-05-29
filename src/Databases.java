/*
 * Singleton class of all databases needed for the Database Search Strategies
 * 
 * downloads and stores all of the words from txt files into
 * hashtables for easy access
 * 
 * Written By: Shannon Wing, Kelly Finke, Kiara Wahschafft
 * Date: 5/31/16
 */

import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class Databases {
	
	//check about sizing for each
	private static Hashtable<Integer, String> ingNotProgressiveTenseD ;
	private static Hashtable<Integer, String> edNotPastTenseD;
	private static Hashtable<Integer, String> passiveVoiceD ;
	private static Hashtable<Integer, String> irregularPastTenseD;
	
	public static Hashtable<Integer, String> getEdNotPastTenseD()
	{
		if (edNotPastTenseD == null)
		{
		   File file = new File("EdWordsNotPastTense.txt");
	       Scanner input = null;
	       try
	       {
	           input = new Scanner(file);
	       }
	        
	       catch (FileNotFoundException ex) //returns error if input.txt is not found
	       {
	         System.out.println("*** Cannot open file ***");
	         System.exit(1); //quit the program
	       }
	       
	       edNotPastTenseD = new Hashtable<Integer, String>();
	     
	       while(input.hasNextLine())
	       {
	     	  try {
		        	String in = input.nextLine();
		        	edNotPastTenseD.put(in.hashCode(), in);
	 		  }
	     	  catch(Exception e) {
	     		
	     	  }
	       }
		}
		return edNotPastTenseD;
	}
	
	public static Hashtable<Integer, String> getIrregularPastTenseD()
	{
		if (irregularPastTenseD == null)
		{
		   File file = new File("IrregularPastTenseVerbs.txt");
	       Scanner input = null;
	       try
	       {
	           input = new Scanner(file);
	       }
	        
	       catch (FileNotFoundException ex) //returns error if input.txt is not found
	       {
	         System.out.println("*** Cannot open file ***");
	         System.exit(1); //quit the program
	       }
	       
	       irregularPastTenseD = new  Hashtable<Integer, String>();
	     
	       while(input.hasNextLine())
	       {
	     	  try {
		        	String in = input.nextLine();
		        	irregularPastTenseD.put(in.hashCode(), in);
	 		  }
	     	  catch(Exception e) {
	     		
	     	  }
	       }
		}
		return irregularPastTenseD;
	}
		
	public static Hashtable<Integer, String> getIngNotProgressiveTenseD()
	{
		if (ingNotProgressiveTenseD == null)
		{
		   File file = new File("IngWordsNotProgressiveTense.txt");
		   Scanner input = null;
		   try
		   {
		       input = new Scanner(file);
		   }
		    
		   catch (FileNotFoundException ex) //returns error if input.txt is not found
		   {
		     System.out.println("*** Cannot open file ***");
		     System.exit(1); //quit the program
		   }
		   
		   ingNotProgressiveTenseD = new Hashtable<Integer, String>();
		 
		   while(input.hasNextLine())
		   {
		 	  try {
		        	String in = input.nextLine();
		        	ingNotProgressiveTenseD.put(in.hashCode(), in);
			  }
		 	  catch(Exception e) {
		 		  
		 	  }
		   }
		}
		return ingNotProgressiveTenseD;
	} 
	
	public static Hashtable<Integer, String> getPassiveVoiceD()
	{
		if (passiveVoiceD == null)
		{
	      File file = new File("PassiveVoiceVerbs.txt");
	      Scanner input = null;
	       try
	       {
	           input = new Scanner(file);
	       }
	        
	       catch (FileNotFoundException ex) //returns error if input.txt is not found
	       {
	         System.out.println("*** Cannot open file ***");
	         System.exit(1); //quit the program
	       }
	       
	       passiveVoiceD = new Hashtable<Integer, String>();
	     
	       while(input.hasNextLine())
	       {
	     	  try {
		        	String in = input.nextLine();
		        	passiveVoiceD.put(in.hashCode(), in);
	 		  }
	     	  catch(Exception e) {
	     		
	     	  }
	       }
	    }
		 return passiveVoiceD;
   }
}