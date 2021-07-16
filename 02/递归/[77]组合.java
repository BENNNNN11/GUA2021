//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 数组 回溯 
// 👍 614 👎 0


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> ans;
    private List<Integer> set;
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<List<Integer>>();
        set = new ArrayList<Integer>();
        this.n = n;
        this.k = k;
        findSubsets(1);
        return ans;
    }

    public void findSubsets(int index) {
        // 如果set的大小已经超过k， 或者把剩下的全选上也不够k个
        if (set.size() > k || set.size() + n - index + 1 < k) return;
        if (index == n + 1) {
            ans.add(new ArrayList<Integer>(set));
            return;
        }
        // 选
        set.add(index);
        findSubsets(index + 1);
        set.remove(set.size() - 1);
        // 不选
        findSubsets(index + 1);

    }

}
//leetcode submit region end(Prohibit modification and deletion)
