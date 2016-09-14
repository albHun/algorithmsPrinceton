import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
   private LineSegment[] segments;
   
   public BruteCollinearPoints(Point[] points) {
       if (points == null) throw new java.lang.NullPointerException("Null input!");
       
       Arrays.sort(points);
	   for (int i = 0; i <= points.length - 1; i++) {
		   if (points[i] == null) throw new java.lang.NullPointerException("Null input!");
	   }
	   for (int i = 0; i <= points.length - 2; i++) {
		   if (points[i].compareTo(points[i + 1]) == 0) throw new java.lang.IllegalArgumentException("Repeated Points!");
	   }
	          
       ArrayList<LineSegment> seg = new ArrayList<LineSegment>();
       
       for (int i = 0; i <= points.length - 4; i++) {
           for (int j = i + 1; j <= points.length -3; j++) {
               for (int k = j + 1; k <= points.length -2; k++) {
                   for (int l = k + 1; l <= points.length -1; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) 
                           && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]))
                           seg.add(new LineSegment(points[i], points[l]));
                   }
               }
           }
       }
       segments = new LineSegment[seg.size()];
	   seg.toArray(segments);
   }    // finds all line segments containing 4 points

   public           int numberOfSegments() {
       return segments.length;
   }        // the number of line segments

   public LineSegment[] segments() {
       return segments; 
   }               // the line segments
}