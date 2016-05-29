import java.util.LinkedList;
import java.util.Set;

//tests TreeMap
public class TreeMapTester {
	public static void main(String[] args){
		//String text = "Ancient Egypt was inhabited by mummies, and they all wrote in hydraulics.  They lived in the Sarah Dessert and traveled by Camelot. The climate of the Sarah is such that the inhabitants have to live elsewhere, so certain areas of the dessert are cultivated by irritation.  The pyramids are a range of mountains between France and Spain.  The Egyptians built the pyramids in the shape of huge triangular cube. The Greeks were a highly sculptured people, and without them we wouldn't have history.  The Greeks also had myths.  A myth is a female moth.  One myth says that the mother of Achilles dipped him in the river Stynx until he became intollerable. Achilles appears in The Iliad, by Homer.  Homer also wrote The Oddity, in which Penelope was the last hardship that Ulysses endured on his journey. Actually, Homer was not written by Homer, but by another man of that name. Socrates was a famous greek teacher who went around giving people advice.  They killed him.  Socrates died from an overdose of wedlock.  After his death, his career suffered a dramatic decline. In the Olympic Games, Greeks ran races, jumped, hurled the biscuits, and threw java.  The reward to the victor was a coral wreath. From Anguished English by Richard Lederer.";
		String text = "";
		Essay essay = new Essay(text);
		ListNode2 header = essay.getSentences();
		TreeMap tree = essay.getTree();
		ListNode2 node = header;
		while(node.getNext() != null && node.getNext() != header){
			node = node.getNext();
			System.out.println(node.getValue());
			System.out.println();
		}
		
		
		System.out.println(tree.containsKey(","));
		ListNode2 resultHead = tree.get(",");
		ListNode2 next = resultHead;
		do{
			System.out.println(next.getValue());
			next = next.getNext();
		} while(next != resultHead);
		
		
	}
	
}
