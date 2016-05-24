import java.util.ArrayList;

/*
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class ProgressiveTenseStrategy implements DatabaseSearchStrategy{

	private static String rule = "<html>" +
	"<b>XII. Avoid progressive tenses.</b>" +
	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Sensing God's desire to destroy Sodom, Abraham <i>is negotiating</i> for a less apocalyptic punishment.</li>" 
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'><b>(Correct)</b> Sensing God's desire to destroy Sodom, Abraham <i>negotiates</i> for a less apocalyptic punishment.</li>" + "</ul" + "</html>";

	public ArrayList<ListNode2> findInDatabase(TreeMap tree) {
       String searchFor = "ing";
		
		ArrayList<ListNode2> returning = new ArrayList<ListNode2>();
		
		    //search tree for words ending in ing
			for (String word: tree.keySet())
			{
				//when word ending in ing is found 
				//check to make sure it is progressive using database and add
				//the ListNode2 head to the arrayList
			    if (word.substring(word.length()-3, word.length()).compareTo(searchFor) == 0)
			    {
			    	if (Databases.getIngNotProgressiveTenseD().contains(word) == false)
			        	returning.add(tree.get(word));  	  
			    }
			}
		return returning;
	}
	
	public String getRule() {
		return rule;
	}

}
