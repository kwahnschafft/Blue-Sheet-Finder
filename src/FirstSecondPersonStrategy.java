/*
 * strategy to check for sentences with first or second person within an essay
 * 
 * Written By: Shannon Wing, Kelly Finke, Kiara Wahnschafft
 * Date: 5/31/16
 */
public class FirstSecondPersonStrategy implements EssaySearchStrategy {

	private static String rule = "<html>" +
    "<b>III. Do not use the first or second person ('I,' 'me,' 'my'; 'we,' 'us,' 'our'; 'you,' 'your') in critical writing.</b>" +
  	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> <i>I think that</i> Holden Caulfield, the hero of <u>The Catcher in the Rye</u>, is actually a hypocrite.</li>" 
  	 + "<li style='list-style-type:none'></li>"
  	 + "<li style='list-style-type: none'><b>(Correct)</b> Holden Caulfield, the hero of <u>The Catcher in the Rye</u>, is actually a hypocrite.</li>" + "</ul" + "</html>";
	     	  	     	         
	//returns an array of Linked Lists containing all of the sentences 
	//within the essay that contain first or second person references
	public ListNode2[] findInEssay(TreeMap tree) {
        String[] searchFor = {"i", "me", "my", "we", "us", "our", "you", "your"};
	
        return StrategyHelperMethods.findInEssayHelper(searchFor, tree);
		
	}

	//returns a String representation of the first 
	//person and second person bluesheet rule
	public String getRule() {
		return rule;
	}

}
