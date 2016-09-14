import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;
    
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException("Null input!");
        
        Arrays.sort(points);
       
        ArrayList<LineSegment> seg = new ArrayList<LineSegment>();
        
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new java.lang.NullPointerException("Null input!");
            else {
                double[] slopePoints = new double[points.length-1];
                int count = 0;
                for (int j = 0; j < points.length; j++) {
                    if (i == j) j++;
                    else {
                        slopePoints[count] = points[i].slopeTo(points[j]);
                        if (slopePoints[count] == Double.NEGATIVE_INFINITY) 
                            throw new java.lang.IllegalArgumentException("Repeated points!");
                        else count++;
                    }
                }
            Arrays.sort(slopePoints);
            //for (int j = 0; j < points.length-3; j++)
              //  if (slopePoints[j] == slopePoints[j + 1] && slopePoints[j + 2] == slopePoints[j + 1])
                //    seg.add(new LineSegment(points[i], points[j + 2]));
            //}
			
			}
        segments = seg.toArray(new LineSegment[seg.size()]);
		}
	}
    
    public int numberOfSegments() {
        return segments.length;
    }
    
    public LineSegment[] segments() {
        return segments;
    }
}