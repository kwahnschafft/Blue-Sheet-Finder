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
	
	//constructs new WordLoc
	public WordLoc(ListNode2 s, int i){
		sentence = s;
		index = i;
	}
	
	//returns sentence
	public ListNode2 getSentence(){
		return sentence;
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
