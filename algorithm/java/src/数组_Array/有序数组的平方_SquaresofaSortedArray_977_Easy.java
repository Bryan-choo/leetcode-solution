package 数组_Array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 有序数组的平方_SquaresofaSortedArray_977_Easy {


    public static void main(String[] args) {
        Solution977 solution = new Solution977();
        int[] arr = new int[]{-3, -2, 0, 1, 3, 6};
//        arr = new int[]{1};
        int[] ans = solution.sortedSquares(arr);
        Arrays.stream(ans).forEach(System.out::println);
    }

}


class Solution977 {

    public int[] sortedSquares(int[] A) {
//        List<Integer> results = Arrays.stream(A).boxed().map(Math::abs).sorted().map(a -> a * a).collect(Collectors.toList());
//        int[] ans = results.stream().mapToInt(Integer::intValue).toArray();
//        return ans;
        int len = A.length;

        int p, q;
        p = 0;
        q = len-1;

//        int i = len-1;
        while (p <= q) {

            if (Math.abs(A[p]) <= Math.abs(A[q])) {
                A[q] *= A[q];
                q--;
            } else {
                int tmp = A[q];
                A[q--] = A[p] * A[p];
                A[p] = tmp;

            }

        }
        A[0] *= A[0];
        return A;

//        int p, q;
//        p = 0;
//        q = len-1;
//        int[] ans = new int[len];
//        int i = len-1;
//        while (p <= q) {
//            if (Math.abs(A[p]) <= Math.abs(A[q])) {
//                ans[i--] = A[q] * A[q];
//                q--;
//            } else {
//                ans[i--] = A[p] * A[p];
//                p++;
//            }
//        }
//
//        return ans;
    }

}
