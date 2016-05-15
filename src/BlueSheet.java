import java.util.LinkedList;

public class BlueSheet {
	private static LinkedList<String> sentences = new LinkedList<String>();
	
	/*
	 * place all sentences of essay into a linked list of sentences (strings)
	 */
	public static void parse(String essay) {
		String temp = "";
		int i = 0;
		while(i < essay.length()) {
			int k = i;
			while(k < essay.length() && essay.charAt(k) != '.') {
				temp += essay.charAt(k);
				k++;
			}
			temp += essay.charAt(k);
			sentences.add(temp);
			i = k+1;
			temp = "";
		}
	}

	/*
	 * adds all the words in the sentences to the tree of words
	 */
	public static void addWords() {
		String temp = "";
		for(String str : sentences) {
			int i = 0;
			while(i < str.length()) {
				int k = i;
				while(k < str.length() && str.charAt(k) != ' ') {
					temp += str.charAt(k);
					k++;
				}
				//tree.add(temp, str); //add method of the tree
				i = k+1;
				temp = "";
			}	
		}
	}
	
	public static void main(String[] args) {
		parse("Heae ael fwe iajfiawpejf eof. AGwaelkj foiajfewjf ej feljakew;f. alsdfj.");
		System.out.println(sentences);
		addWords();
	}
}
