import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;

public class Board {
	private final int[][] block;
	private final int n; // dimension of the board
	
	public Board(int[][] blocks) {
		n = blocks.length;
		this.block = new int[n][];
		for (int i = 0; i < n; i++) 
			this.block[i] = Arrays.copyOf(blocks[i], n); 
	}
	
	public int dimension() {
		return n;
	}
	
	public int hamming() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (block[i][j] != 0 && block[i][j] != i * n + j + 1)
					count ++;
			}
		}
		return count;
	}
	
	public int manhattan() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (block[i][j] != 0) {
					count += Math.abs((block[i][j] - 1) / n - i);
					count += Math.abs((block[i][j] - 1) % n - j);
				}
			}
		}
		return count;
	}
	
	public boolean isGoal() {
		return (this.hamming() == 0);
	}
	
	public Board twin() {
		int[][] twin = new int[n][];
		twin = new int[n][];
		for (int i = 0; i < n; i++) 
			twin[i] = Arrays.copyOf(block[i], n); 
		if (block[0][0] != 0) {
			if (block[0][1] != 0) {
				twin[0][0] = block[0][1];
				twin[0][1] = block[0][0];
			}
		}
		else {
			twin[0][1] = block[1][0];
			twin[1][0] = block[0][1];
		}
		return new Board(twin);
	}
	
	public boolean equals(Object y) {
		if (y == null) return false;
		if (y == this) return true;
		if (y.getClass() != this.getClass()) return false;
		Board that = (Board) y;
		if (this.n != that.n) return false;
		if (!Arrays.deepEquals(this.block, that.block)) return false;
		return true;
	}
	
	public Iterable<Board> neighbors() {
		Queue<Board> q = new Queue<Board>();
		nextGeneration(q);
		return q;
	}
	
	private void nextGeneration(Queue<Board> q) {
		int i = 0, j = 0;
		boolean find = false;
		for (int k = 0; k < n; k++) {
			for (int l = 0; l < n; l++) {
				if (block[k][l] == 0) {
					i = k;
					j = l;
					find = true;
					break;
				}
			}
			if (find) break;
		}
		
		if (j - 1 >= 0) {
			Board temp = new Board(block);
			exch(temp.block, i, j, i, j - 1);
			q.enqueue(temp);
		}
		if (i - 1 >= 0) {
			Board temp = new Board(block);
			exch(temp.block, i, j, i - 1, j);
			q.enqueue(temp);
		}
		if (j + 1 <= n - 1) {
			Board temp = new Board(block);
			exch(temp.block, i, j, i, j + 1);
			q.enqueue(temp);
		}
		if (i + 1 <= n - 1) {
			Board temp = new Board(block);
			exch(temp.block, i, j, i + 1, j);
			q.enqueue(temp);
		}
	}
	
	private void exch(int[][] a, int i1, int j1, int i2, int j2) {
		int temp = a[i1][j1];
		a[i1][j1] = a[i2][j2];
		a[i2][j2] = temp;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(n + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", block[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		int[][] test = { {1, 2, 3}, {4, 0 ,6}, {7, 8, 5}};
		Board b = new Board(test);
		Iterable<Board> q = b.neighbors();
		for (Board item : q) {
			System.out.println(item);
		}
		System.out.println(b.twin());
	}
}