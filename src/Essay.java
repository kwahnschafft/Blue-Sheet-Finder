import java.util.LinkedList;

public class Essay {
	//blank header node for sentences linked list
	private static ListNode2 sentencesHead;
	
	/*
	 * place all sentences of essay into a linked list of sentences (strings)
	 */
	public static void parse(String essay) {
		sentencesHead = new ListNode2(null);
		sentencesHead.setNext(sentencesHead);
		sentencesHead.setPrevious(sentencesHead);
		String temp = "";
		ListNode2 tempNode;
		int i = 0;
		while(i < essay.length()) {
			int k = i;
			while(k < essay.length() - 1 && essay.charAt(k) != '.') {
				temp += essay.charAt(k);
				k++;
			}
			temp += essay.charAt(k);
			//add node to sentence list
			tempNode = new ListNode2(temp);
			tempNode.setNext(sentencesHead);
			tempNode.setPrevious(sentencesHead.getPrevious());
			sentencesHead.getPrevious().setNext(tempNode);
			sentencesHead.setPrevious(tempNode);
			i = k+1;
			temp = "";
		}
	}

	/*
	 * adds all the words in all the sentences to the tree of words
	 */
	public static void addAllWords() {
		ListNode2 node = sentencesHead.getNext();
		while(node != sentencesHead) {
			addSentenceWords((String)node.getValue());
			node = node.getNext();
		}
	}
	
	//adds words from a single sentence to the tree of words
	public static void addSentenceWords(String str){
		String punctuation = ".,\"'()[]{};:?!-/\\"; //TODO all punctuation?
		String temp = "";
		int i = 0;
		while(i < str.length()) {
			int k = i;
			if(punctuation.indexOf(str.charAt(k)) >= 0){ //character is punctuation
				temp = ""+str.charAt(k);
			}else{
			while(k < str.length() && str.charAt(k) != ' ' && punctuation.indexOf(str.charAt(k)) < 0) {
				temp += str.charAt(k);
				k++;
			}
			}
			tree.put(temp, new WordLoc(str, i)); //add method of the tree
			i = k+1;
			temp = "";
		}
	}
	
/*	public static void main(String[] args) {
		parse("Heae ael fwe iajfiawpejf eof. AGwaelkj foiajfewjf ej feljakew;f. alsdfj.");
		System.out.println(sentences);
		addAllWords();
	}
	*/
}
