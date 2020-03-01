
/**
 * Solutions for 2016a/91 
 *
 * @author AvivNaaman
 * @version 2016a91/2020a
 */
public class _2016a91
{
    // SORRY! No Q1. I hate chess.
    public static int[] findUFO(Space s) {
        // O(log n), O(1)
        // Bin search for row:
        int foundRow = -1, foundCol = -1;
        int mid = -1, min = 0, max = s.getSize()-1;
        while (min < max) {
            mid = (min+max)/2;
            int res = s.ask(0, mid)[1];
            if (res == 0) break;
            else if (res < 0) max = mid-1;
            else min = mid+1;
        }
        foundRow = mid;
        // Same for col
        mid = -1; min = 0; max = s.getSize()-1;
        while (min < max) {
            mid = (min+max)/2;
            int res = s.ask(mid, foundRow)[0];
            if (res == 0) break;
            else if (res < 0) max = mid-1;
            else min = mid+1;
        }
        foundCol = mid;
        return new int[]{foundRow, foundCol};
    }
    /*
     * Q3-Q6:
     * Q3: (i) 4 (ii) Returns the number of leafs, which have father with 2 sons or 1 son and that son's data is more than it's father.
     * Q4:  (1) 2 (2) Compilation Error (3) 2 (4) B::f2(B)
     *      (5) B::f1(Object) (6) B::f2(B) (7) Runtime Error
     *      (8) C::f2 (9) C::f2 (10) C::f2 (11) Compilation Error, Symbol not found
     *      (12) C::f2
     * Q5:  (i) Puts the n as a node at the end of the list
     *      (ii) 2->2->5 (iii) 2->2->2->2->2 
     *      (iv) Returns a NodeList which contains the divisors of the number since 2 in ascending order.
     * Q6:  (i) 42 (ii) Returns the biggest sum of ascending order in the array.
     */
}
/**
 * An interface for space class, which isn't defined beyond th described here.
 */
interface Space {
    /**
     * Returns the 2-int position array
     */
    int[] ask(int x, int y);
    /**
     * Returns the Space size
     */
    int getSize();
}
