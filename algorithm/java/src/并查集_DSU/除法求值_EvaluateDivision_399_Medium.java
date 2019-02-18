package 并查集_DSU;
/*
 * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。

示例 :
给定 a / b = 2.0, b / c = 3.0
问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]

输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。

基于上述例子，输入如下：

equations(方程式) = [ ["a", "b"], ["b", "c"] ],
values(方程式结果) = [2.0, 3.0],
queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。

==========================================================================================================================
本题可以通过构造邻接矩阵求解(如下所示):
        a       b       c
  a     1       2       -
  b     1/2     1       3
  c     -       1/3     1
  对于给定两个节点'a' 'c' 通过深度优先查找DFS方式可以找到两个节点之间的路径,边上的权重即为除法结果
  通过HashMap 构造邻接矩阵,查询速度比普通的list方式快,查询的时间复杂度是线性的
 */

import java.util.HashMap;
/**
 * 
 * @author chuchengwei
 *
 */
public class 除法求值_EvaluateDivision_399_Medium {
	public static void main(String[] args) {
		String[][] equations = new String[][] { { "a", "e" }, { "b", "e" } };
		double[] values = new double[] { 4.0, 3.0 };
		String[][] queries = new String[][] { { "a", "b" }, { "e", "e" }, { "x", "x" } };
		Solution399 s = new Solution399();
		double[] result = s.calcEquation(equations, values, queries);
		for (double i:result)
			System.out.println(i);

	}
}

class Solution399 {
	private String find(HashMap<String, String> pred, String p1) {
		String p = p1;
		while (pred.containsKey(p)) {
			p = pred.get(p);
		}
		return p;
	}

	private boolean same(HashMap<String, String> pred, String p1, String p2) {
		return this.find(pred, p1).equals(this.find(pred, p2));
	}

	private void join(HashMap<String, String> pred, String p1, String p2) {
		pred.put(this.find(pred, p1), this.find(pred, p2));
	}

	private double dfs(HashMap<String, HashMap> adjMatrix, String p1, String p2, String pre) {
		if (p1.equals(p2))
			return 1.0;
		HashMap<String, Double> keys = adjMatrix.get(p1);
		for (String i : keys.keySet()) {
			if (!i.equals(pre) && !i.equals(p1)) {
				double val = keys.get(i);
				double subval = this.dfs(adjMatrix, i, p2, p1);
				if (subval != -1.0)
					return val * subval;
			}
		}
		return -1.0;
	}

	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		if (equations.length == 0)
			return new double[] { -1.0 };

//		结果
		double[] result = new double[queries.length];

//		构建并查集
		HashMap<String, String> pred = new HashMap();

//		构建邻接矩阵
		HashMap<String, HashMap> adjMatrix = new HashMap();

		for (int i = 0; i < equations.length; i++) {
			String[] pair = equations[i];
			String p1 = pair[0];
			String p2 = pair[1];
			double val = values[i];
			if (!this.same(pred, p1, p2))
				this.join(pred, p1, p2);
			if (!adjMatrix.containsKey(p1)) {
				HashMap<String, Double> vals = new HashMap<>();
				vals.put(p2, val);
				adjMatrix.put(p1, vals);
			} else {
				adjMatrix.get(p1).put(p2, val);
			}
			if (!adjMatrix.containsKey(p2)) {
				HashMap<String, Double> vals = new HashMap<>();
				vals.put(p1, (double) 1 / val);
				adjMatrix.put(p2, vals);
			} else {
				adjMatrix.get(p2).put(p1, (double) 1 / val);
			}
		}

//		求解
		for (int i = 0; i < queries.length; i++) {
			String[] qpair = queries[i];
			String q1 = qpair[0];
			String q2 = qpair[1];
			if (!adjMatrix.containsKey(q1) || !adjMatrix.containsKey(q2)) {
				result[i] = -1.0;
			} else {
				if (q1.equals(q2)) {
					result[i] = 1.0;
				} else {
//					通过并查集判断q1 q2是否在属于同一类
					if (!this.same(pred, q1, q2)) {
						result[i] = -1.0;
					} else {
						result[i] = this.dfs(adjMatrix, q1, q2, q1);
					}
				}
			}
		}
		return result;
	}
}
