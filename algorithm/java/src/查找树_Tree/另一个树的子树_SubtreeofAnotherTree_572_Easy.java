package 查找树_Tree;

public class 另一个树的子树_SubtreeofAnotherTree_572_Easy {
}


class Solution572 {
    private boolean isSameTree(TreeNode t1, TreeNode t2) {

        if (t1 != null) {
            if (t2 == null)
                return false;
            if (t1.val != t2.val)
                return false;
            return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
        } else
            return t2 == null;
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {

        if (null != s) {
            boolean top = isSameTree(s, t);
            boolean left = isSubtree(s.left, t);
            boolean right = isSubtree(s.right, t);
            return top || left || right;
        } else
            return false;

    }
}