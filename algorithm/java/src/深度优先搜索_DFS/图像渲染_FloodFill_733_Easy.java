package 深度优先搜索_DFS;

import java.util.LinkedList;
/*
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。

为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

最后返回经过上色渲染后的图像。

示例 1:

输入:
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
输出: [[2,2,2],[2,2,0],[2,0,1]]
解析:
在图像的正中间，(坐标(sr,sc)=(1,1)),
在路径上所有符合条件的像素点的颜色都被更改成2。
注意，右下角的像素没有更改为2，
因为它不是在上下左右四个方向上与初始点相连的像素点。
注意:

image 和 image[0] 的长度在范围 [1, 50] 内。
给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
============================================================================
  本题是经典的FloodFill问题(https://zh.wikipedia.org/wiki/Flood_fill)
  既可以通过深度优先搜索DFS(本题通过递归方式实现),也可以通过广度优先搜索BFS(通过队列的方式实现)求解
      方式一DFS:
      DFS求解思路很简单，在给定一个初始点后，首先将该点颜色转换为给定颜色,然后依次递归地对左、上、右、下节点调用dfs方法,
      边界条件为: 旁边有节点且该节点颜色和初始颜色不同,以左边为例: sc - 1 >= 0 and image[sr][sc-1] == tcolor

      方式二BFS:
      BFS求解需要通过队列实现, 首先将初始节点入队列, 接着循环出队列操作,更改颜色,同时将满足条件的左、上、右、下相邻节点入队列,
      循环结束条件即为 队列为空
 */

/**
 * 
 * @author chuchengwei
 *
 */
public class 图像渲染_FloodFill_733_Easy {
	public static void main(String[] args) {
		Solution733 s = new Solution733();
		int[][] image = new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		s.floodFill(image, 1, 1, 2);
	}
}


class Solution733 {
    
    private void dfs(int[][] image, int sr, int sc, int newColor) {
        int tcolor = image[sr][sc];
        if (tcolor == newColor)
            return;
        image[sr][sc] = newColor;
        if (sr - 1 >= 0 && image[sr-1][sc] == tcolor)
            dfs(image, sr-1, sc, newColor);
        if (sc - 1 >= 0 && image[sr][sc-1] == tcolor)
            dfs(image, sr, sc-1, newColor);
        if (sr + 1 < image.length && image[sr+1][sc] == tcolor)
            dfs(image, sr+1, sc, newColor);
        if (sc + 1 < image[0].length && image[sr][sc+1] == tcolor)
            dfs(image, sr, sc+1, newColor);
    }
    
    private void bfs(int[][] image, int sr, int sc, int newColor) {
        int tcolor = image[sr][sc];
        if (tcolor == newColor)
            return;
        LinkedList<Integer[]> queue = new LinkedList<Integer[]>();
        queue.offerLast(new Integer[] {sr, sc});
        while (queue.size() != 0) {
            Integer[] val = queue.pollFirst();
            int m = val[0];
            int n = val[1];
            image[m][n] = newColor;
            if (m - 1 >= 0 && image[m-1][n] == tcolor)
                queue.offerLast(new Integer[] {m-1, n});
            if (n - 1 >= 0 && image[m][n-1] == tcolor)
                queue.offerLast(new Integer[] {m, n-1});
            if (m + 1 < image.length && image[m+1][n] == tcolor)
                queue.offerLast(new Integer[] {m+1, n});
            if (n + 1 < image[0].length && image[m][n+1] == tcolor)
                queue.offerLast(new Integer[] {m, n+1});
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // dfs(image, sr, sc, newColor);
        bfs(image, sr, sc, newColor);
        return image;
    }
}