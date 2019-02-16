# 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
#
# 示例 :
# 给定 a / b = 2.0, b / c = 3.0
# 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
# 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
#
# 输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。
#
# 基于上述例子，输入如下：
#
# equations(方程式) = [ ["a", "b"], ["b", "c"] ],
# values(方程式结果) = [2.0, 3.0],
# queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
# 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。

# ==========================================================================================================================
# 本题可以通过构造邻接矩阵求解(如下所示):
#         a       b       c
#   a     1       2       -
#   b     1/2     1       3
#   c     -       1/3     1
#   对于给定两个节点'a' 'c' 通过深度优先查找DFS方式可以找到两个节点之间的路径,边上的权重即为除法结果
#   通过HashMap 构造邻接矩阵,查询速度比普通的list方式快,查询的时间复杂度是线性的



class Solution(object):

    # def find(self, pred, p1):
    #     if p1 not in pred.keys():
    #         return p1
    #     else:
    #         parent = pred[p1]
    #         return self.find(pred, parent)
    #
    # def same(self, pred, p1, p2):
    #     return self.find(pred, p1) == self.find(pred, p2)

    # 深度优先搜索
    def dfs(self, adjMatrix, p1, p2, parent):
        if p1 == p2:
            return True, 1.0
        for i in adjMatrix[p1].keys():
            if i != p1 and i != parent:
                val = adjMatrix[p1][i]
                res, subval = self.dfs(adjMatrix, i, p2, p1)
                if res:
                    return True, val * subval
        return False, 0.0

    def calcEquation(self, equations, values, queries):
        """
        :type equations: List[List[str]]
        :type values: List[float]
        :type queries: List[List[str]]
        :rtype: List[float]
        """
        if len(equations) == 0:
            return [-1.0]

        #结果
        result = []


        #构造邻接矩阵
        adjMatrix = dict()
        for i in range(len(equations)):
            pair = equations[i]
            val = values[i]
            p1, p2 = pair[0], pair[1]
            #这里通过HashMap实现的查询要比list快很多
            if p1 not in adjMatrix.keys():
                adjMatrix[p1] = dict({p2: val})
            else:
                adjMatrix[p1][p2] = val
            if p2 not in adjMatrix.keys():
                adjMatrix[p2] = dict({p1: 1/val})
            else:
                adjMatrix[p2][p1] = 1/val

        # print(adjMatrix)
        for j in range(len(queries)):
            qpair = queries[j]
            q1, q2 = qpair[0], qpair[1]
            if q1 not in adjMatrix.keys() or q2 not in adjMatrix.keys():
                result.append(-1.0)
            else:
                if q1 == q2:
                    result.append(1.0)
                else:
                    res, val = self.dfs(adjMatrix, q1, q2, q1)
                    if res:
                        result.append(val)
                    else:
                        result.append(-1.0)
        return result

s = Solution()
equations = [["a", "b"], ["b", "c"], ["e", "f"]]
values = [2.0, 3.0, 4.0]
queries = [["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]]
equations = [["a","e"],["b","e"]]
values = [4.0,3.0]
queries = [["a","b"],["e","e"],["x","x"]]
s.calcEquation(equations, values, queries)
