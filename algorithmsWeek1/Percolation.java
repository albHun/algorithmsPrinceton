import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private boolean[] sites;
    private WeightedQuickUnionUF uf;
    private int virtualTopSite;
    private int virtualBottomSite;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("The length of the grid must be positive.");
        
        this.n = n;
        sites = new boolean[n*n];
        for (int i = 0; i < n*n; i++)
            sites[i] = false;
        
        uf = new WeightedQuickUnionUF(n*n+2);
        virtualTopSite = n*n;  
        virtualBottomSite = n*n+1;
        
        for (int j = 1; j <= n; j++) {
            uf.union(getPos(1, j), virtualTopSite);
            uf.union(getPos(n, j), virtualBottomSite);
        }
    }
    
    private int getPos(int i, int j) {
        check(i, j);
        return n*i-(n-j+1);
    }
    
    private void check(int i, int j) {
        if (!exist(i, j)) 
            throw new IndexOutOfBoundsException("index out of bounds");
    }    
    
    public boolean isOpen(int i, int j) {     
        check(i, j);        
        return sites[getPos(i, j)];
    } 
     
    public boolean isFull(int i, int j) {    
        check(i, j);
        
        return isOpen(i, j) && uf.connected(virtualTopSite, getPos(i, j));    
    }

    public void open(int i, int j) {
        check(i, j);
        if (!isOpen(i, j))
            this.sites[getPos(i, j)] = true;
        
        if (exist(i-1, j) && isOpen(i-1, j)) { 
            uf.union(getPos(i, j), getPos(i-1, j)); 
        }
        
        if (exist(i+1, j) && isOpen(i+1, j)) { 
            uf.union(getPos(i, j), getPos(i+1, j)); 
        }
        
        if (exist(i, j-1) && isOpen(i, j-1)) { 
            uf.union(getPos(i, j), getPos(i, j-1)); 
        }
        
        if (exist(i, j+1) && isOpen(i, j+1)) { 
            uf.union(getPos(i, j), getPos(i, j+1));
        }        
    }
    
    private boolean exist(int i, int j) {
        if (i <= 0 || i > n) { return false; }
        if (j <= 0 || j > n) { return false; }
        
        return true;
    }
    
    public boolean percolates() {
        if (n == 1) return isFull(1, 1);
        
        if (uf.connected(virtualTopSite, virtualBottomSite)) 
            return true;
        
        return false;
    }    
    
    public static void main(String[] args) {
        Percolation per = new Percolation(1);
        // per.open(1, 1);
        System.out.println(per.percolates());
        
        per = new Percolation(2);
        per.open(1, 1);         
        per.open(1, 2);       
        per.open(2, 2);
        System.out.println(per.isFull(1, 1));
    }
}