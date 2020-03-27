package 数组_Array;

import java.util.Collection;
import java.util.HashMap;

public class 卡牌分组_XofaKindinaDeckofCards_914_Easy {

    public static void main(String[] args) {
        Solution914 solution = new Solution914();
        int[] vars = new int[]{1,2,3,4,4,3,2,1};
        vars = new int[] {1,1,2,2,2,2};
        vars = new int[] {1,1,1,2,2,2,3,3};
        boolean result = solution.hasGroupsSizeX(vars);
        System.out.println(result);
    }

}


class Solution914 {

    //计算最大公约数 辗转相除
    private int gcd(int a, int b) {
        return b == 0 ? a : (gcd(b, a % b));
    }

    public boolean hasGroupsSizeX(int[] deck) {
        int len = deck.length;

        if (len <= 1)
            return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        int maxvar = 0;


        for (int i:deck) {
            int counts = map.getOrDefault(i, 0)+1;
            maxvar = (counts > maxvar) ? counts : maxvar;
            map.put(i, counts);
        }

        Collection<Integer> values = map.values();

        int gcd = 0;
        for (int i : values) {
            int m = gcd(i, gcd);
            if (m == 1)
                return false;
            gcd = m;
        }
        return gcd >= 2;

//        boolean ans = false;
//        for (int i = 2; i <= maxvar && !ans; i++) {
//            ans = true;
//            for (int j : values) {
//                if (j % i != 0) {
//                    ans = false;
//                    break;
//                }
//            }
//
//        }
//
//        return ans;
    }
}