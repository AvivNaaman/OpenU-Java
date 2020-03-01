
/**
 * Solutions for _2016b87
 *
 * @author AvivNaaman
 * @version 2016b87/2020a
 */
public class _2016b87
{
    /**
     * Returns the number of ascending progression which their sum equals to 'sum' param
     * @param sum The sum
     * @return The number of ascending progressions
     */
    public static int count(int sum) {
        return count(sum, 0);
    }
    private static int count(int sum, int lastNum) {
        if (lastNum > sum) return 0; // lastNum too big
        if (sum == lastNum) return 1; // exactly the right sum
        if (lastNum == 0) return count(sum-1, 1)+1; // if first, start with 0. add the singleton {sum}.
        return count(sum-lastNum, lastNum+1) + count(sum, lastNum+1); // another progression part or increase this part
    }
    /* Q2: go to /Works/14/Ex14.java */
    /*
     * Q3-Q6:
     * Q3:  (i) 3 (ii) The length of the path from the root to the first node with the value x
     * Q4:  (1) B false (2) C true (3) false (4) false (5) D true (6) C true 
     *      (7) A true (8) A true (9) B true (10) B false (11) A true (12) false
     * Q5:  true: (1)-(2),(5) false: (3)-(4)
     * Q6:  O(1)/O(n) | O(n)/O(n^2) | O(n)/O(n^2) | O(n)/O(n) | O(n+k)/O(n) | O(n)/O(n^2)
     */
}
