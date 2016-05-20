import java.util.Iterator;

/*
 *  Written By: Shannon Wing
 * Date: 5/15/16
 */
public class AppropriateCasePronounsStrategy implements EssaySearchStrategy {

	
	
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

	//returns a String consisting of the pronoun case rule
	public String getRule()
	{
		return "";
	}

}
