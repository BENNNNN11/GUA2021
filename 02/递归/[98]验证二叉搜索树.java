//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 1118 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 左子树的最大数比根节点小
    // 右子树的最小节点比根节点大
    // 并且左右子树都是二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return check(root).isValid;
    }
    class Info {
        public boolean isValid;
        public long minVal;
        public long maxVal;
    }
    public Info check(TreeNode root) {
        // terminator
        if (root == null) {
            Info info = new Info();
            info.isValid = true;
            info.minVal = Integer.MAX_VALUE + 1L;
            info.maxVal = Integer.MIN_VALUE - 1L;
            return info;
        }
        // drill down
        Info left = check(root.left);
        Info right = check(root.right);
        // process current level
        Info result = new Info();
        result.isValid = left.isValid && right.isValid
                && left.maxVal < root.val && right.minVal > root.val;
        result.minVal = Math.min(root.val, Math.min(left.minVal, right.minVal));
        result.maxVal = Math.max(root.val, Math.max(left.maxVal, right.maxVal));
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
