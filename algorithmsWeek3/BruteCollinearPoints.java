import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
   private LineSegment[] segments;
   
   public BruteCollinearPoints(Point[] points) {
	   if (points == null) throw new java.lang.NullPointerException("Null input!");
	   
	   Arrays.sort(points);
	   
	   ArrayList<LineSegment> seg = new ArrayList<LineSegment>();
	   
	   for (int i = 0; i <= points.length - 4; i++) {
		   for (int j = i + 1; j <= points.length -3; j++) {
			   for (int k = j + 1; k <= points.length -2; k++) {
				   for (int l = k + 1; l <= points.length -1; l++){
					   if (points[i] == null || points[j] == null || points[k] == null || points[l] == null) throw new java.lang.NullPointerException("Null input!");
					   else if (points[i] == points[j] || points[i] == points[k] || points[i] == points[l] || points[j] == points[k] || points[j] == points[l] || points[k] == points[l])
						   throw new java.lang.IllegalArgumentException("Repeated Points!");
					   else if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]))
						   seg.add(new LineSegment(points[i], points[l]));
				   }
			   }
		   }
	   }
	   segments = seg.toArray(new LineSegment[seg.size()]);
   }    // finds all line segments containing 4 points

   public           int numberOfSegments() {
	   return segments.length;
   }        // the number of line segments

   public LineSegment[] segments() {
	   return segments; 
   }               // the line segments
}