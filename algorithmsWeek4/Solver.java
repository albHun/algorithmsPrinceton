import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;

public class Solver {
	private boolean isSolvable;
	private int moves;
	private MinPQ<Node> minPQ;
	private Stack<Board> solutionStack = new Stack<Board>();

	private class Node {
		private final Board board;
		private final int manhattan, moves, priority;
		private final Node pre;
		private final boolean isOriginal;

		public Node(Node x, Board b, int moves, boolean isOriginal) {
			this.pre = x;
			this.board = b;
			this.moves = moves;
			this.isOriginal = isOriginal;
			this.manhattan = this.board.manhattan();
			this.priority = this.manhattan + moves;
		}

		public Comparator<Node> orderOfPriority() {
			return new OrderOfPriority();
		}

		private class OrderOfPriority implements Comparator<Node> {
			public int compare(Node a, Node b) {
				if (a.priority < b.priority)
					return -1;
				else if (a.priority > b.priority)
					return +1;
				else
					return 0;
			}
		}
	}

	public Solver(Board initial) {
		if (initial == null)
			throw new java.lang.NullPointerException();
		Board twin = initial.twin();
		Node initialOri = new Node(null, initial, 0, true);
		Node initialTwin = new Node(null, twin, 0, false);

		minPQ = new MinPQ<Node>(initialOri.orderOfPriority());
		minPQ.insert(initialOri);
		minPQ.insert(initialTwin);

		while (true) {
			Node min = minPQ.delMin();
			if (!min.board.isGoal()) {
				for (Board item : min.board.neighbors()) {
					if (min.pre == null) {
						Node next = new Node(min, item, min.moves + 1, min.isOriginal);
						minPQ.insert(next);
					} else if (!item.equals(min.pre.board)) {
						Node next = new Node(min, item, min.moves + 1, min.isOriginal);
						minPQ.insert(next);
					} else
						continue;
				}
			} else {
				isSolvable = min.isOriginal;
				this.moves = min.moves;
				while (min != null) {
					this.solutionStack.push(min.board);
					min = min.pre;
				}
				break;
			}
		}
	}

	public boolean isSolvable() {
		return isSolvable;
	}

	public int moves() {
		if (!isSolvable())
			return -1;
		return this.moves;
	}

	public Iterable<Board> solution() {
		if (!isSolvable())
			return null;
		return solutionStack;
	}

	public static void main(String[] args) {
		int[][] test = { { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 } };
		// int[][] test = { { 1, 6, 2, 4}, { 5,0,3,8 },
		// {9,10,7,11},{13,14,15,12} };
		Board initial = new Board(test);
		Solver s = new Solver(initial);

		if (!s.isSolvable()) {
			StdOut.println("No solution possible!");
		} else {
			StdOut.println("Minimum number of moves = " + s.moves());
			for (Board board : s.solution()) {
				StdOut.println(board);
			}
		}
	}
}