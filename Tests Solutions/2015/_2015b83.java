/**
 * Solutions for 2015b/83 test
 * Q3-Q6 on bottom
 * @author AvivNaaman
 * @version _2015b83/2020a
 */
public class _2015b83
{
    /* Q1: Recursion */
    /**
     * Returns the longest common substring length of two strings.
     * @param s The first string
     * @param t The second string
     * @return the lcs length of both strings.
     */
    public static int lcs(String s, String t){
        // call overload with initial indexes
        return lcs(s,t,0,0);
    }
    // Overload: indexes for both strings
    private static int lcs(String s, String t, int sIndx, int tIndx){
        // avoid java.lang.ArrayIndexOutOfBoundsException
        if (sIndx >= s.length() || tIndx >= t.length()) return 0;
        // Now, we can increase both indexes, or only one. if we increase both, we should check whether 
        // the chars in out current indexes are the same, if so it's a common char, so we'll add 1 to the sum.
        return Math.max(Math.max(lcs(s,t,sIndx+1,tIndx+1)+(s.charAt(tIndx) == t.charAt(sIndx)?1:0),
                                lcs(s,t,sIndx+1,tIndx)),lcs(s,t,sIndx,tIndx+1));
    }
    
    /* Q2: Efficiency */
    /**
     * Replace each cell value by the biggest value in the array after
     * it's index.
     * @param a The array
     */
    public static void replace(int[] a) {
        // Solution method: set biggest to 0; if bigger found, reset the biggest by the current biggest.
        // of course, each cell will be replaced by the current biggest.
        int biggest = 0;
        for (int i = a.length-1; i >= 0; i--){
            int temp = Math.max(biggest, a[i]); // find biggest yet
            a[i] = biggest; // set cell to last biggest
            biggest = temp;
        }
    }
    
    /*
     * Q3: (i) false (ii) Remove (150) (iii) Whether the nodes count is odd.
     * Q4: A)   1. overload
     *          2. overload
     *          3. override
     *          4. overload
     *          5. illegal - return type
     *          6. overload
     *     B)   1. compilation error - creating instance of abstract class
     *          2. ok
     *          3. compilation error - creating instance of abstract class
     *          4. compilation error - creating instance of abstract class
     *          5. ok
     *          6. compilation error - C doesn't extend B + creating an instance of abstract class
     *          7. comilation error - B doesn't extend C
     * Q5: (a) -1 (b) 2 (c) -1 (d) returns the value which appears in the list more than half of it's length. if no such, returns -1.
     * Q6: (i) 2 (ii) First index where current val is more than last (iii) 7 9 2 3 8 1 4 (iv) sorts the array in bubble-like sort
     */
}
