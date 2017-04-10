import java.util.EmptyStackException;
import java.util.LinkedList;

public class LinkedListDeque implements Deque {
	
    private class Node {
        public Object data;
        public Node next;
        public Node previous;
        public Node() {}
    }
    
    private int size;
    private Node first = null;
    private Node last = null;	

	LinkedList list;
	
	LinkedListDeque(){
		list = new LinkedList();
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false;
	}

	@Override
	public void insertFirst(Object o) {
		
		Node newFirst = new Node();
		newFirst.data = o;
		
		if(first != null){
			newFirst.next = first;
			first.previous = newFirst;
		}
		first = newFirst;
		if(last == null) last = first;
		
		size++;
	}

	@Override
	public void insertLast(Object o) {
		Node newLast = new Node();
		newLast.data = o;
		
		if(last != null){
			newLast.previous = last;
			last.next = newLast;
		}
		last = newLast;
		if(first == null) first = last;
		
		size++;
	}

	@Override
	public Object removeFirst() throws EmptyStackException {
		if(first == null){ throw new EmptyStructureException(); }
		Node oldFirst = first;
		first = first.next;
		
		if(first == null)
			last = null;
		else
			first.previous = null;
		
		size--;
		
		return oldFirst.data;
	}

	@Override
	public Object removeLast() throws EmptyStructureException {
		if(last == null){ throw new EmptyStructureException(); }
		Node oldLast = last;
		last = oldLast.previous;
		
		if(last == null)
			first = null;
		else
			last.next = null;
		
		size--;
		
		return oldLast.data;
	}

	@Override
	public Object firstElement() throws EmptyStackException {
		if(first != null){ return first.data; }
		return null;
	}

	@Override
	public Object lastElement() throws EmptyStackException {
		if(last != null){ return last.data; }
		return null;
	}

	@Override
	public Object pollFirst() {
		if(first != null){ 
			return first.data; 
		}

		return null;
	}

	@Override
	public Object pollLast() {
		if(last != null){ 
			return last.data; 
		}

		return null;
	}
	
	@Override
	public String toString(){
		
		String result = "";
		Node current = first;
		if(current != null){result += current.data + ", "; }
		else{return null;}
		while(current.next != null){
			current = current.next;
			result += current.data + ", ";
		}
		return "List: " + result;
		
	}

}
