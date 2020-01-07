import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Tester to the class Ex14 of mmn 14
 * @version 2020a.1
 * @author AvivN
 */
public class Test14 {
    /**
     * Empty CTOR
     */
    public Test14() {}

    /**
     * Test Question 1A - subStrC(String s, char c)
     */
    @Test
    public void testQ1A() {
        System.out.println("Testing Question 1, A - int subStrC(String s, char c)");
        assertEquals(3, Ex14.subStrC("ababcbababa", 'a'));
        assertEquals(5, Ex14.subStrC("aaaaaaa", 'a'));
        assertEquals(2, Ex14.subStrC("abcdefaaba", 'a'));
        assertEquals(0, Ex14.subStrC("", 'a'));
        assertEquals(0, Ex14.subStrC("a", 'a'));
        assertEquals(0, Ex14.subStrC("aa", 'a'));
        assertEquals(0, Ex14.subStrC("aaaaaaaaaaaaaaaaaa", 'b'));

        successMessage("1A");
    }
    /**
     * Test Question 1A - subStrC(String s, char c, int k)
     */
    @Test
    public void testQ1B() {
        System.out.println("Testing Question 2, B - int subStrMaxC(String c, char c, int k)");
        assertEquals(6, Ex14.subStrMaxC("bbbba", 'b', 3));
        assertEquals(6, Ex14.subStrMaxC("bbbba", 'b', 2));
        assertEquals(5, Ex14.subStrMaxC("bbbba", 'b', 1));
        //// NOTICE RUNTIME HERE
        System.out.println(":::NOTICE::: Tester in line 28 of Q1A - this should take less than second! If takes too long, think how to improve runtime complexity!");
        assertEquals(0, Ex14.subStrMaxC("bbbba", 'a', Integer.MAX_VALUE));

        assertEquals(10, Ex14.subStrMaxC("bbcabbab", 'b', 3));
        assertEquals(9, Ex14.subStrMaxC("bbcabbab", 'b', 2));
        assertEquals(7, Ex14.subStrMaxC("bbcabbab", 'b', 1));
        for (int t = 0; t < 500; t++)
            assertEquals(1, Ex14.subStrMaxC("bbcabbab", 'a', t));

        assertEquals(11, Ex14.subStrMaxC("aacbbbacacbbcabbcaac", 'a', 1));
        assertEquals(15, Ex14.subStrMaxC("aacbbbacacbbcabbcaac", 'a', 2));
        assertEquals(18, Ex14.subStrMaxC("aacbbbacacbbcabbcaac", 'a', 3));
        assertEquals(20, Ex14.subStrMaxC("aacbbbacacbbcabbcaac", 'a', 4));
        assertEquals(21, Ex14.subStrMaxC("aacbbbacacbbcabbcaac", 'a', 5));
        assertEquals(21, Ex14.subStrMaxC("aacbbbacacbbcabbcaac", 'a', 6));
        assertEquals(21, Ex14.subStrMaxC("aacbbbacacbbcabbcaac", 'a', 10));

        // edge cases
        assertEquals(0, Ex14.subStrMaxC("", 'b', Integer.MAX_VALUE));
        assertEquals(0, Ex14.subStrMaxC("", 'b', 7));
        assertEquals(0, Ex14.subStrMaxC("aaaaaaaaaaaagfsdhfgsaaaaaaa", 'b', Integer.MAX_VALUE));
        assertEquals(0, Ex14.subStrMaxC("aaaaaaaaaaaashfhsfgdjgffdgaaaaaaa", 'b', 10));

        successMessage("1B");
    }

    /**
     * Test Question 2 - Ex14.zeroDistance(int[] arr)
     */
    @Test
    public void testQ2() {
        int[][] beforeArrs = {{1,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1},
                {1,1,1,0,1,1,1,0,0,1,1,1,0,0,0,0,1,1,1,1,1,0,1,1,0},{0},
                {0,1,1,1,1,1,1,1,0},{0,0,0,0,0,1,0,0,0},{0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,0,1}};
        int[][] afterArrs =  {{2,1,0,1,2,3,4,5,5,4,3,2,1,0,0,0,0,1,2},
                {9,8,7,6,5,4,3,2,1,0,1,2,3,4,5,6,7,8,9},
                {1,0,0,0,1,2,1,0,1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1},
                {3,2,1,0,1,2,1,0,0,1,2,1,0,0,0,0,1,2,3,2,1,0,1,1,0},{0},
                {0,1,2,3,4,3,2,1,0},{0,0,0,0,0,1,0,0,0},{0,0,0,0,0,0,0,0},
                {7,6,5,4,3,2,1,0,1}};
        // test for each pair
        for (int i = 0; i < Math.min(beforeArrs.length, afterArrs.length); i++) {
            Ex14.zeroDistance(beforeArrs[i]);
            System.out.print("Checking arrays in index "+i+"... ");
            assertArrayEquals(afterArrs[i], beforeArrs[i]);
            System.out.println("Success. ");
        }
        successMessage("2");
    }

    /**
     * Test Question 3 Ex14.isTrans(String s, String t)
     */
    @Test
    public void testQ3() {
        final String original0 = "avivtest";
        final String[] trasformed0 = {"avivtest", "aaaavivtest", "aavviivvtteesstt", "aaaaavivttttttest",
                "avvvvivvvvvteeeeeestttttt", "avivvtttteeeeeeeessst", "avivtestttt", "aavivtest", "avivvvvtttteeesst",
                "aviiiiivtestt", "aaaaavvvvvvvviiiiiiiiiiivvvvvvvvvvvvvvvvvtttttttttteeeeeeeeeeeesssssssssstttttttttt"};
        final String[] notTransformed0 = {"avivtest0", "0avivtest", "a", "v", "s", "t", "aviv", "test", "b", "avivteset",
                                            "avivtsetttt", "tsetviva", "aaaaaaaaaaavvvvvvvvvvviiiiiiiiivvvvvvvvvvtttttttttteeeeeeeeeeseeeeeettttttt",
                                            "totallydifferent", "avivtestwithend", "withstartavivtest", "avvitest", "avitest",
                                            "aivtest", "avivte", "avivtes", "vivte", "vivtest"};
        /* You Can Add More Tests here. */
        testIsTransformedByArrays(original0, trasformed0, notTransformed0);
    }

    /**
     * Tests IsTransformed by original string, transformed strings array and not transformed strings array
     * and prints each string and whether succeeded or not
     * @param original The original string to check
     * @param transformed array of transformed strings
     * @param notTransformed array of not transformed strings
     */
    private void testIsTransformedByArrays(String original, String[] transformed, String[] notTransformed) {
        for (int i = 0; i < transformed.length; i++) {
            System.out.print("Checking whether " + transformed[i] + " is a transformation of "+original+"... ");
            assertTrue(Ex14.isTrans(original, transformed[i]));
            System.out.println("Success");
        }
        for (int i = 0; i < notTransformed.length; i++) {
            System.out.print("Checking whether " + notTransformed[i] + " isn't a transformation of "+original+"... ");
            assertFalse(Ex14.isTrans(original, notTransformed[i]));
            System.out.println("Success");
        }
    }

    /**
     * Test question 4 - Ex14.countPaths(int[][] array)
     */
    @Test
    public void testQ4() {
        int[][] arr0 = {
                {1 ,10,32,52,7 },
                {13,12,43,9 ,5 },
                {10,12,35,21,76},
                {5 ,4 ,34,21,10},
                {0 ,30,56,22,1 }
        };
        final int arr0_result = 4;
        assertEquals(arr0_result, Ex14.countPaths(arr0));
        int[][] arr1 = {
                {12,11,46,14,10,48,54,59,89,53},
                {0 ,49,21,33,27,89,34,63,57,41},
                {89,20,30,23,2 ,18,12,84,28,24},
                {29,18,84,48,96,75,26,31,10,74},
                {98,31,55,72,14,68,75,34,51,82},
                {14,50,45,90,98,43,17,37,1 ,4 },
                {24,54,5 ,9 ,72,72,86,96,11,46},
                {2 ,0 ,13,40,75,15,7 ,99,89,2 },
                {35,77,28,90,39,10,1 ,11,11,20},
                {37,54,70,19,52,90,82,20,31,57},
        };
        final int arr1_result = 4;
        assertEquals(arr1_result, Ex14.countPaths(arr1));

        int[][] arr2 = {{11,1,3},{5,0,8},{1,5,8}};
        // NOTICE RUNTIME HERE:
        System.out.println("NOTICE! This method might cause stack overflow due to infinite recursion (if failed, think WHY? [1][1])");
        assertEquals(0, Ex14.countPaths(arr2));

        successMessageRecursion("4");
		afterAll();
    }

    /**
     * prints nice success message by question number
     * @param questionId The question id/name
     */
    private static void successMessage(String questionId) {System.out.println("Q" + questionId +" Success. Don't forget API, with Description, Complexity, @param and @return\n");}

    /**
     * prints nice success message by question number
     * @param questionId The question id/name
     */
    private static void successMessageRecursion(String questionId) {System.out.println("Q"+questionId+" Success. Don't forget API with Description, @param and @return.");}

    private static void afterAll() { System.out.println("Please Feedback and report any error or possible improvement!");}
}
