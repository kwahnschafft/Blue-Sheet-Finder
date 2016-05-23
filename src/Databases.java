/*
 * Written By: Shannon Wing
 * Date: 5/19/16
 */
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import java.io.*;

public class Databases {
	//hashtable
	Hashtable<int, String> progressiveTense;
	Hashtable<int, String> pastTense;
	Hashtable<int, String> passiveVoice;
	
	//downloads and stores all of the words from a pdf into an array
	public Databases ()
	{
		
		
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
