package 字符串_String;

/**
 * 2020 3-26
 */

public class 最后一个单词长度_LengthofLastWord_58_Easy {


    public static void main(String[] args) {
        Solution58 solution = new Solution58();

        String var = "aaa bb  ";
        System.out.println(solution.lengthOfLastWord(var));
    }


}

class Solution58 {

    public int lengthOfLastWord(String s) {
        if (null == s | "" == s)
            return 0;

        String newstr = s.trim();

        int len = newstr.length();
        int ans = 0;

        for (int i = len-1; i >=0 && newstr.charAt(i) != ' '; i--) {
            ans++;
        }
        return ans;
    }
}
