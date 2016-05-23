/**
 * Represents the location of a word as a ListNode containing its
 * sentence and an int containing the index of the first letter of the word
 * @author Kelly Finke
 * @version 5/15/16
 *
 */
public class WordLoc{
	private ListNode2 sentence;
	private int index;
	
	//wraps String in a ListNode2 and creates new WordLoc
	public WordLoc(String s, int i){
		ListNode2 node = new ListNode2(s);
		node.setPrevious(node);
		node.setNext(node);
		sentence = node;
		index = i;
	}
	
	//constructs new WordLoc
	public WordLoc(ListNode2 s, int i){
		sentence = s;
		index = i;
	}
	
	//returns sentence
	public ListNode2 getSentence(){
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
	
	//returns true if this is the same a other
	public boolean equals(WordLoc other){
		return sentence == other.getSentence() && index == other.getWordIndex();
	}
}
