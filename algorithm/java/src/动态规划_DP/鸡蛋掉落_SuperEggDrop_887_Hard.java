package 动态规划_DP;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class 鸡蛋掉落_SuperEggDrop_887_Hard {


    public static void main(String[] args) {
        Solution887 solution = new Solution887();
        int ans = solution.superEggDrop(2, 9);
        System.out.println(ans);
    }
}


class Solution887 {


    private int dp(int K, int N, Map<Integer, Integer> map) {
        int key = N*100+K;
        if (map.containsKey(key))
            return map.get(key);
        else {
            if (N==0) {
                map.put(key, 0);
                return 0;
            }
            if (K == 1) {
                map.put(key, N);
                return N;
            }

            int start = 1;
            int end = N;
            System.out.println("start: "+start+" end: "+end);
            while (start + 1 < end) {
                int mid = (start + end) / 2;
                int top = dp(K, N-mid, map);
                int buttom = dp(K-1, mid-1, map);

                if (top > buttom) {
                    start = mid;
                } else if (top <buttom) {
                    end = mid;
                } else {
                    start = end = mid;
                    break;
                }
            }

            int ans = 1 + Math.min(Math.max(dp(K-1, start-1, map), dp(K, N-start, map)), Math.max(dp(K-1, end-1, map), dp(K, N-end, map)));

            map.put(key, ans);
            return ans;
        }
    }

    public int superEggDrop(int K, int N) {
        if (N <= 2)
            return N;
        Collection<Object> values = new HashMap<>().values();
        return dp(K, N, new HashMap<>());
    }

//    public int superEggDrop(int K, int N) {
//
//        if (N <= 2)
//            return N;
//        int [][] dp = new int[K+1][N+1];
//
//        for (int j = 1; j <= N; j++) {
//            dp[1][j] = j;
//        }
//        for (int i = 1; i <= K; i++) {
//            dp[i][1] = 1;
//            dp[i][2] = 2;
//        }
//
//        for (int i = 2; i <= K; i++) {
//            for (int j = 3; j <= N; j++) {
//                int top;
//                int buttom;
//                if (j % 2 == 0) {
//                    top = dp[i][j/2];
//                    buttom = dp[i-1][j/2-1];
//                } else {
//                    top = dp[i][j/2];
//                    buttom = dp[i-1][j/2];
//                }
//
//                dp[i][j] = 1 + Math.max(top, buttom);
//            }
//        }
//
//        return dp[K][N];
//    }
}