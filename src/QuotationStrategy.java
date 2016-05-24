/*
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class QuotationStrategy implements EssaySearchStrategy{

	private static String rule = "<html>" +
	"<b>XIII. Conventions of Quotation.</b>" +
	"<ul>" + "<li style='list-style-type: none'><b>Refer<b> to all quoted material in <u>prose</u> by page number(s); <b>Examples:</b> (p. 57) or (pp. 57-58)</li>" 
	 + "<li style='list-style-type: none'><b>Refer<b> to all quoted material in <u>poetry</u> by line number(s); <b>Examples:</b> (15) or (15-18)</li>" 
	 + "<li style='list-style-type: none'><b>Refer<b> to all quoted material in <u>drama</u> by act, scene, and line number(s); <b>Example:</b> (2.4.15-25)</li>" 
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'>Put such references <i>within</i> parentheses at the end of the quotation, <i>outside</i> the quotation marks, <i>before</i> end punctuation.</li>" 
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'><b>Example:</b> Macbeth's failure as a human being is never more pronounced than when he takes pity on himself, declaring that his 'way of life / Is fall'n into the sere, the yellow leaf' (5.3.22-23).</li>"
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'>Note from the above that when quoting lines of petry <i>in your text</i>, you indicate the end of each line by using a slash(/). Put a space before and after the slash.</li>"
	 + "</ul" + "</html>";
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
		return rule;
	}

}
