import java.util.Iterator;

/*
 *  Written By: Kiara
 * Date: 5/15/16
 */

public class ApostropheStrategy implements EssaySearchStrategy {

	private static String rule = "<html>" +
		    "<b>VIII. Use an apostrophe to indicate possession, <i>not</i> to indicate that a noun is plural. Distinguish properly between <i>its</i> and <i>it's</i>.</b>" +
		  	 "<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Longbourn is Elizabeth <i>Bennets</i> home.</li>" 
		     + "<li style='list-style-type:none'></li>"
		     + "<li style='list-style-type: none'><b>(Correct)</b> Longbourn is Elizabeth <i>Bennet's</i> home.</li>" + "</ul" + "</html>";
			
	public ListNode2[] findInEssay(TreeMap tree) {
		String[] searchFor = {"'"};
		
		ListNode2[] returning = new ListNode2[6];
		//search tree for each pronoun case
		
		for (int i = 0; i < searchFor.length; i++)
		{
			//when word is found add head of LinkedList to array slot
			for (String word: tree.keySet())
			{
			    if (word.compareTo(searchFor[i]) == 0)
			    {
			        returning[i] = tree.get(word);
			        break;
			    }
			}
		}
		return returning;
		
	}
	
	public String getRule() {
		return rule;
	}
}
