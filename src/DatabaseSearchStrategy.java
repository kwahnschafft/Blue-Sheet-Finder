/*
 * an interface that represents bluesheet search strategies 
 * that utilize databases of hashtables 
 * 
 * Written By: Shannon Wing, Kelly Finke, Kiara Wahnschafft
 * Date: 5/31/16
 */

import java.util.ArrayList;
public interface DatabaseSearchStrategy {
	
	//returns an arraylist containing LinkedLists of WordLocs 
	//that contain the sentences that could maintain the specific bluesheet
	public ArrayList<ListNode2> findInDatabase(TreeMap tree);
	
	//returns specific bluesheet rule
	public String getRule();
}
