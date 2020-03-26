import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int len = calories.length;
        if (len == 0)
            return 0;

        if (len == 1)
            return (calories[0] < lower) ? -1 : (calories[0] > upper) ? 1 : 0;


        int[] presum = new int[len];
        int sum = 0;

        for (int i = 0; i < len; i++) {
            sum += calories[i];
            presum[i] = sum;
        }

        int res = 0;

        int var1 = presum[k-1];
        res += (var1 < lower) ? -1 : ((var1 > upper) ? 1 : 0);

        for (int i = k; i <= len-k; i += k) {
            int var2 = presum[i+k-1] - presum[i-1];
            res += (var2 < lower) ? -1 : ((var2 > upper) ? 1 : 0);
        }
        return res;
    }


    public static boolean check(String var, int k) {

        int len = var.length();
        if (len <= 1)
            return true;

        char[] chars = var.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (char c : chars) {
            int var1 = map.getOrDefault(c, 0);
            map.put(c, var1+1);
        }

        int[] var2 = new int[]{0};

        map.entrySet().stream().forEach(r -> {
            if ((r.getValue() % 2) != 0)
                var2[0]++;
        });

        if (var2[0] % 2 != 0)
            return (var2[0]/2 -1) <= k;
        else
            return var2[0]/2 <= k;
    }

    public static void docheck(int[][] counts, int start, int end, int k, List<Boolean> result) {

        int modify = 0;
        if (start == 0) {
            for (int i = 0; i < 26; i++) {

                int nums = counts[end][i];
                if (nums % 2 != 0) {
                    modify ++;
                }
            }
        } else {
            for (int i = 0; i < 26; i++) {

                int nums = counts[end][i] - counts[start-1][i];

                if (nums % 2 != 0) {
                    modify ++;
                }

            }
        }

        if ((modify / 2 <= k) || modify == 0 || (modify == 1 && k == 0)) {
            result.add(true);
        } else {
            result.add(false);
        }

    }

    public static List<Boolean> canMakePaliQueries(String s, int[][] queries) {

        int lens = queries.length;
        List<Boolean> result = new ArrayList<>();

        if (s.length() <= 1) {
            result.add(true);
            return result;
        }


        int[][] dp = new int[s.length()][26];

        dp[0][s.charAt(0)-'a'] = 1;

        for (int i = 1; i < s.length(); i++) {
            dp[i][s.charAt(i)-'a']++;
            for (int j = 0; j < 26; j++)
                dp[i][j] += dp[i-1][j];
        }


        for (int[] var : queries) {

            int begin = var[0];
            int end = var[1];
            int k = var[2];

            if (k > end - begin + 1) {
                result.add(true);
                continue;
            }

            docheck(dp, begin, end, k, result);

        }
        return result;

    }


    public static void main(String[] args) {
//        int[] a = new int[]{6,5,0,0};
//        int k = 2;
//        int lower = 1;
//        int upper = 5;
//
//        int result = Solution.dietPlanPerformance(a, k, lower, upper);
//
//        System.out.println(result);
//
        String var = "pazcds";

        int[][] queries = {{1,5,1}};

        Solution.canMakePaliQueries(var, queries);


    }


}