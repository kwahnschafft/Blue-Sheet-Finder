import java.util.ArrayList;

/*
 * Designed to consolidate repeated code within multiple strategy methods
 * 
 * Written By: Shannon Wing, Kelly Finke, Kiara Wahnschafft
 * 5/13/16
 */
public class StrategyHelperMethods {
	
	//returns an array of LinkedLists of WordLocs that that contain the sentences 
	// that could maintain the specific bluesheet 
	public static ListNode2[] findInEssayHelper(String[] searchFor, TreeMap tree)
	{
			ListNode2[] returning = new ListNode2[searchFor.length];
			
			//search tree for each String in searchFor
			for (int i = 0; i < searchFor.length; i++)
			{
				//when String is found add head of LinkedList to array slot
				for (String word: tree.keySet())
				{
				    if (word.compareTo(searchFor[i]) == 0)
				    {
				        returning[i] = tree.get(word);
				        break;
				    }
				}
			}
			return returning;
	}

	//returns true if the word is past tense
	public static boolean isPastTense(String word)
	{
		 //returns true if word is an irregular past tense verb
		  if (Databases.getIrregularPastTenseD().contains(word)) 
		    	return true;
		//return true when word ends in "ed" and does not exist in the words ending in ed that aren't past tense database
		  else if (word.length() > 2 && word.substring(word.length()-2, word.length()).compareTo("ed") == 0)
	    {
	    	if (!Databases.getEdNotPastTenseD().contains(word))
	        	return true; 	  
	    }
		return false;
	}
	
	public static ArrayList<ListNode2> helperVerbStrategy(TreeMap tree, boolean isProgressiveTenseStrategy)
	{
		 String[] searchFor = {"is", "are", "was", "were"};
		 String punctuation = ".,\"\'()���[]{}';:?!-/\\";
			
			ArrayList<ListNode2> returning = new ArrayList<ListNode2>();
			ListNode2 head = null;
			ListNode2 node = null;
			ListNode2 previousNode = null;
			
			    //search tree for helper verbs
				for (String word: tree.keySet())
				{
					for (String helperVerb: searchFor)
					{
					    if (word.compareTo(helperVerb) == 0)
					    {
					    	
					    	ListNode2 nodeWithWordLoc = tree.get(word);
					    	ListNode2 oldHead = tree.get(word);
					    	
					    	do {
					    		
					    		//check to see if next word in each sentence is progressive tense
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
					    		String nextWord = sentence.substring(origIndex, index);
					    		
					    		//when word after 'is' or 'are' ends in 'ing'
								//check to make sure it is progressive using database and add
								//the ListNode2 head to the arrayList
					    		if (isProgressiveTenseStrategy)
				    		    {
								    if (nextWord.length() > 3 && nextWord.substring(nextWord.length()-3, nextWord.length()).compareTo("ing") == 0
								    		&& !Databases.getIngNotProgressiveTenseD().contains(nextWord))
								    {
								    		 previousNode = node;
										    node = new ListNode2(nodeWithWordLoc.getValue());
									    	if (head == null)
									    	{
									    	    head = node;
									    	}
									    	else {
									        	previousNode.setNext(node);  }	  
								    	
								    }
				    		    }
					    		else 
					    		{
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
					    	}
						    
					    		nodeWithWordLoc = nodeWithWordLoc.getNext();  

					    } while(!nodeWithWordLoc.equals(oldHead));
					    	
					}
				  
				 }
			
				}
			returning.add(head);
			return returning;
	}
}

