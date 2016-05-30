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
					 //if word is past tense its LinkedList to the arrayList
					if (StrategyHelperMethods.isPastTense(word))
				        returning.add(tree.get(word)); 
				
				 }
			return returning;
		}
		
	//returns a String representation of the  
    //past tense bluesheet rule
	public String getRule() {
		return rule;
	}


}
