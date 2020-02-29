
/**
 * Solutions for test 2015b/94
 *
 * @author AvivNaaman
 * @version 2015b94/2020a
 */
public class _2015b94
{
    public static int longOrdNum(String s){
        return longOrdNum(s, 'x', 0, 0); //s,lastDigit
    }
    private static int longOrdNum(String s, char lastChar, int currSeq, int maxSeq) {
        if (s.length() == 0) return Math.max(currSeq, maxSeq);
        if (isDigit(s.charAt(0)) && currSeq < 1) currSeq = 1;
        if (isDigit(lastChar) && isDigit(s.charAt(0)) && s.charAt(0) > lastChar) 
            return longOrdNum(s.substring(1), s.charAt(0), currSeq+1, maxSeq);
        return longOrdNum(s.substring(1), s.charAt(0), 0, Math.max(currSeq, maxSeq));
    }
    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    
    public static int smallestSub(int[] a, int k) {
        int p1 = 0, p2 = 0, sum = a[0], minLength = a.length+1;
        while (p2 < a.length-1) {
            if (sum > k) {
                minLength = Math.min(minLength, p2-p1+1);
                sum -= a[p1];
                p1++;
            }
            else {
                sum += a[p2+1];
                p2++;
            }
        }
        if (sum > k) minLength = Math.min(minLength, p2-p1+1);
        return minLength;
    }
}
