import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetMinNum {


    public static int getNum(int target) {

        int i = target;
        while (true) {
           int sumvar = String.valueOf(i).chars().map(r -> {
                return r - 48;
            }).sum();
           if (sumvar == target)
               return i;
           i++;
        }

    }


    public static String getNum1(int target) {

        if (target < 10)
            return String.valueOf(target);

        List<Integer> ans = new ArrayList<>();

        while (target > 0) {
            if (target >= 10) {
                ans.add(9);
                target -= 9;
            } else {
                ans.add(target);
                break;
            }
        }
        StringBuilder sb = new StringBuilder();

        ans.stream().forEach(r -> {
            sb.append(r);
        });

        return sb.reverse().toString();

    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int nums = Integer.valueOf(sc.nextLine());

        List<String> ans = new ArrayList<>();

        for (int i = 0; i < nums; i++) {

            int var = Integer.valueOf(sc.nextLine());
            ans.add(getNum1(var));

        }

        ans.stream().forEach(System.out::println);
    }
}
