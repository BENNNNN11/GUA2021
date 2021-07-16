//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 
// 👍 1427 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> ans;
    private List<Integer> per;
    private boolean[] used;
    private int n;

    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<List<Integer>>();
        per = new ArrayList<Integer>();
        n = nums.length;
        used = new boolean[n];
        find(nums, 0);
        return ans;
    }
    // 考虑0, 1 ...n-1上放什么数
    // 从没用过的数中选一个放在该位置
    public void find(int[] nums, int index) {
        if (index == n) {
            ans.add(new ArrayList<>(per));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            used[i] = true;
            per.add(nums[i]);
            find(nums, index + 1);
            used[i] = false;
            per.remove(per.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
