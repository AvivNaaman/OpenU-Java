/**
 * Solutions for 2016b/82
 * @author AvivNaaman
 * @version 2016b82/2020a
 */
public class _2016b82 {
    /**
     * Returns an index where the array is fully balanced around it
     * @param vec the array to look for the index in.
     * @return the index of full-balanced array
     */
    public static int where(int[] vec) {
        return where(vec, 0, 1, vec.length-1);
    }
    // overload by tami's plan
    private static int where(int[] vec, int left, int p, int right) {
        if (p == vec.length) return -1;
        return Math.max((sum(vec, left, p-1)-sum(vec, p, right))==0?p:-1, where(vec, left, p+1, right));
    }
    /**
     * Returns whether the array is k-balanced
     * @param vec The array
     * @param k The balance to check
     * @return whether the array is k-balanced
     */
    public static boolean isBalanced(int[] vec, int k) {
        return isBalanced(vec, k, 0, 1);
    }
    private static boolean isBalanced(int[] vec, int k, int indx, int otherLength) {
        if (otherLength < 0 || indx < vec.length-1 || otherLength+k+indx > vec.length-1 || indx+otherLength > vec.length-1) 
            return false;
        return sum(vec, indx, indx+k)-sum(vec, indx+k, indx+k+otherLength)==0 ||
               sum(vec, indx, indx+otherLength)-sum(vec, indx+otherLength, indx+k+otherLength)==0||
               isBalanced(vec, k, indx+1, otherLength) || isBalanced(vec, k, indx, otherLength+1);
    }
    // sums up the array from index low to high, including both. GIVEN!
    private static int sum(int[] vec, int low, int high) {
        int sum = 0;
        for (int i = low; i < high; i++) sum+= vec[i];
        return sum;
    }
    /**
     * Returns whether the sum x is creatable by using the numbers in the array, represented by the ranges array.
     * @param a The ranges array
     * @param x The number to look for it's sum.
     */ 
    public static boolean isSum(Range[] a, int x) {
        // Two Pointers
        int p1 = 0, p2 = a.length-1;
        while (p1 <= p2) {
            if (a[p1].getSmallest()+a[p2].getSmallest() <= x && a[p1].getBiggest() >= a[p2].getBiggest()) {
                return true;
            }
            if (a[p1].getBiggest() + a[p2].getBiggest() > x) p2--;
            else p1++;
        }
        return false;
    }
    /*
     * Q3-Q6:
     * Q3: (i)  (ii) prints the way to get to every leaf from the root, 0 for lef turn and 1 for right turn
     * Q4: (a) 1 true\n1 false\nfalse\n1 false\n1 true\n2 true
     *     (b) 1 true\n1 false\nfalse\n3 true\n3 true\n2 true
     * Q5: (i) 6 -3 0 0 3 4 1 -4 2 2 5 (ii) sorts the list to be 3-modulus sorted ascending (foreach x, x%?=0 first, x%?=2 last)
     * Q6: (i) O(n) / O(1) (ii) O(1) / O(n) (iii) O(log n) / O(n) (iv) O(n) / O(n) (v) O(n) / o(nlog n) (vi) O(1) / O(n)
     */
}
/**
 * a Class which defines a range of single number sequence.
 */
class Range {
    private int _smallest, _biggest;
    /**
     * Creates a new range.
     * @param s The smallest number in range
     * @param b The biggest number in range
     */
    public Range(int s, int b) {
        _smallest = s;
        _biggest = b;
    }
    /**
     * Returns the smallest number in range.
     * @return The smallest numebr in range
     */
    public int getSmallest() {
        return _smallest;
    }
    /**
     * Returns the biggest number in range.
     * @return The bigest numebr in range
     */
    public int getBiggest() {
        return _biggest;
    }
}