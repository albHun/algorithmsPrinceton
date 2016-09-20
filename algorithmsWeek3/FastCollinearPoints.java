import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class FastCollinearPoints {
    private LineSegment[] segments;
    
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException("Null input!");
        
        Arrays.sort(points);
       
        ArrayList<LineSegment> seg = new ArrayList<LineSegment>();
        
        for (Point p : points) {
            if (p == null) throw new java.lang.NullPointerException("Null input!");
            else {
				Point[] aux = new Point[points.length];
				aux = points;
				
				Arrays.sort(aux, p.slopeOrder());
				
				int count = 0;
				int start = 0;
				double init_slope = p.slopeTo(aux[0]);
				if (aux[0].compareTo(p) == 0) {
					init_slope = p.slopeTo(aux[1]);
					start = 1;
				}
				for (int i = 0; i < points.length; i++) {
					if (aux[i].compareTo(p) == 0) i++;
					else if (p.slopeTo(aux[i]) == Double.NEGATIVE_INFINITY) 
                        throw new java.lang.IllegalArgumentException("Repeated points!");
					else if (p.slopeTo(aux[i]) == init_slope) count ++;
					else {
						if (count >= 3) {
							Point[] collinearPoints = new Point[count + 1];
							collinearPoints = Arrays.copyOfRange(aux, start, start + count);
							collinearPoints[count] = p;
							Arrays.sort(collinearPoints);
							LineSegment segment1 = new LineSegment(collinearPoints[0], collinearPoints[count]);
							LineSegment segment2 = new LineSegment(collinearPoints[count], collinearPoints[0]);
							if (checkDuplicate(seg, segment1) == false && checkDuplicate(seg, segment2) == false) seg.add(segment1);
						}
						
						count = 1;
						start = i;
						init_slope = p.slopeTo(aux[i]);
					}
				}			
			}
		}
		segments = seg.toArray(new LineSegment[seg.size()]);
	}
    
	private boolean checkDuplicate(ArrayList<LineSegment> segCol, LineSegment line) {
		return segCol.contains(line);
	}
	
    public int numberOfSegments() {
        return segments.length;
    }
    
    public LineSegment[] segments() {
        return segments;
    }
	
	public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
}
}