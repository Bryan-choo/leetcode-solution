package 动态规划_DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 统计重复个数_CountTheRepetitions_466_Hard {

    public static void main(String[] args) {

        Solution466 solution = new Solution466();
        StringBuilder sb1 = new StringBuilder("adcebacbacb");
        StringBuilder sb2 = new StringBuilder("acb");

//        System.out.println(solution.findk(sb1.toString(), sb2.toString(), 0));

//        int ans = solution.getMaxRepetitions("acb", 4, "ab", 2);
//        int ans = solution.getMaxRepetitions("aaa",3, "aa",1);
//        int ans = solution.getMaxRepetitions("baba", 11, "baab", 2);
        long start = System.currentTimeMillis();
        int ans = solution.getMaxRepetitions("phqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikefphqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikefphqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikefphqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikefphqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikefphqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikef", 1000000, "fmznimkkasvwsrenzkycxfxtlsgypsfad",333);
        long end = System.currentTimeMillis();

        System.out.println((end-start)/1000.0);
        System.out.println(ans);
    }
}


class Solution466 {

    public int findk(String sb1, String sb2, int start1) {

        if(start1 >= sb1.length() || (sb1.length() - start1 < sb2.length()))
            return 0;
        int j = 0;
        int i = start1;

        while (j < sb2.length() && i < sb1.length()) {
            if (sb1.charAt(i) == sb2.charAt(j)) j++;
            i++;
        }
        if (j == sb2.length())
            return 1+findk(sb1, sb2, i);
        else
            return 0;

    }

    public int findAnddeleteMatch(String s1, String s2, int startind, List list) {
        int len1 = s1.length();
        int len2 = s2.length();

        if(startind >= len1 || (len1 - startind < len2))
            return 0;
        int ans = 0;

        int i = 0;
        int j = 0;
        while (!(startind >= len1 || (len1 - startind < len2))) {
            i = startind;
            j = 0;
            while (i < len1 && j < len2) {
                if (s1.charAt(i) == s2.charAt(j)) j++;
                i++;
            }
            if (j == len2) {
                startind = i;
                ans++;
            } else {
//                sb.append(s1.substring(i, len1));
                list.set(0, s1.substring(startind, len1));
                return ans;
            }
        }
        list.set(0, s1.substring(startind, len1));
        return ans;

    }

    private boolean contains(StringBuilder sb, char s) {

        for (int i = 0; i<sb.length(); i++) {
            if (sb.charAt(i) == s)
                return true;
        }
        return false;
    }

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (s1 == "" || s2 == "" || n1 == 0)
            return 0;

//        HashSet<Character> set = new HashSet<>();
//
//        s1.chars().forEach(r->{set.add((char) r);});
//
//        char[] charss2 = s2.toCharArray();
//
//        for (char c:charss2) {
//            if (!set.contains(c))
//                return 0;
//        }
//        if (set.size() == 1) {
//            return (n1*s1.length()) / (n2*s2.length());
//        }

        String olds1 = s1;
        int mod = 0;


        //更新s1
//        long start = System.currentTimeMillis();
        if (s1.length() < s2.length() || findk(s1, s2, 0) == 0) {
            int counts = 1;
            StringBuilder ss1 = new StringBuilder(s1);
            while (findk(ss1.toString(), s2, 0) == 0 && counts < n1) {
                counts++;
                ss1 = ss1.append(s1);
            }
            mod = n1 % counts;
            n1 /= counts;
            s1 = ss1.toString();
        }

//        long end = System.currentTimeMillis();
//        System.out.println("更新s1 耗时:"+(end-start)/1000.0);
        if (findk(s1, s2, 0) == 0)
            return 0;

        String modstr = "";
        while (mod != 0) {
            modstr += olds1;
            mod--;
        }

        int k = 0;
        //计算周期
        int time = 0;

//        start = System.currentTimeMillis();
        StringBuilder strbuilder = new StringBuilder("");
        List<String> list = new ArrayList<>();
        list.add(s1);
        while (time < n1) {
            strbuilder.append(s1);
            k += findAnddeleteMatch(strbuilder.toString(), s2, 0, list);
            strbuilder = new StringBuilder(list.get(0));
            time++;
            if(strbuilder.length() == 0|| !contains(strbuilder, s2.charAt(0)))
                break;
        }
        String str = strbuilder.toString();

//        end = System.currentTimeMillis();
//        System.out.println("计算周期耗时: "+(end-start)/1000.0);
        mod = n1 % time;
        str += modstr;

        //加上剩余字符串
        while (mod != 0) {
            str += s1;
            mod--;
        }
        int modk = findk(str, s2, 0);

        int ans = (n1/time)*k + modk;
        return ans / n2;
    }
}
