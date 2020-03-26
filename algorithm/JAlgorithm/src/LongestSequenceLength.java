import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LongestSequenceLength {


    public static int getLen(int[] vars) {

        int len = vars.length;
        if (len <= 1)
            return len;

        int[] presum = new int[len];
        int[] masks= new int[len];
//        masks[0] = 1;

        presum[0] = vars[0];

        for (int i = 1; i < len; i++) {
            presum[i] = vars[i] + presum[i-1];
            if (vars[i] >= presum[i-1])
                masks[i] = 1;
        }

        int maxlen = 0;

        int j = 0;

        while (j < len) {

            if (masks[j] == 1) {
                int l = 1;
                while (j+l < len && masks[j+l] == 1)
                    l++;

                maxlen = Math.max(maxlen, l);
                j = j+l;

            } else
                j++;
        }

        return maxlen == 0 ? 0 : maxlen+1;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lines = Integer.valueOf(sc.nextLine());

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < lines; i++) {

            int nums = Integer.valueOf(sc.nextLine());

            int [] vars = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf)
                    .toArray();

            int result = getLen(vars);
            ans.add(result);

        }

        ans.stream().forEach(System.out::println);
    }
}
