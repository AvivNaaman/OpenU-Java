/**
 * Solutions for 2015b/86 test
 * Q3-Q6 on bottom
 * @author AvivNaaman
 * @version 2015b86/2020a
 */
public class _2015b86 {
    /**
     * Prints all the binary strings which represent the given sum in the given array.
     * @param a The array
     * @param sum The Sum
     */
    public static void printAllSum(int[] a, int sum) {
        printAllSum(a, sum, 0, "");
    }
    // Overload: index+string
    public static void printAllSum(int[] a, int sum, int indx, String toPrint) {
        if (indx >= a.length) {
            if (sum == 0) System.out.println(toPrint); // if end of array and we reached the sum, print the bin string
            return; // stop
        }
        printAllSum(a, sum-a[indx],indx+1,toPrint+"1"); // include the current cell
        printAllSum(a, sum, indx+1, toPrint+"0"); // exclude the current cell
    }
    
    /**
     * Prints the pairs which their substraction result is k.
     * @param a The pair numbers aray
     * @param k The substraction required result
     */
    public static void printPairs(int[] a, int k) {
        // Solution: 2 pointers, starting at [0] and [1]
        if (a.length < 2) return;
        int p1 = 0, p2=1;
        while (p2 < a.length-1) {
            if (a[p2]-a[p1] == k) System.out.println("Pair Found: ("+a[p1]+","+a[p2]+")");
            if (p2-p1==1 || a[p2]-a[p1]<k) p2++; else p1++;
        }
        if (a[p2]-a[p1] == k) System.out.println("Pair Found: ("+a[p1]+","+a[p2]+")");
    }
}