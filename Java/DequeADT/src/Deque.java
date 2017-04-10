import java.util.*;

public interface Deque {
   public int size();
   public boolean isEmpty();
   public void insertFirst(Object o) throws FullStructureException;
   public void insertLast(Object o) throws FullStructureException;
   public Object removeFirst() throws EmptyStackException;
   public Object removeLast() throws EmptyStackException;
   public Object firstElement() throws EmptyStackException;
   public Object lastElement() throws EmptyStackException;
   public Object pollFirst();
   public Object pollLast();
   public String toString();
   
}
