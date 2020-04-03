package 字符串_String;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 2020 4-3
 */
public class 字符串转换整数_StringToInteger_8_Medium {

    public static void main(String[] args) {
        String str = "  -2222233333999999999999999";
        str = "  +";
        Solution8 solution = new Solution8();
        int ans = solution.myAtoi(str);
        System.out.println(ans);
    }
}

class Solution8 {

    public int myAtoi(String str) {

        str = str.trim();
        if (str.length() == 0)
            return 0;

        if (!(str.charAt(0) == 43 || str.charAt(0) == 45 || (str.charAt(0) >= 48 && str.charAt(0) <= 57)))
            return 0;
        StringBuilder sb = new StringBuilder();

        boolean positive = true;

        if (str.charAt(0) == 45)
            positive = false;
        else if (str.charAt(0) >= 49 && str.charAt(0) <= 57)
            sb.append(str.charAt(0));

        int ind = 1;
        //number
        while (ind <= str.length()-1) {
            if (str.charAt(ind) >= 48 && str.charAt(ind) <= 57) {
                sb.append(str.charAt(ind++));
                if (positive && Long.valueOf(sb.toString()) > Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                if (!positive && (Long.valueOf(sb.toString())*-1 <  Integer.MIN_VALUE))
                    return Integer.MIN_VALUE;
            } else
                break;
        }

        if (sb.length() == 0)
            return 0;
        long num = Long.valueOf(sb.toString());
        if (positive) {
            if (num > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            else
                return Long.valueOf(num).intValue();
        } else {
            if (num*(-1) < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            else
                return -1*Long.valueOf(num).intValue();
        }

    }
}