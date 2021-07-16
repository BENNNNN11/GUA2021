//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯 
// 👍 1233 👎 0


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private ArrayList<List<Integer>> ans;
    private ArrayList<Integer> set;
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<List<Integer>>();
        set = new ArrayList<Integer>();
        findSubsets(nums, 0);
        return ans;
    }

    public void findSubsets(int[] nums, int level) {
        if (nums.length == level) {
            ans.add(new ArrayList<Integer>(set));
            return;
        }
        // 选当前层数字的话, set添加然后继续向下drill down，然后恢复状态
        set.add(nums[level]);
        findSubsets(nums, level + 1);
        set.remove(set.size() - 1);
        // 不选当前层数字的话
        findSubsets(nums, level + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
