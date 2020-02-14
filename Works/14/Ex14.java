/**
 * MMN 14 solutions - Complexity & Recursion
 *
 * @author Aviv Naaman
 * @version 2020a
 */
public class Ex14
{
    /* Exercise 1 */
    /**
     * Returns how much substrings of string s are
     * with the char c at beginning, end and middle.
     * 
     * Complexity: Time O(n), Space O(1) (all vars are constants, one loop by string length)
     * 
     * @param s The string to check her substrings with the char.
     * @param c The char to look a the beginning, end and middle of substring in the string.
     * @return The amount of substring contains the char in middle, beginning and end.
     */
    public static int subStrC(String s, char c) {
        // count how much times does the char appear in string: (the complexity of this loop is O(n)!)
        int numOfCs = 0;
        // for each char, if match, increase counter - time complexity O(n) and space O(1)
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c)
                numOfCs++;
        
        // then, if the char appears less than 3 times, 
        // there are no substring with it 3 times at all!
        // otherwise. let's find injection between each substring and the last char. that means,
        // that we count how much last chars there are - so we'll know the amount of substrings.
        // we have only two chars in the string which aren't last for any, and these are the first and the second ones. so let's substract them.
        return numOfCs > 2 ? numOfCs-2 : 0;
    }
    
    /**
     * Returns the amount of substrings of the given string, with maximum of k chars c
     * in the middle, and c in beginning and end.
     *
     * Complexity: Time O(n) Space O(1) (two linear loops with max of string length, max of string length iterations, vars num is constant)
     *
     * @param s The String to look for it's substrings with maximum of k cs
     * @param c The char to look for maximum k times in middle of the string substrings
     * @param k the maximum amount of c chars to look in the middle.
     * @return The amount of substrings containing c in their beginning, end, and k times in middle.
     */
    public static int subStrMaxC(String s, char c, int k) {
        // count c chars in the string - Complexity of loop is: Time O(n) Space O(1)
        int numOfCs = 0;
        // for each char, if match, increase counter - time complexity O(n) and space O(1)
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c)
                numOfCs++;
        
        int finalCounter = 0;
        // for each value less than k, use the same method as previous
        // method to check the number of substrings with i c chars in middle:
        // if (k's grater than the string length, then for sure maximum times the char's in the string
        //      is it's length, otherwise just the k: (Complexity of loop is maximally Time O(n) and Space always O(1))
        int iterations = Math.min(k, numOfCs);
        for (int i = 0; i <= iterations; i++) {
            finalCounter += numOfCs > i+1 ? numOfCs-(i+1) : 0;
        }
        return finalCounter;
    }
    /* END Exercise 1*/
    
    /* Question 2 */
    /**
     * Sets each cell in the given array, to the distance between it and the closest zero cell in the array
     *  only if it's value's 1.
     *
     *  Complexity: Time O(n) Space O(1) (Two linear loops, vars num is constant)
     *
     * @param a Array of 0s & 1s to be replaced
     */
    public static void zeroDistance(int[] a) {
        final int arrLength = a.length;
        // iterate from first value to last:
        // initial distance value (distance is always positive, so we know that haven't met any 0 yet)
        int lastValue = -1;
        // loop complexity - time O(n) and space O(1)
        for (int i = 0; i < arrLength; i++) {
            // if we're on 0 cell, reset the distance counter.
            if (a[i] == 0) {
                lastValue = 0;
            }
            // otherwise, 
            else {
                // if no value defined yet, let's set the cell value to Integer.MAX_VALUE.
                // we'll see the use of it later.
                if (lastValue == -1) {
                    a[i] = Integer.MAX_VALUE;
                }
                // otherwise, let's increase distance and set the current cell to the increased.
                else {
                    a[i] = ++lastValue;
                }
            }
        }
        lastValue = -1;
        // now let's iterate from last to first: (loop complexity - time O(n) and space O(1))
        for (int i = arrLength-1; i > -1; i--) {
            // again, reset distance counter if current cell is 0.
            if (a[i] == 0) {
                lastValue = 0;
            }
            // otherwise, 
            else {
                // if we already "met" a 0 cell, let's replace the value of each cell with
                // minimum of distances between it and the next 0 - so we'll get the minimum distance from
                // closest cell.
                if (lastValue != -1) {
                    a[i] = Math.min(++lastValue, a[i]);
                }
                // we use the fact that at first, we set unknown distance before first 0
                // to max value of int, and now we check the minimum - which is almost every int,
                // and in our case, every reasonable int value.
            }
        }
    }

    /* Question 3 */

    /**
     * returns whether the string t is a transformation of the string s -
     * means every char of s is in t at least one time. by order in s.
     * @param s the original string, to be compared to the transformation.
     * @param t the transformatted string, to be compared to s - the original string.
     * @return whether t is a transformation of s (as defined above).
     */
    public static boolean isTrans (String s, String t) {
        // if s length is less than the transformatted, it's impossible for it to be valid transformation.
        if (s.length() > t.length()) return false;
        // if transformation is empty (but notice that it's length's always more than the s)
        // then it means we successfully found that t is a transformation, and both s and t are empty.
        if (t.length() == 0) return true;
        // if s empty, then it's impossible
        if (s.length() == 0) return false;
        return s.charAt(0) == t.charAt(0) && ( // first, check if two first chars are match.
                isTrans(s, t.substring(1)) || // then if first char of s and next char of t.
                isTrans(s.substring(1), t.substring(1))); // if not the prev, check for next char of both s and t.
    }

    /* Question 4 */

    /**
     * Returns how much paths from first cell of matrix to it's last cell
     * can be done, when moving by each cell digit value by rows and cols or cols and rows
     * @param mat the matrix to find valid paths from first cell to the last one by digits of each cell
     * @return the number of valid paths from first cell to last cell.
     */
    public static int countPaths (int [][] mat) {
        // start counting paths from [0][0], using overload (we don't want to duplicate code..)
        return countPaths(mat, 0, 0);
    }
    // Does the same, starting from specific cell in the matrix.
    private static int countPaths(int[][] mat, int rowIndex, int colIndex) {
        // if we're at low-right cell, it's a legal route - let's add 1 to the sum of paths.
        if (mat.length-1 == rowIndex && mat[0].length-1 == colIndex) return 1;

        // if cell value is 0, then this is the end of current path (because we can't "move"..)
        if (mat[rowIndex][colIndex] == 0) return 0;

        // find first & second digits:
        int firstDigit = mat[rowIndex][colIndex] / 10;
        int secondDigit = mat[rowIndex][colIndex] % 10;


        int totalRoutes = 0;
        // check if cell exists after moving rows by first digit and cols by second, and oppositely.
        // if exists, check paths from that point. // if not both digits are same (we don't like to count 2 times!)
        if (!(firstDigit == secondDigit) && mat.length > rowIndex+firstDigit && mat[rowIndex].length > colIndex+secondDigit) {
            totalRoutes += countPaths(mat, rowIndex+firstDigit, colIndex+secondDigit);
        }
        if (mat.length > rowIndex+secondDigit && mat[rowIndex].length > colIndex+firstDigit) {
            totalRoutes += countPaths(mat, rowIndex+secondDigit, colIndex+firstDigit);
        }

        // return sum of route when choosing to move first digit as row and second as col, and oppositely.
        return totalRoutes;
    }
}
