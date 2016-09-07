import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
   private class Node{
	   Item item;
	   Node next;
   }
   
   private Node first;
   private Node last;
   
   public Deque(){
	   first = null;
	   last = first;
   }   // construct an empty deque
   
   public boolean isEmpty(){
	   return first == null;
   }                 // is the deque empty?
   
   public int size(){
	   int count = 0;
	   Node iter = first;
	   while (iter != null){
		   iter = iter.next;
		   count ++;
	   }
	   return count;
   }                        // return the number of items on the deque
   
   public void addFirst(Item item){
	   if (item == null) throw new java.lang.NullPointerException();
	   else{
		   Node oldfirst = first;
	       first.item = item;
	       first.next = oldfirst;
	   }
   }          // add the item to the front
   
   public void addLast(Item item){
	   if (item == null) throw new java.lang.NullPointerException();
	   else{
		   last = new Node();
		   last.item = item;
	       last.next = null;
	       last = last.next;
	   }
   }           // add the item to the end
   
   public Item removeFirst(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   else{
		   Node oldfirst = first;
		   first = first.next;
		   return oldfirst.item;
	   }
   }                // remove and return the item from the front
   
   public Item removeLast(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   else {
		   Node tempfirst = first;
		   while (first.next.next != null) first = first.next;
		   Node templast = first.next;
		   first.next = null;
		   first = tempfirst;
		   return tempfirst.item;
	   }
   }                 // remove and return the item from the end
   
   public Iterator<Item> iterator(){ return new ListIterator(); }   // return an iterator over items in order from front to end
   
   private class ListIterator implements Iterator<Item>{
	   private Node current = first;
	   
	   public boolean hasNext(){ return current != null; }
	   public void remove(){ throw new UnsupportedOperationException(); }
	   public Item next(){
		   Item item = current.item;
		   current = current.next;
		   return item;
	   }
   }
   
   public static void main(String[] args){
	   Deque<Integer> test = new Deque<Integer>();
	   System.out.println(test.isEmpty());
	   System.out.println(test.size());
	   test.addFirst(5);
	   test.addLast(6);
	   test.removeFirst();
	   test.removeLast();
   }   // unit testing
}