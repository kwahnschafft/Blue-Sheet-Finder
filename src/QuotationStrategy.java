/*
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class QuotationStrategy implements EssaySearchStrategy{

	@Override
	public ListNode2[] findInEssay(TreeMap tree) {
		String searchFor = "\"";
			
			ListNode2[] returning = new ListNode2[1];
			//search tree for each pronoun case
			
				//when quotation is found add head of LinkedList to array slot
				for (String word: tree.keySet())
				{
				    if (word.compareTo(searchFor) == 0)
				    {
				        returning[0] = tree.get(word);
				        break;
				    }
				}
			return returning;
			
	}

	public String getRule() {
		// TODO Auto-generated method stub
		return null;
	}

}
