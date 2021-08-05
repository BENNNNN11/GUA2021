//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。 
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
// Related Topics 字符串 动态规划 
// 👍 631 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        text1 = " " + text1;
        text2 = " " + text2;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/*
            a       ac      ace
    a      1(a)    1(a)     1(a)
    ab     1(a)    1(a)     1(a)
    abc    1(a)    2(ac)    2(ac)
    abcd   1(a)    2(ac)    2(ac)
    abcde  1(a)    2(ac)    3(ace)
 */

//    f[i, j] 表示text1的前i个字符，text2的前j个字符的LCS的长度
//    如果text1[i] == text2[j], 那么f[i, j] = f[i - 1, j - 1] + 1
//    else, f[i, j] = max(f[i - 1, j], f[i, j - 1])

//边界处理技巧
//1. f[0][0] = 0, 然后用if语句判断, 目标f[n - 1][m - 1]
//2. 认为字符串从下标1开始，f[i][0]和f[0][j]均作为边界处理，目标f[n][m]