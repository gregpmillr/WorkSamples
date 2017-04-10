
public class Test {
	static DequeStack aStack;
	static DequeStack llStack;
	static DequeQueue aQueue;
	static DequeQueue llQueue;
	
	public void Deques(){
		aStack = new DequeStack(new ArrayDeque(12));
		llStack = new DequeStack(new LinkedListDeque());
		aQueue = new DequeQueue(new ArrayDeque(12));
		llQueue = new DequeQueue(new LinkedListDeque());
	}
	

	public void adding(){
		stackAdding();
		queueAdding();
	}
	

	public void removing(){
		stackRemoving();
		queueRemoving();
	}
	
	public void exceptions(){
		stackExceptions();
		queueExceptions();
	}	

	public void viewing(){
		stackViewing();
		queueViewing();
	}
	

	public void output(){
		System.out.println("ArrayQueue toString: " + aQueue.toString());
		System.out.println("LinkedListQueue toString: " + llQueue.toString());
		
		System.out.println("ArrayStackDeque toString: " + aStack.toString());
		System.out.println("LinkedListStackDeque toString: " + llStack.toString());
	}
	

	public void stackAdding(){
		System.out.println("Pushing to stacks");
		for(int i = 0; i < aStack.size(); i++){	aStack.push(i);}
		for(int i = 0; i < 12; i++){ llStack.push(i); }
	}
	

	public void queueAdding(){
		System.out.println("Adding to queues");
		for(int i = 0; i < 5; i++){	aQueue.insertFirst(i); }
		for(int i = 5; i < 9; i++){	aQueue.insertLast(i); }
		for(int i = 9; i < 12; i++){ aQueue.enqueue(i); }
		
		for(int i = 0; i < 5; i++){	llQueue.insertFirst(i); }
		for(int i = 5; i < 9; i++){ llQueue.insertLast(i); }
		for(int i = 9; i < 12; i++){ llQueue.enqueue(i); }
	}
	

	public void stackRemoving(){
		System.out.println("Removing from stacks");
		for(int i = 0; i < aStack.size(); i++){ aStack.pop(); }
		for(int i = 0; i < llStack.size(); i++){ llStack.pop(); }
	}
	

	public void queueRemoving(){
		System.out.println("Removing from queues");
		for(int i = 0; i < 5; i++){ 
			aQueue.removeFirst(); 
		}
		for(int i = 5; i < 9; i++){	
			aQueue.removeLast();
		} 

		System.out.println("ArrayQueue poll: " + (Integer) aQueue.poll());
		System.out.println("ArrayQueue pollFirst: " + (Integer)  aQueue.pollFirst());
		System.out.println("ArrayQueue pollLast: " + (Integer) aQueue.pollLast());
		
		for(int i = 0; i < 5; i++){ llQueue.removeFirst(); }
		for(int i = 5; i < 9; i++){	llQueue.removeLast();} 
		for(int i = 9; i < 12; i++){ llQueue.dequeue(); }
	}
	

	public void stackExceptions(){
		System.out.println("Forcing exceptions to be thrown");
		aStack.pop();
		llStack.pop();
	}
	
	public void stackViewing(){
		System.out.println("Executing view methods on stacks");
		System.out.println("ArrayStackDeque isEmpty: " + aStack.isEmpty());
		System.out.println("ArrayStackDeque size: " + aStack.size());
		System.out.println("ArrayStackDeque top: " + aStack.top());
		
		System.out.println("LinkedListStackDeque isEmpty: " + llStack.isEmpty());
		System.out.println("LinkedListStackDeque size: " + llStack.size());
		System.out.println("LinkedListStackDeque top: " + llStack.top());
	}
	
	public void queueViewing(){
		System.out.println("\nExecuting view methods on queues");
		System.out.println("ArrayQueue front: " + aQueue.front());
		System.out.println("ArrayQueue isEmpty: " + aQueue.isEmpty());
		System.out.println("ArrayQueue peek: " + aQueue.peek());
		System.out.println("ArrayQueue peekLast: " + aQueue.peekLast());
		System.out.println("ArrayQueue size: " + aQueue.size());
		
		System.out.println("LinkedListQueue front: " + llQueue.front());
		System.out.println("LinkedListQueue isEmpty: " + llQueue.isEmpty());
		System.out.println("LinkedListQueue peek: " + llQueue.peek());
		System.out.println("LinkedListQueue peekLast: " + llQueue.peekLast());
		System.out.println("LinkedListQueue poll: " + (Integer) llQueue.poll());
		System.out.println("LinkedListQueue pollFirst: " + (Integer) llQueue.pollFirst());
		System.out.println("LinkedListQueue pollLast: " + (Integer) llQueue.pollLast());
		System.out.println("LinkedListQueue size: " + llQueue.size());
	}
	
	
	public void queueExceptions(){
		aQueue.dequeue();
		aQueue.removeFirst();
		aQueue.removeLast();
		llQueue.dequeue();
		llQueue.removeFirst();
		llQueue.removeLast();
	}
	 
}
