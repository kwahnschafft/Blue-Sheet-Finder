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
		String filesname = "PastTenseVerbs.doc";
        POIFSFileSystem fs = null;
        try
        {
                  fs = new POIFSFileSystem(new FileInputStream(filesname; 
                  //Couldn't close the braces at the end as my site did not allow it to close

                  HWPFDocument doc = new HWPFDocument(fs);

          WordExtractor we = new WordExtractor(doc);

          String[] paragraphs = we.getParagraphText();

          System.out.println( "Word Document has " + paragraphs.length + " paragraphs" );
          for( int i=0; i<paragraphs .length; i++ ) {
            paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n","");
                    System.out.println( "Length:"+paragraphs[ i ].length());
          }
                }
                catch(Exception e) { 
                    e.printStackTrace();
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
