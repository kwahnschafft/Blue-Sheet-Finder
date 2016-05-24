import java.util.ArrayList;

/*
 * 
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class PastTenseStrategy implements DatabaseSearchStrategy{
	private static String rule = "<html>" +
	"<b>I. Use the present tense in writing about a literary work.</b>" +
	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Macbeth <i>hastened</i> home to tell his wife of the king's approach.</li>" 
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'><b>(Correct)</b> Macbeth <i>hastens</i> home to tell his wife of the king's approach.</li>" 
	 + "</ul" + "</html>";
	
	public ArrayList<ListNode2> findInDatabase(TreeMap tree) {
		  String searchFor = "ed";
			
			ArrayList<ListNode2> returning = new ArrayList<ListNode2>();
			
			    //search tree for words ending in ed
				for (String word: tree.keySet())
				{
					//when word ending in ed is found 
					//check to make sure it is past tense using database and add
					//the ListNode2 head to the arrayList
				    if (word.substring(word.length()-2, word.length()).compareTo(searchFor) == 0)
				    {
				    	if (Databases.getEdNotPastTenseD().contains(word) == false)
				        	returning.add(tree.get(word));  	  
				    }
				  //check to see if word is an irregular past tense verb
				    else if (Databases.getIrregularPastTenseD().contains(word)) 
				    	returning.add(tree.get(word));
				  
				 }
			return returning;
		}
		
	
	public String getRule() {
		return rule;
	}


}
