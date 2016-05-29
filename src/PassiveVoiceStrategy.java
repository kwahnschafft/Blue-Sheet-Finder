/*
 * strategy to check for sentences with passive voice within an essay
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
		 String[] searchFor = {"is", "are", "was", "were"};
		 String punctuation = ".,\"\'()���[]{}';:?!-/\\";
			
			ArrayList<ListNode2> returning = new ArrayList<ListNode2>();
			ListNode2 head = null;
			ListNode2 node = null;
			ListNode2 previousNode = null;
			
			    //search tree for 'is'
				for (String word: tree.keySet())
				{
					for (String helperVerb: searchFor)
					{
					    if (word.compareTo(helperVerb) == 0)
					    {
					    	
					    	ListNode2 nodeWithWordLoc = tree.get(word);
					    	ListNode2 oldHead = tree.get(word);
					    	
					    	do {
					    		//check to see if next word in each sentence is pastTense
					    		String sentence =((WordLoc)( nodeWithWordLoc.getValue())).getSentenceString();
					    		int index = ((WordLoc)( nodeWithWordLoc.getValue())).getWordIndex();
					    		index += helperVerb.length()+1;
					    		
					    		int origIndex = index;
					    		char ch = sentence.charAt(index);
					    		while ((punctuation.indexOf(ch) < 0) && ch != ' ')
					    		{
					    			index++;
					    			ch = sentence.charAt(index);
					    		}
					    		System.out.println(origIndex + " " + index);
					    		String nextWord = sentence.substring(origIndex, index);
					    		System.out.println("next word " + nextWord);
					    		
					    		 if ( nextWord.length() > 2 && nextWord.substring(nextWord.length()-2, nextWord.length()).compareTo("ed") == 0
					    				 && !Databases.getEdNotPastTenseD().contains(nextWord))
								 {
					    			 //if the next word is past tense, create a clone of the ListNode2
					    			 //and add it to the LinkedList that is being returned
								    	previousNode = node;
								    	node = new ListNode2(nodeWithWordLoc.getValue());
								    	if (head == null)
								    	{
								    		 head = node;
								    	}
								    	else {
								        	previousNode.setNext(node);}
					
								    
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
								 
								  }
					    		nodeWithWordLoc = nodeWithWordLoc.getNext();  

					    } while(!nodeWithWordLoc.equals(oldHead));
					    	
					}
				  
				 }
			
				}
			returning.add(head);
			return returning;
		
	}

	//returns a String representation of the 
    //passive voice bluesheet rule
	public String getRule() {
		return rule;
	}

}
