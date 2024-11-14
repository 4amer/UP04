public class IntervalChecker {
    public static void main(String[] args) {
        double n = 2.0; 
        double m = 5.0;
        double k = 3.0;

        boolean inOpenClosed = (n < k && k <= m);
        boolean inClosedOpen = (n <= k && k < m);  
        boolean inOpenOpen = (n < k && k < m);      
        boolean inClosedClosed = (n <= k && k <= m); 

        System.out.println("Value k belong to interval (n, m]: " + inOpenClosed);
        System.out.println("Value k belong to interval  [n, m): " + inClosedOpen);
        System.out.println("Value k belong to interval  (n, m): " + inOpenOpen);
        System.out.println("Value k belong to interval  [n, m]: " + inClosedClosed);
    }
}
