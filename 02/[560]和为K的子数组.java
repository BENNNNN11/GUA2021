//给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。 
//
// 示例 1 : 
//
// 
//输入:nums = [1,1,1], k = 2
//输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
// 
//
// 说明 : 
//
// 
// 数组的长度为 [1, 20,000]。 
// 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。 
// 
// Related Topics 数组 哈希表 
// 👍 963 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        /*
            根据题意，条件可转化为pre[i] - pre[j-1] = k, 即pre[j-1] = pre[i] - k
         */
        int count = 0, pre = 0; // pre记录前缀和
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1); // 考虑 pre[i] == k 的这种情况
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
// BruteForce:
//    public int subarraySum(int[] nums, int k) {
//        int ans = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for (int j = i; j >= 0; j--) {
//                sum += nums[j];
//                if (sum == k) {
//                    ans++;
//                }
//            }
//        }
//        return ans;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

