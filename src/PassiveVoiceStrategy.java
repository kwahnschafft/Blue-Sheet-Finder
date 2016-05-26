import java.util.ArrayList;

/*
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class PassiveVoiceStrategy implements DatabaseSearchStrategy{

	private static String rule = "<html>" +
		    "<b>IX. Write in the active voice; avoid the passive voice.</b>" +
		  	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Gulliver is <i>taught</i> many lessons in rational behavior.</li>" 
		  	 + "<li style='list-style-type:none'></li>"
		  	 + "<li style='list-style-type: none'><b>(Correct)</b> The Houyhnhnms <i>teach</i> Gulliver many lessons in rational behavior.</li>" + "</ul" + "</html>";
			
	public ArrayList<ListNode2> findInDatabase(TreeMap tree) {
		 String searchFor = "is";
			
			ArrayList<ListNode2> returning = new ArrayList<ListNode2>();
			ListNode2 head = null;
			ListNode2 node = null;
			ListNode2 previousNode = null;
			
			    //search tree for 'is'
				for (String word: tree.keySet())
				{
				    if (word.compareTo(searchFor) == 0)
				    {
				    	//check to see if next word in each sentence is pastTense
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
				    		
				    		 if (nextWord.substring(nextWord.length()-2, nextWord.length()).compareTo("ed") == 0)
							 {
				    			 //if the next word is past tense, create a clone of the ListNode2
				    			 //and add it to the LinkedList that is being returned
							    if (Databases.getEdNotPastTenseD().contains(word) == false)
							    {
							    	previousNode = node;
							    	node = new ListNode2(nodeWithWordLoc.getValue());
							    	if (head == null)
							    	{
							    		 head = node;
							    	}
							    	else 
							        	previousNode.setNext(node);
				
							    	break;
							    }
							  }
							  //check to see if word is an irregular past tense verb
							  else if (Databases.getIrregularPastTenseD().contains(nextWord)) 
							  {
								  previousNode = node;
								  node = new ListNode2(nodeWithWordLoc.getValue());
							    	if (head == null)
							    	{
							    	    head = node;
							    	}
							    	else 
							        	previousNode.setNext(node);  
							    	break;
							  }
				    		nodeWithWordLoc = nodeWithWordLoc.getNext();
				    		
				    	}
				    	break;	  
				    }
				  
				 }
				returning.add(head);
			return returning;
		
	}

	public String getRule() {
		return rule;
	}

}
