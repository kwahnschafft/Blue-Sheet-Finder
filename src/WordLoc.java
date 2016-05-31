/**
 * Represents the location of a word as a ListNode containing its
 * sentence and an int containing the index of the first letter of the word
 * 
 * Written By: Kelly Finke, Shannon Wing, Kiara Wahschafft
 * Date: 5/31/16
 *
 */
public class WordLoc{
	private ListNode2 sentence;
	private int index;
	private String word;
	
	/*//wraps String in a ListNode2 and creates new WordLoc
	public WordLoc(String s, int i, String w){
		ListNode2 node = new ListNode2(s);
		node.setPrevious(node);
		node.setNext(node);
		sentence = node;
		index = i;
		word = w.toLowerCase();
	}*/
	
	//constructs new WordLoc
	public WordLoc(ListNode2 s, int i, String w){
		sentence = s;
		index = i;
		word = w.toLowerCase();
	}
	
	//returns sentence
	public ListNode2 getSentenceNode(){
		return sentence;
	}
	
	//returns string in sentence ListNode
	public String getSentenceString(){
		return (String)sentence.getValue();
	}
	
	//returns index of word in sentence
	public int getWordIndex(){
		return index;
	}
	
	//returns word in wordloc
	public String getWord(){
		return word;
	}
	
	//returns true if this is the same a other
	public boolean equals(WordLoc other){
		return sentence == other.getSentenceNode() && index == other.getWordIndex();
	}
	
	//used for testing
	public String toString(){
		return "sentence: \"" + sentence.getValue() + "\" word: \"" + word + "\" index: " + index;
	}
}
