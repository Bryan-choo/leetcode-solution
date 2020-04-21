package 面试题;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class 整数一出现的次数_LCOF_43 {

    public static void main(String[] args) {
        Solution43 solution = new Solution43();
        int ans = solution.countDigitOne(1230123);
        System.out.println(ans);
    }
}


class Solution43 {

    public int countDigitOne(int n) {

        if (n < 10)
            return 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        int presum = 1;
        double base = 10;

        while (base <= Integer.MAX_VALUE) {
            int val = (int)base + 9*presum + presum;
            map.put((int)base, val);
            presum = val;
            base *= 10;
        }

        LinkedList<Integer> nums = new LinkedList<>();

        while (n != 0) {
            int num = n % 10;
            nums.offerLast(num);
            n = n / 10;
        }

        base = 1;
        int ans = 0;
        int sum = 0;
        int len = nums.size();
        while (nums.size() > 0) {
            int num = nums.pollFirst();
            int val = num*(int)base;
            if (val < 10) {
               if (val > 0)
                   ans += 1;
            }
            else {
                int tmpval = 0;
                if (val == base)
                    tmpval = map.get((int)base/10) + 1 + sum;
                else
                    tmpval = (int)base + num*(map.get((int)base/10));
                ans += tmpval;
            }
            sum += val;
            base *= 10;
        }

//        int num = nums.pollFirst();
//        if (num == 1) {
//            ans += (map.get((int)base/10) + 1 + sum);
//        } else {
//            ans += (int) base + num*(map.get((int)base/10));
//        }
        return ans;
    }
}