import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private int n; 
    private int tMax; 
    private double[] results;
    
    public PercolationStats(int n, int tMax) {        
        if (n <= 0 || tMax <= 0) {
            throw new java.lang.IllegalArgumentException(
                           "n or tMax must be greater than 0");
        }      
        
        this.n = n;
        this.tMax = tMax;
        results = new double[tMax];
        
        for (int t = 0; t < tMax; t++) {
            results[t] = run();
        } 
    }
    
    private double run() {        
        Percolation percolation = new Percolation(n);        
        double count = 0;
        
        while (!percolation.percolates()) {        
            count++;
            
            // pick a random site 
            // (n+1 because second value to uniform is exclusive)
            int i = StdRandom.uniform(1, n+1);
            int j = StdRandom.uniform(1, n+1);
            
            // generate new random sites until a blocked one is found
            while (percolation.isOpen(i, j)) {
                
                i = StdRandom.uniform(1, n+1);
                j = StdRandom.uniform(1, n+1);
                
            }
            
            // open that site
            percolation.open(i, j);
        
        }
        return count / (n*n); // percolation threshold estimate
    }
    

    public double mean() {
        return StdStats.mean(results);
    }
    
    public double stddev() {
        return StdStats.stddev(results);
    }
    
    public double confidenceHi() {
        return  mean() - 1.96 * stddev() / Math.sqrt(tMax);    
    }
    
    public double confidenceLo() {
        return mean() + 1.96 * stddev() / Math.sqrt(tMax);
    }
    
    public static void main(String[] args) {
       
        int n;
        int tMax;
  
        if (args.length == 0) {
            n = 100;
            tMax = 10;
        } else {
            n = Integer.parseInt(args[0]);
            tMax = Integer.parseInt(args[1]);
        }              
        
        PercolationStats stats =  new PercolationStats(n, tMax);
        
        double confidenceLow  = stats.confidenceHi();        
        double confidenceHigh = stats.confidenceLo();
                
        System.out.println("mean                    = "  + stats.mean()); 
        System.out.println("stddev                  = "  + stats.stddev());
        System.out.println("95% confidence interval = "  + confidenceLow 
                               + ", " + confidenceHigh);  
        
        // performance measuring
            
    }
}