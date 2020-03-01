
/**
 * Solutions for 2016a/87
 *
 * @author AvivNaaman
 * @version 2016a87/2020a
 */
public class _2016a87
{
    public static int minDiff(int[] arr) {
        return minDiff(arr, 0, 0, 0); // call overload, (int[] arr, int indx, int s1, int s2)
    }
    private static int minDiff(int[] arr, int indx, int s1, int s2){
        if (indx == arr.length) return Math.abs(s1-s2);
        return Math.min(minDiff(arr, indx+1, s1+arr[indx], s2), minDiff(arr, indx+1, s1, s2+arr[indx]));
    }
    public static int passingCars(int[] a){
        int westCount = 0, finalCount = 0;
        for (int i = a.length-1; i >= 0; i--){
            if (a[i] == 1) westCount++;
            else finalCount += westCount;
        }
        return finalCount;
    }
}
