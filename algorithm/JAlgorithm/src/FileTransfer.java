import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileTransfer {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int var1 = Integer.valueOf(sc.nextLine());


        Map<Integer, Integer> var2 = new HashMap<>();

        for (int i = 0; i < var1; i++) {
            String[] var3 = sc.nextLine().split(" ");

            int var4 = Integer.valueOf(var3[0]);
            int var5 = Integer.valueOf(var3[1]);
            int var6 = var2.getOrDefault(var3, 0);
            var2.put(var4, var6+var5);
        }

        int maxvar = 0;

        int[] var7 = new int[var2.size()];
        var7[0] = var2.getOrDefault(1, 0);
        var7[1] = var7[0] - 1 + var2.getOrDefault(2, 0);

        maxvar = Math.max(var7[0], var7[1]);


        for (int i = 2; i < var2.size(); i++) {

            var7[i] = var7[i-1] - 1 + var2.getOrDefault(i+1, 0);
            maxvar = Math.max(var7[i], maxvar);
        }


        int time = var7[var2.size()-1] + var2.size();

        System.out.println(time +" "+maxvar);


    }
}
