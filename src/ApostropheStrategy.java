/*
 * strategy to check for sentences with possible apostrophe errors within an essay
 * 
 *  Written By: Kiara Wahnschafft, Kelly Finke, Shannon Wing
 * Date: 5/31/16
 */

public class ApostropheStrategy implements EssaySearchStrategy {

	private static String rule = "<html>" +
		    "<b>VIII. Use an apostrophe to indicate possession, <i>not</i> to indicate that a noun is plural. Distinguish properly between <i>its</i> and <i>it's</i>.</b>" +
		  	 "<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Longbourn is Elizabeth <i>Bennets</i> home.</li>" 
		     + "<li style='list-style-type:none'></li>"
		     + "<li style='list-style-type: none'><b>(Correct)</b> Longbourn is Elizabeth <i>Bennet's</i> home.</li>" + "</ul" + "</html>";
	
	//returns an array of Linked Lists containing all of the sentences 
	//within the essay that contain an apostrophe or 'its'
	public ListNode2[] findInEssay(TreeMap tree) {
		String[] searchFor = {"'", "���", "its"};
		
		return StrategyHelperMethods.findInEssayHelper(searchFor, tree);
		
	}
	
	//returns a String representation of the
	//apostrophe blue sheet rule
	public String getRule() {
		return rule;
	}
}
