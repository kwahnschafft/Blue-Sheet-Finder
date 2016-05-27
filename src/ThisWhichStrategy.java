/*
 * Written By: Shannon Wing, Kelly Finke, and Kiara Wahnschafft
 * Date: 5/31/16
 */

public class ThisWhichStrategy implements EssaySearchStrategy {

	private static String rule = "<html>" +
	 "<b>IV. Do not use 'this' or 'which' to refer to a clause.</b>" +
	  "<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> In Dr. Seuss's <u>Horton Hears a Who</u>, Horton the elephant says that he hears a voice. <i>This</i> causes his friends to accuse him of being insane.</li>" 
	         + "<li style='list-style-type:none'></li>"
	         + "<li style='list-style-type: none'><b>(Correct)</b> In Dr. Seuss's <u>Horton Hears a Who</u>, Horton the elephant says that he hears a voice. <i>This claim</i> causes his friends to accuse him of being insane.</li>" + "</ul" + "</html>";
	
	public ListNode2[] findInEssay(TreeMap tree) {
		String[] searchFor = {"this", "which"};
			
			ListNode2[] returning = new ListNode2[2];
			
			//search tree for this, then which
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

	//returns a String representation of the 
	//this which bluesheet rule
	public String getRule() {
		return rule;
	}

}
