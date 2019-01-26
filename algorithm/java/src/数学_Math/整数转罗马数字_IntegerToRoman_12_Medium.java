package 数学_Math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/*
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

示例 1:

输入: 3
输出: "III"
示例 2:

输入: 4
输出: "IV"
示例 3:

输入: 9
输出: "IX"
示例 4:

输入: 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
示例 5:

输入: 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */



/**
 * 
 * @author chuchengwei
 *
 */
public class 整数转罗马数字_IntegerToRoman_12_Medium {
	
	public static void main(String []args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		System.out.println(s.intToRoman(3090));

	}
	
}


class Solution {
	public static Map<Integer, String> roman_map = new HashMap<Integer, String>();
	public static Map<Integer, String> smap = new HashMap<Integer, String>();
	public static int[] keys = new int[] {1, 5, 10, 50, 100, 500, 1000};
	
	public List<Integer> split(int num) {
		char[] val = String.valueOf(num).toCharArray();
		List<Integer> result = new ArrayList<Integer>();
		int len = val.length;
		for(int i=0; i<val.length; i++) {
			int n = (int) (Integer.parseInt(String.valueOf(val[i]))*Math.pow(10, len-i-1));
			result.add(n);
		}
		return result;
	}
	public String convert(int num) {
		if(num == 0)
			return "";
		if(roman_map.containsKey(num))
			return roman_map.get(num);
		if(smap.containsKey(num))
			return smap.get(num);
		Set<Integer> keySet = roman_map.keySet();
		int downlimit = 0;
		for(int i:keys) {
			if(i<=num)
				downlimit = i;
			else
				break;
		}
		String base = roman_map.get(downlimit);
		String left = convert(num - downlimit);
		return base+left;
	}
	
    public String intToRoman(int num) {
    	roman_map.put(1, "I");
    	roman_map.put(5, "V");
    	roman_map.put(10, "X");
    	roman_map.put(50, "L");
    	roman_map.put(100, "C");
    	roman_map.put(500, "D");
    	roman_map.put(1000, "M");
    	
    	smap.put(4, "IV");
    	smap.put(9, "IX");
    	smap.put(40, "XL");
    	smap.put(90, "XC");
    	smap.put(400, "CD");
    	smap.put(900, "CM");
    	
    	List<Integer> vals = split(num);
    	StringBuilder sb = new StringBuilder();
    	for(int i:vals) {
    		String val = convert(i);
    		sb.append(val);
    	}
        return sb.toString();
    }
}