
/**
 * Test solutions for 2016a/83
 *
 * @author AvivNaaman
 * @version 2016a83/2020a
 */
public class _2016a83
{
    public static int minPoints(int[][] m){
        return minPoints(m, m.length-1, m.length-1, 1);
    }
    private static int minPoints(int[][] m, int row, int col, int num){
        if (row < 0 || col < 0) return Integer.MAX_VALUE;
        num -= m[row][col];
        if (row == 0 && col == 0) return num;
        if (num <= 0) return Integer.MAX_VALUE;
        return Math.min(minPoints(m,row-1,col, num), minPoints(m,row,col-1,num));
    }
    
    public static boolean findX(int[] a, int x) {
        int min = 0, max = a.length-1, mid = -1;
        while (min < max) {
            mid = (min+max)/2;
            if (a[mid]+a[mid+1]==x) return true;
            else if (a[mid]+a[mid+1] > x) max = mid-1;
            min = mid+1;
        }
            return false;
    }
}
