/*
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class FirstSecondPersonStrategy implements EssaySearchStrategy {

	@Override
	public ListNode2[] findInEssay(TreeMap tree) {
        String[] searchFor = {"i", "me", "my", "we", "us", "our", "you", "your"};
		
		ListNode2[] returning = new ListNode2[8];
		
		//search tree for each first person instance and second person instance
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
		// TODO Auto-generated method stub
		return null;
	}

}
