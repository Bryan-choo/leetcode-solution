import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//...
public class MinSumNumbers {


    public static void getMin(List<Integer> arr1, List<Integer> arr2, List<Integer>result, List<Integer> vars) {

         if (vars.size() == 0) {
//             result.add(Math.abs(arr1.stream().mapToInt(Integer::valueOf).sum() - arr2.stream().mapToInt(Integer::valueOf).sum()));
             return;
         }

         for (int i = 0; i < vars.size(); i++) {

         }


    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int n = Integer.valueOf(sc.nextLine());


        int[] var1 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();



    }
}
