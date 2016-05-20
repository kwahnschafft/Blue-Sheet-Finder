/*
 * Written By: Shannon Wing, Kelly Finke, and Kiara Wahnschafft
 * Date: 5/15/16
 */
public class ThisWhichStrategy implements EssaySearchStrategy {

	@Override
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

	public String getRule() {
		// TODO Auto-generated method stub
		return null;
	}

}
