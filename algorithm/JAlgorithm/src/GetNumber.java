import java.util.Scanner;

public class GetNumber {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int var1 = sc.nextInt();
        int result = 1;
        for (int i = 1; i <= var1; i++) {
            String var2 = String.valueOf(result);
            while (var2.indexOf(String.valueOf(4)) != -1) {
                result++;
                var2 = String.valueOf(result);
            }
            result++;
        }
        System.out.println(result-1);
    }
}
