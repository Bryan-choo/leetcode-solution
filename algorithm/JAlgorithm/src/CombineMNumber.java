import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CombineMNumber {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String var1 = sc.nextLine();
        String var2 = sc.nextLine();


        int[] arr1 = Arrays.stream(var1.split(",")).mapToInt(Integer::valueOf).sorted().toArray();
        int[] arr2 = Arrays.stream(var2.split(",")).mapToInt(Integer::valueOf).sorted().toArray();


        Set<Integer> ans = new HashSet<>();



        int i = 0;
        int j = 0;
        ans.add(arr1[i] + arr2[j]);

        while (ans.size() < 3) {

            if (i < arr1.length-1 && j < arr2.length -1) {

                int var3 = Math.min(arr1[i+1]+arr2[j], arr1[i]+arr2[j+1]);
                ans.add(var3);
                if (var3 == arr1[i+1]+arr2[j])
                    i+=1;
                else
                    j+=1;


            } else {


            }

        }
    }
}
