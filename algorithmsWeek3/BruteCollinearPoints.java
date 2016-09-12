import LineSegment

public class BruteCollinearPoints {
   public BruteCollinearPoints(Point[] points) {
	   for (int i = 0; i <= points.length - 4; i++) {
		   for (int j = i + 1; j <= points.length -3; j++) {
			   for (int k = j + 1; k <= points.length -2; k++) {
				   for (int l = k + 1; l <= points.length -1; l++){
					   
				   }
			   }
		   }
	   }
   }    // finds all line segments containing 4 points
   public           int numberOfSegments()        // the number of line segments
   public LineSegment[] segments()                // the line segments
}