## 哈希表

- 组成： 
  - 一个数据结构，如链表，数组
  - hash函数，输入key，返回数据结构的索引
- **对外表现： Hash_table[key] = value** 
- **内部： 在数据结构的hash(key)位置处存储了value：data_structure[hash(key)] = value** 
  - 简单例子： key是整数：hash[key] = key，类似于one-hot
- **复杂信息： 设计hash函数把复杂信息映射到一个较小的值域内，作为索引**
- **哈希碰撞： hash collision**
  - **两个不同的key算出相同的hash结果**
  - **开散列： 数组的每个位置存储一个链表的头针指（表头数组）**
- 应用： 
  - 电话号码簿， 用户信息表， 缓存（LRU Cache），键值对存储（redis）
- 时间复杂度： 期望O(1)， 最坏O(n)



## 例题

1. [1] 两数之和

   ```java
   class Solution {
       public int[] twoSum(int[] nums, int target) {
           Map<Integer, Integer> exists = new HashMap();
           for (int i = 0; i < nums.length; i++) {
               if (exists.containsKey(target - nums[i])) {
                   return new int[]{i, exists.get(target - nums[i])};
               }
               exists.put(nums[i], i);
           }
           return new int[0];
       }
   }
   ```

2. [874] 模拟行走机器人

   ```
   
   ```

   

## 递归基本形式总结

- 递归实现的暴力搜索
  - 指数型： 子集，大体积背包 O(k^n)
  - 排列型： 全排列，n皇后，旅行商 O(n!)
  - 组合型： 组合选数 O(n! / m!(n - m)!)

- 反转二叉树， 验证二叉搜索树
- 



