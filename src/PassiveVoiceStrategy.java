/*
 * strategy to check for sentences with passive voice within an essay
 * 
 * we are aware that this strategy does not find all passive voice errors because 
 * we are checking for a helper verb and then a paste tense verb rather than
 * the past participle form of the verb. Conveniently the past participle of a verb
 * if often the same as the past tense, but not always. For example, the sentence
 * The book was written by her." would not be found because written is not past tense.
 * To check for such cases we would need a large database of all possible past participle 
 * conjugations of the verb that would take too much time to compile and take up too
 *  much space on the computer. 
 * 
 * 
 * Written By: Shannon Wing, Kiara Wahschafft, Kelly Finke
 * Date: 5/31/16
 */

import java.util.ArrayList;
public class PassiveVoiceStrategy implements DatabaseSearchStrategy{

	private static String rule = "<html>" +
		    "<b>IX. Write in the active voice; avoid the passive voice.</b>" +
		  	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Gulliver is <i>taught</i> many lessons in rational behavior.</li>" 
		  	 + "<li style='list-style-type:none'></li>"
		  	 + "<li style='list-style-type: none'><b>(Correct)</b> The Houyhnhnms <i>teach</i> Gulliver many lessons in rational behavior.</li>" + "</ul" + "</html>";
	
	//returns an arraylist with one LinkedList containing all of the sentences 
	//within the essay that contain passive voice
	public ArrayList<ListNode2> findInDatabase(TreeMap tree) {
		 return StrategyHelperMethods.helperVerbStrategy(tree,  false);
	}

	//returns a String representation of the 
    //passive voice bluesheet rule
	public String getRule() {
		return rule;
	}

}
