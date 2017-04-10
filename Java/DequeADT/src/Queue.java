
public interface Queue {
	public void enqueue(Object o);
	public boolean offer(Object o);
	public Object peek();
	public Object poll();
	public Object dequeue();
	public String toString();
	
}
