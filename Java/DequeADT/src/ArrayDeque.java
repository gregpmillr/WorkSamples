import java.util.*;
import java.util.EmptyStackException;

public class ArrayDeque implements Deque {
	Integer list[];
	int maxSize, front, rear, count;
	
	ArrayDeque(int maxSize){
		list = new Integer[maxSize];
		this.maxSize = maxSize;
		this.front = 0;
		this.rear = 0;
	}
	
	@Override
	public int size() {return list.length;}
	
	@Override
	public boolean isEmpty() {
		if(this.size() == 0){
			return true;
		}
		return false;
	}

	@Override
	public void insertFirst(Object o) throws FullStructureException {	
	
		if(count == maxSize){ 
			throw new FullStructureException();
		}else{
			//shift
			for(int i = maxSize-1; i>0; i--){
				list[i] = list[i-1];
			}
		}
		
		front = 0;
		list[front] = (Integer) o;
		rear++;
		count++;
	}

	@Override
	public void insertLast(Object o) throws FullStructureException{
		if(count == maxSize){
			throw new FullStructureException();
		}else {
			list[rear] = (Integer) o;
			rear++;
			count++;
		}
	}
	
	@Override
	public Object removeFirst() throws EmptyStackException {
		if(count == 0){
			throw new EmptyStructureException();
		}
		list[front] = null;
		front = (front + 1) % maxSize;
		count--;
		return (Object) list[0];
	}

	@Override
	public Object removeLast() throws EmptyStackException {
		if(count == 0){
			throw new EmptyStackException();
		}
		list[rear-1] = null;
		rear--;
		count--;
		return null;
	}

	@Override
	public Object firstElement() throws EmptyStackException { 
		if(count == 0){
			throw new EmptyStackException();
		}
		return (Integer) list[front];	
	}

	@Override
	public Object lastElement() throws EmptyStackException { 
		if(count == 0){
			throw new EmptyStackException();
		}
		return (Integer) list[rear-1];
	}
	
	@Override
	public Object pollFirst() {
		
		if(count == 0){return null;}
		else{
			Object element = list[front];
			list[front] = null;
			front = (front + 1) % maxSize;
			count--;
			return element;
		}
	}
	
	@Override
	public Object pollLast(){
		if(count == 0){return null;}
		else{
			Object element = list[rear-1];
			list[rear-1] = null;
			rear--;
			count--;
			return element;
		}
	}
	
	@Override
	public String toString(){
		if(count == 0){return null;}
		else{
			String result = "";
			for(int i = front;i<rear;i++){
				result += list[i] + ", ";
			}
			return "List: " + result;
		}
	}

}
