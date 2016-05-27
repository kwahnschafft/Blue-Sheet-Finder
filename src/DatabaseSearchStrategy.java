/*
 * represents a bluesheet search strategy that utilizes databases of hashtables 
 * 
 * Written By: Shannon Wing, Kelly Finke, Kiara Wahnschafft
 * Date: 5/31/16
 */

import java.util.ArrayList;
public interface DatabaseSearchStrategy {
	
	//returns an arraylist containing linked lists of WordLocs 
	//that contain the sentences that could maintain the specific bluesheet
	public ArrayList<ListNode2> findInDatabase(TreeMap tree);
	
	//returns specific bluesheet rule
	public String getRule();
}
