/*
 * strategy to check for sentences with possible pronoun case errors within an essay
 * 
 *  Written By: Shannon Wing, Kelly Finke, Kiara Wahschafft
 * Date: 5/31/16
 */
public class AppropriateCasePronounsStrategy implements EssaySearchStrategy {

	private static String rule = "<html>" +
    "<b>VI. Put pronouns in the appropriate case (subjective, objective, possessive).</b>" +
  	 "<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> She is the last person <i>who</i> I would suspect.</li>" 
     + "<li style='list-style-type:none'></li>"
     + "<li style='list-style-type: none'><b>(Correct)</b> She is the last person <i>whom</i> I would suspect.</li>" + "</ul" + "</html>";
	
	//returns an array of Linked Lists containing all of the sentences 
	//within the essay that contains a pronoun case
	public ListNode2[] findInEssay(TreeMap tree) {
		String[] searchFor = {"who", "whom", "she", "her", "he", "him"};
		
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

	//returns a String representation of
	// the pronoun case bluesheet rule
	public String getRule()
	{
		return rule;
	}

}
