//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1399 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private Map<Character, String> phone;
    private List<String> ans = new ArrayList<>();
    StringBuilder s = new StringBuilder(); // 用stringBuilder方便对字符串操作

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return ans;
        }

        phone = new HashMap<Character, String>();
        phone.put('2', "abc");
        phone.put('3', "def");
        phone.put('4', "ghi");
        phone.put('5', "jkl");
        phone.put('6', "mno");
        phone.put('7', "pqrs");
        phone.put('8', "tuv");
        phone.put('9', "wxyz");

        dfs(digits, 0);

        return ans;
    }
    private void dfs(String digits, int index) {
        if (index == digits.length()) {
            ans.add(s.toString());
            return;
        }
        String letters = phone.get(digits.charAt(index));
        for (int i = 0; i < letters.length(); i++) {
            s.append(letters.charAt(i));
            dfs(digits, index + 1);
            s.deleteCharAt(index);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
