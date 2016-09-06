public class Deque<Item> implements Iterable<Item> {
   private class Node{
	   Item item;
	   Node next;
   }
   
   public Deque(){
	   Node first = null, last = first;
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
	   Node oldfirst = first;
	   first.item = item;
	   first.next = oldfirst;
   }          // add the item to the front
   
   public void addLast(Item item){
	   last = new Node();
	   last.item = item;
	   last.next = null;
	   last = last.next;	   
   }           // add the item to the end
   
   public Item removeFirst(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   else{
		   oldfirst = first;
		   first = first.next;
		   return oldfirst.item;
	   }
   }                // remove and return the item from the front
   
   public Item removeLast(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   else {
		   Node tempfirst = first;
		   while (first.next.next != null) first = first.next;
		   first.next = null;
		   first = tempfirst;
	   }
   }                 // remove and return the item from the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing
}