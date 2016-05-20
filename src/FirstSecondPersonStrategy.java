/*
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class FirstSecondPersonStrategy implements EssaySearchStrategy {

	String rule = "<html>" +
    "<b>VI. Put pronouns in the appropriate case (subjective, objective, possessive).</b>" +
  	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> She is the last person <i>who</i> I would suspect.</li>" 
  	 + "<li style='list-style-type:none'></li>"
  	 + "<li style='list-style-type: none'><b>(Correct)</b> She is the last person <i>whom</i> I would suspect.</li>" + "</ul" + "</html>";
	     	  	     	         
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
