import java.util.*;

public class DequeStack implements Stack {
	   Deque deque;
	   DequeStack(Deque o) { deque = o; }
	   public int size() { return deque.size(); }
	   public boolean isEmpty() { return deque.isEmpty(); }
	   public Object top() { return deque.lastElement(); }
	   public Object pop() { return deque.removeLast(); }
	   public void push(Object o) { deque.insertLast(o); }
	   public String toString() { return deque.toString(); }
	   
}
