/*
 * strategy to check for sentences with progressive tense within an essay
 * 
 * Written By: Shannon Wing, Kiara Wahschafft, Kelly Finke
 * Date: 5/31/16
 */

import java.util.ArrayList;
public class ProgressiveTenseStrategy implements DatabaseSearchStrategy{

	private static String rule = "<html>" +
	"<b>XII. Avoid progressive tenses.</b>" +
	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Sensing God's desire to destroy Sodom, Abraham <i>is negotiating</i> for a less apocalyptic punishment.</li>" 
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'><b>(Correct)</b> Sensing God's desire to destroy Sodom, Abraham <i>negotiates</i> for a less apocalyptic punishment.</li>" + "</ul" + "</html>";

	//returns an arraylist of LinkedLists containing all of the sentences 
	//within the essay that contain progressive tense
	public ArrayList<ListNode2> findInDatabase(TreeMap tree) {
		
		return StrategyHelperMethods.helperVerbStrategy(tree, true);
	}
	
	//returns a String representation of the  
	//progressive tense bluesheet rule
	public String getRule() {
		return rule;
	}

}
