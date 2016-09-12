import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (this.x == that.x) {
			if (this.y == that.y) return Double.NEGATIVE_INFINITY;
			else return Double.POSITIVE_INFINITY;
		}
		else if (this.y == that.y) return +0.0;
		else {
			double slope = ((that.y - this.y) * 1.0) / (that.x - this.x);
			return slope;
		}
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
		else if (this.y == that.y) {
			if (this.x < that.x) return -1;
			else if (this.x == that.x) return 0;
			else return 1;
		}
		else return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
		return new order();
	} 
	
	private class order implements Comparator<Point> {
		public int compare(Point p1, Point p2) {
			double s1 = slopeTo(p1), s2 = slopeTo(p2);
			if (s1 == s2) return 0;
			else if (s1 > s2) return 1;
			else return -1;
		}
	}


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * You should not call the hashCode() method on this assignment.
     * This means that you should not use java.util.HashMap or java.util.HashSet.
     *
     * @return a string representation of this point
     */
    public int hashCode() {
        /* DO NOT MODIFY */
        throw new UnsupportedOperationException("calling hashCode() is not permitted on this assignment");
    }

    /**
     * You should not call the equals() method on this assignment.
     * This means that you should not use java.util.TreeMap or java.util.TreeSet.
     */
    public boolean equals(Object that) {
        /* DO NOT MODIFY */
        throw new UnsupportedOperationException("calling equals() is not permitted on this assignment");
    }


    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        StdDraw.setXscale(0, 5);
        StdDraw.setYscale(0, 5);
            
        Point p = new Point(0, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(2, 3);
        Point p5 = new Point(3, 2);           

        p.drawTo(p2);
        p.drawTo(p3);
        p.drawTo(p4);
        p.drawTo(p5);
		
		Comparator<Point> f = p.slopeOrder();
        
        System.out.println(f.compare(p2, p3));
        System.out.println(f.compare(p3, p4));
        System.out.println(f.compare(p4, p5));
        
        System.out.println(p3.slopeTo(p5));
        System.out.println(p3.slopeTo(p4));
    }
}