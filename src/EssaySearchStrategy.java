/*
 * an interface that represents bluesheet search strategies that search the essay for 
 * specific words that correlate with the specific Bluesheet
 * 
 * Written By: Shannon Wing, Kelly Finke, Kiara Wahnschafft
 * Date: 5/31/16
 */
public interface EssaySearchStrategy {

	//returns an array of LinkedLists of WordLocs that that contain the sentences 
	// that could maintain the specific bluesheet
	public ListNode2[] findInEssay(TreeMap tree);
	
	//returns the specific bluesheet rule
	public String getRule();
}
