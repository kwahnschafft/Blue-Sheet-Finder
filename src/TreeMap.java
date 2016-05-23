/**
 * in our version of TreeMap, elements with the same key are
 * added to a corresponding LinkedList containing all WordLocs 
 * with the given key
 * @author Kelly Finke
 * @version 5/15/16
 *
 */
public class TreeMap extends java.util.TreeMap<String, ListNode2>{
	//wraps up WordLoc in a ListNode to be stored in the map
	public ListNode2 put(String key, WordLoc value){
		ListNode2 node = new ListNode2(value);
		node.setPrevious(node);
		node.setNext(node);
		return put(key, node);
	}
	
	// overrides TreeMap put method
	// if the key is not already in the tree, adds it,
	// otherwise, adds the ListNode2 to the linked list attached to existing key
	public ListNode2 put(String key, ListNode2 value){
		if(!containsKey(key)){ //if key not already there, add normally
			return super.put(key, value);
		}
		else{ //key already exists, so add value to the linked list attached to key
			ListNode2 head = get(key);
			
			//add to end of this circular linked list
			value.setPrevious(head.getPrevious());
			value.getPrevious().setNext(value);
			value.setNext(head);
			head.setPrevious(value);
			return head;
		}
	}
}

