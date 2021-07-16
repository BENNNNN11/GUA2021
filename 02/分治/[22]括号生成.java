//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 1863 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<String> ans;
    private int n;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<String>();
        this.n = n;
        if (n == 0) return ans;
        dfs("", 0, 0);
        return ans;
    }
    public void dfs(String s, int left, int right) {
        if (left == n && right == n) {
            ans.add(s);
            return;
        }
        if (left < n) {
            dfs(s + "(", left + 1, right);
        }
        if (right < left) {
            dfs(s + ")", left, right + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
