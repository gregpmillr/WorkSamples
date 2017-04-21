
public class StackImplementation implements Stack {
	
	private Node node; // top of stack
	private int n; // size of stack
	
	static class Node {
		private int data;
		private Node prev;
	}
	
	StackImplementation() {
		node = null;
		n = 0;
	}

	@Override
	public void push(int data) {
		Node old = node;
		node = new Node();
		node.data = data;
		node.prev = old;
		n++;
	}

	@Override
	public int pop() {
		if(node == null) {
			System.out.println("stack underflow");
			return 0;
		}
		int data = node.data;
		node = node.prev;
		n--;
		return data;
	}

	@Override
	public int peek() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
