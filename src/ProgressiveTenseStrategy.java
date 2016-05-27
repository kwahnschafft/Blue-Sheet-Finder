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
       String[] searchFor = {"is", "are"};
       
   	ArrayList<ListNode2> returning = new ArrayList<ListNode2>();
	ListNode2 head = null;
	ListNode2 node = null;
	ListNode2 previousNode = null;
		
		for (String helperVerb: searchFor)
		{
		    //search tree for words ending in ing
			for (String word: tree.keySet())
			{
				 if (word.compareTo(helperVerb) == 0)
				    {
				    	ListNode2 nodeWithWordLoc = tree.get(word);
				    	
				    	while(nodeWithWordLoc.getNext() != null)
				    	{
				    		String sentence =((WordLoc)( nodeWithWordLoc.getValue())).getSentenceString();
				    		int index = ((WordLoc)( nodeWithWordLoc.getValue())).getWordIndex()+3;
				    		int origIndex = index;
				    		char ch = sentence.charAt(index);
				    		while (ch != ' ')
				    		{
				    			index++;
				    		}
				    		
				    		String nextWord = sentence.substring(origIndex, index);
							//when word after 'is' or 'are' ends in 'ing'
							//check to make sure it is progressive using database and add
							//the ListNode2 head to the arrayList
						    if (nextWord.length() > 3 && nextWord.substring(nextWord.length()-3, word.length()).compareTo("ing") == 0)
						    {
						    	if (Databases.getIngNotProgressiveTenseD().contains(nextWord) == false)
						    		 previousNode = node;
								  node = new ListNode2(nodeWithWordLoc.getValue());
							    	if (head == null)
							    	{
							    	    head = node;
							    	}
							    	else 
							        	previousNode.setNext(node);  	  
						    }
							nodeWithWordLoc = nodeWithWordLoc.getNext();
			            }
		            }       
			}
		}
		returning.add(head);
		return returning;
	}
	
	//returns a String representation of the  
	//progressive tense bluesheet rule
	public String getRule() {
		return rule;
	}

}
