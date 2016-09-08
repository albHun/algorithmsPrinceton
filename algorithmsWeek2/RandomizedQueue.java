import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] s;
   private int N = 0;
   
   private void resize(int capacity){
	   Item[] copy = (Item[]) new Object[capacity];
	   for (int i = 0; i < N; i++)
	       copy[i] = s[i];
	   s = copy;
   }
   
   public RandomizedQueue(){
	   s = (Item[]) new Object[1];
   }                 // construct an empty randomized queue
   
   public boolean isEmpty(){
	   return N == 0;
   }                 // is the queue empty?
   
   public int size(){
	   return s.length;
   }                        // return the number of items on the queue
   
   public void enqueue(Item item){
	   if (item == null) throw new NullPointerException();
	   if (s.length == N ) resize(2 * s.length);
	   s[N++] = item;
   }           // add the item
   
   public Item dequeue(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   int index = StdRandom.uniform(N);
	   Item temp = s[index];
	   for (int i=index; i < N; i++)
		   s[i] = s[i+1];
	   N--;
	   if (s.length >= 4 * N) resize(s.length / 2);
	   return temp;
   }                    // remove and return a random item
  
   public Item sample(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   int index = StdRandom.uniform(N);
	   Item temp = s[index];
	   return temp;
   }                     // return (but do not remove) a random item
   
   public Iterator<Item> iterator(){
	   return new myIterator();
   } 

	private class myIterator implements Iterator<Item> {
		private int[] randomIndex;
		private int i;

		public myIterator() {
			i = 0;
			randomIndex = new int[N];
			for (int j = 0; j < N; j++) {
				randomIndex[j] = j;
			}
			StdRandom.shuffle(randomIndex);
		}

		public boolean hasNext() {
			return i < N;
		}

		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException(
						"Cannot show next item when there are no more items to return!");
			}
			return s[randomIndex[(i++)]];
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException("Cannot call remove() in Iterator!");
		}
	}
   
   public static void main(String[] args){
	   RandomizedQueue<String> test = new RandomizedQueue<String>();
	   System.out.println(test.isEmpty());
	   System.out.println(test.size());
	   test.enqueue("Jiang");
	   test.enqueue("Qiao");
	   test.enqueue("Chu");
	   System.out.println(test.dequeue());
	   System.out.println(test.isEmpty());
	   System.out.println(test.size());
	   System.out.println(test.sample());
	   Iterator<String> itest = test.iterator();
	   for (String p : test)
		   System.out.println(p);
   }   // unit testing
}