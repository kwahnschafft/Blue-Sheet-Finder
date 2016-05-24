/*
 * Written By: Shannon Wing
 * Date: 5/19/16
 */

import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class Databases {
	
	//check about sizing for each
	Hashtable<Integer, String> ingNotProgressiveTense;
	Hashtable<Integer, String> pastTense;
	Hashtable<Integer, String> passiveVoice;
	

	
	//downloads and stores all of the words from a txt files into
	// hashtables for easy access
	public Databases ()
	{
		
	   File file = new File("PastTenseVerbs.txt");
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
     
       while(input.hasNextLine())
       {
     	  try {
	        	String in = input.nextLine();
	        	pastTense.put(in.hashCode(), in);
 		  }
     	  catch(Exception e) {
     		
     	  }
       }
       
       file = new File("IngWordsNotProgressiveTense.txt");
       input = null;
       try
       {
           input = new Scanner(file);
       }
        
       catch (FileNotFoundException ex) //returns error if input.txt is not found
       {
         System.out.println("*** Cannot open file ***");
         System.exit(1); //quit the program
       }
     
       while(input.hasNextLine())
       {
     	  try {
	        	String in = input.nextLine();
	        	ingNotProgressiveTense.put(in.hashCode(), in);
 		  }
     	  catch(Exception e) {
     		
     	  }
       }
       
       file = new File("PassiveVoiceVerbs.txt");
       input = null;
       try
       {
           input = new Scanner(file);
       }
        
       catch (FileNotFoundException ex) //returns error if input.txt is not found
       {
         System.out.println("*** Cannot open file ***");
         System.exit(1); //quit the program
       }
     
       while(input.hasNextLine())
       {
     	  try {
	        	String in = input.nextLine();
	        	passiveVoice.put(in.hashCode(), in);
 		  }
     	  catch(Exception e) {
     		
     	  }
       }
			
	}
	
	//returns database array of most common progressive tense words
	public Hashtable<Integer,String> getPastTenseD()
	{
		return pastTense;
	}
	
	//returns database array of most common past tense verbs
	public Hashtable<Integer, String> getIngNotProgressiveTenseD()
	{
		return ingNotProgressiveTense;
	}
	
	//returns database array of most common passive voice verbs
	public Hashtable<Integer, String> getPassiveVoiceD()
	{
		return passiveVoice;
	}

}
