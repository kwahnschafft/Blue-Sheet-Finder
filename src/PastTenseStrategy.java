/*
 * strategy to check for sentences with past tense within an essay
 * 
 * Written By: Shannon Wing, Kelly Finke, Kiara Wahschafft
 * Date: 5/31/16
 */
import java.util.ArrayList;

public class PastTenseStrategy implements DatabaseSearchStrategy{
	private static String rule = "<html>" +
	"<b>I. Use the present tense in writing about a literary work.</b>" +
	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Macbeth <i>hastened</i> home to tell his wife of the king's approach.</li>" 
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'><b>(Correct)</b> Macbeth <i>hastens</i> home to tell his wife of the king's approach.</li>" 
	 + "</ul" + "</html>";
	
	//returns an arraylist of Linked Lists containing all of the sentences 
	//within the essay that contain a past tense word
	public ArrayList<ListNode2> findInDatabase(TreeMap tree) {
		  String searchFor = "ed";
			
			ArrayList<ListNode2> returning = new ArrayList<ListNode2>();
			
			    //search tree for words ending in ed
				for (String word: tree.keySet())
				{
					 //check to see if word is an irregular past tense verb
					  if (Databases.getIrregularPastTenseD().contains(word)) 
					    	returning.add(tree.get(word));
					//when word ending in ed is found 
					//check to make sure it is past tense using database and add
					//the ListNode2 head to the arrayList
					  else if (word.length() > 2 && word.substring(word.length()-2, word.length()).compareTo(searchFor) == 0)
				    {
				    	if (!Databases.getEdNotPastTenseD().contains(word))
				        	returning.add(tree.get(word));  	  
				    }
				 
				  
				 }
			return returning;
		}
		
	//returns a String representation of the  
    //past tense bluesheet rule
	public String getRule() {
		return rule;
	}


}
