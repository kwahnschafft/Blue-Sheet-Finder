/*
 * Written By: Shannon Wing
 * Date: 5/19/16
 */
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import java.io.*;
import java.util.Scanner;

public class Databases {
	//hashtable
	Hashtable<int, String> progressiveTense;
	Hashtable<int, String> pastTense;
	Hashtable<int, String> passiveVoice;
	
	//downloads and stores all of the words from a pdf into an array
	public Databases ()
	{
		File file = new File("input.txt");
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
	        	int i = 0;
	        	int insert = 0;
	        	String temp = "";
	        	while(in.charAt(i) != ' ') { 
	        		temp += in.charAt(i);
	        		i++;
	        	}
    		}
        	catch(Exception e) {
        		
        	}
        }
		
	}
	
	//returns database array of most common progressive tense words
	public String[] getPastTenseD()
	{
		return progressiveTense;
	}
	
	//returns database array of most common past tense verbs
	public String[] getProgressiveTenseD()
	{
		return pastTense;
	}
	
	//returns database array of most common passive voice verbs
	public String[] getPassiveVoiceD()
	{
		return passiveVoice;
	}

}
