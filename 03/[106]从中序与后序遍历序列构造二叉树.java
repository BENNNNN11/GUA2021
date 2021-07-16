//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 532 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
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
    int[] inorder;
    int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // inorder: 左根右
        // postorder: 左右根
        this.inorder = inorder;
        this.postorder = postorder;
        TreeNode res =  dfs(0, postorder.length - 1, 0, inorder.length - 1);
        return res;
    }

    private TreeNode dfs(int l1, int r1, int l2, int r2) {
        // terminator
        if (l1 > r1) return null;

        // 创建根节点
        TreeNode root = new TreeNode(postorder[r1]);

        int mid = l2;
        while (inorder[mid] != postorder[r1]) mid++;
        int leftSize = mid - l2;
        int rightSize = r2 - mid;

        root.left = dfs(l1, l1 + leftSize - 1, l2, l2 + leftSize - 1);
        root.right = dfs(l1 + leftSize, r1 - 1, mid + 1, r2);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
