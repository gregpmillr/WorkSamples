
public class DequeQueue implements Queue {

    Deque deque;
    DequeQueue(Deque o) { deque = o; }
	   
    public int size() { return deque.size(); }
    public boolean isEmpty() { return deque.isEmpty(); }
    public Object front() { return deque.firstElement(); }
    public void enqueue(Object o) { deque.insertLast(o);  }
    public Object dequeue() { return deque.removeFirst(); }
	
    public void insertFirst(Object o) { deque.insertFirst(o); }
    public void insertLast(Object o) { deque.insertLast(o); }
    public void removeFirst() { deque.removeFirst(); }
    public void removeLast() { deque.removeLast(); }
	public Object pollFirst(){ return deque.pollFirst(); } //retrieves and removed the first elements of this deque, or returns null if this deque is empty
	public Object pollLast(){ return deque.pollLast(); } //retrieves and removed the last elements of this deque, or returns null if this deque is empty
	public String toString() { return deque.toString(); }

	@Override
	public boolean offer(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object peek() { return deque.lastElement(); }	
	
	public Object peekLast() { return deque.lastElement(); }
	
	@Override
	public Object poll() { return deque.pollFirst(); }
	


}
