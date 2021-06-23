## 主线

- 线性结构
  - 数组，链表，栈，队列
  - 前缀和，差分，双指针，滑动窗口
  - 哈希，集合，映射
  - 字符串
- 树形结构
  - 递归，分治
  - 树和图
  - 搜索
  - 堆，二叉搜索树
  - 字典树，并查集
  - 图论算法
- 状态空间
  - 贪心
  - 动态规划

## 副本

- 顺序优化副本
  - 排序
  - 二分
- 高级数据结构副本
  - 平衡树
  - 跳表
  - 树状数组
  - 线段树

## 时间复杂度

- O(n!) > O(2^n) > O(n^2) > O(nlogn) > O(n) > O(logn) > O(1)
- 1 + 2 + ... + n = n*(n-1)/2 = O(n^2)
- n + n/2 + n/4 + ... + 1 < 2n = O(n)
- 1 + 1/2 + 1/4 + ... + 1/n = O(logn) 调和级数

## 空间复杂度

- 静态数组的长度
- 递归的深度（栈上消耗的空间）
- 动态new的空间（堆上消耗的空间）

## 数组

- 索引/查询 lookup：O(1)
  - 内存开辟连续空间
- 插入insert，删除delete： O(n)
- 变长数组resizable array：C++ vector, java ArrayList, python list 
  - 如何实现： 
    - 支持索引和随机访问
    - 分配多长的连续空间
    - 空间不够了怎么操作
    - 空间剩余了如何回收
  - 简易实现方法： 
    - 初始：空数组，分配常数空间
    - push back：  若空间不够，重新申请2倍大小的连续空间，拷贝内容到新空间中，释放旧空间
    - pop back： 若空间利用率不到25%，释放一半的空间
    - 均摊O(1)
    - 在空数组中连续插入n个元素，总插入/拷贝的次数为 n + n/2 + n/4 +... < 2n 
    - 一次扩容到下一次释放，至少需要删除（1-2*0.25）n = 0.5n次

##  链表

- 单链表：
  - 访问：O(n)
  - 插入，删除： O(1)
- 双链表

## 栈，队列，双端队列，优先队列

- c++: stack, queue, deque, priority_queue
- Java: 
  - Stack, 
  - Queue, Deque 可以用linkedlist实现
  - PriorityQueue
- python: list，heapq



## 前缀和

- 数组A的前i个数相加 S[i] = S[i - 1] +A[i]
- 子段和：A中第l到第r个数的和 sum(l, r) = S[r] - S[l - 1]

- 1248-优美子数组

  ```python
  class Solution:
      def numberOfSubarrays(self, nums: List[int], k: int) -> int:
          """
              数组中每个元素对2取余，偶数变成0，奇数变成1
              判断奇数的个数就变成了判断子段的和是否为k
              固定外层循环变量，考虑内层满足什么条件
              对于每个r(1~n), 考虑有几个l(1~l), 使得s[r]-s[l - 1]=k
              对于每个i(1~n), 考虑有几个j(0~i-1), 使得s[i]-s[j]=k
              对于每个i(1~n), 考虑有几个j(0~i-1), 使得s[j]=s[i]-k
          """
          # 前面补一个0，使下标变成1～n
          nums = [0] + nums
          # 求前缀和数组
          s = [0] * len(nums) 
          for i in range(1, len(nums)):
              s[i] = s[i - 1] + nums[i] % 2
          # 开一个count数组，用来统计s中的值的频次
          count = [0] * len(s)
          for i in range(len(s)):
              count[s[i]] += 1
          # 对于每个i，有几个s[j]等于s[i]-k
          ans = 0
          for i in range(1, len(s)):
              if s[i] - k >= 0:
                  ans += count[s[i] - k]
          return ans
  ```

- 二维前缀和： 

  - 前缀和数组: 

    ```python
    S[i][j] = S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1] + A[i][j]
    ```

  - 子矩阵和： 以（p， q）为左上角，（i， j）右下角的A的子矩阵的和

    ```python
    sum(p, q, i, j) = S[i][j] - S[i][q-1] - S[p-1][j] + S[p-1][q-1]
    ```

  - Time Compleity: O(n^2) Initialization| O(1) lookup

  -  304-二维区域和检索 - 矩阵不可变

    ```python
    class NumMatrix:
    
        def __init__(self, matrix: List[List[int]]):
            # 初始化二维前缀和数组
            self.sum = [[0] * len(matrix[0]) for i in range(len(matrix))]
            for i in range(len(matrix)):
                for j in range(len(matrix[i])):
                    self.sum[i][j] = self.getSum(i - 1, j) + self.getSum(i, j - 1) - self.getSum(i - 1, j - 1) + matrix[i][j]
    
        def sumRegion(self, row1: int, col1: int, row2: int, col2: int) -> int:
            return self.getSum(row2, col2) - self.getSum(row1 - 1, col2) - self.getSum(row2, col1 - 1) + self.getSum(row1 - 1, col1 - 1)
    
        def getSum(self, i, j):
            return self.sum[i][j] if i >= 0 and j >= 0 else 0 
    
    
    # Your NumMatrix object will be instantiated and called as such:
    # obj = NumMatrix(matrix)
    # param_1 = obj.sumRegion(row1,col1,row2,col2)
    ```

- 差分

  - 前缀和的互逆运算，差分数组的前缀和数组就是原数组

  -  把A的第l个数到第r个数加d, 差分数组B的变化为：B[l] + d, B[r+1] - d

  - 1109-航班预订

    ```python
    class Solution:
        def corpFlightBookings(self, bookings: List[List[int]], n: int) -> List[int]:
            diff = [0] * (n + 2)
            for booking in bookings:
                first = booking[0]
                last = booking[1]
                seats = booking[2]
                # 差分计算公式
                diff[first] += seats
                diff[last + 1] -= seats
            # 0 ~ n
            a = [0] * (n + 1)
            # 1 ~ n
            for i in range(1, n + 1):
                a[i] = a[i - 1] + diff[i]
            # 0 ~ n - 1
            return a[1:]
    ```

- 最大子序和

  ```c++
  class Solution {
  public:
      // 前缀和做法： 求出前缀和数组s
      // 枚举右端点i，需要找到i之前的一个j，使得s[i] - s[j]最大
      // 也就是让s[j]最小，再维护一个前缀最小值即可
      int maxSubArray(vector<int>& nums) {
          // nums: 0 ~ n-1
          // sum: 0, 1~n
          int n = nums.size();
          vector<long long> sum(n + 1, 0);
          for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
          // 前缀最小值（前i个数的最小值）
          vector<long long> pre(n + 1, 0);
          pre[0] = sum[0];
          for (int i = 1; i <= n; i++) pre[i] = min(pre[i - 1], sum[i]);
  
          long long ans = -1e10;
          for (int i = 1; i <= n; i++) {
              ans = max(ans, sum[i] - pre[i - 1]);
          }
          return ans;
      }       
  };
  ```



## 双指针，滑动窗口

- 11-盛水最多的容器

  ```c++
  class Solution {
  public:
      /*
          盛水多少是由较短的那一根决定的
          双指针从两端相向移动，每次移动较短的那根
      */
      int maxArea(vector<int>& height) {
          int ans = 0;
          int left = 0, right = height.size() - 1;
          while (left < right) {
              ans = max(ans, min(height[left], height[right]) * (right - left));
              if (height[left] == height[right]) {
                  left++;
                  right--;
              } else if (height[left] < height[right]) {
                  left++; 
              } else {
                  right--;
              }
          }
          return ans;
      }
  };
  ```

  

## 单调栈

- 柱状图中最大的矩形

- 思路： 

  - 确定递增递减，考虑“前面不能影响后面”的条件

  - 本题中若h[i - 1] > h[i], 则h[i - 1]这个高度就无法影响到后面，则可以单独运算

  - 代码套路： 

    > for 每个元素
    >
    > ​		while (栈顶与新元素不满足单调性) {
    >
    > ​				弹栈
    >
    > ​				更新答案
    >
    > ​				累加“宽度”
    >
    > ​		}
    >
    > ​		入栈

  ```c++
  class Solution {
  public:
      int largestRectangleArea(vector<int>& heights) {
          heights.push_back(0); // 帮助我们在最后把栈清空
          stack<Rect> s;
          int ans = 0;
          for (int h : heights) {
              // 上一个的高度比当前高，无法继续向右延伸，就进行计算
              // 计算方法为当前高度乘累加宽度，所以需要一个存储累加宽度的变量
              int accumulated_width = 0; 
              while (!s.empty() && s.top().height >= h) { 
                  accumulated_width += s.top().width;
                  ans = max(ans, accumulated_width * s.top().height);
                  s.pop();
              }
              s.push({h, accumulated_width + 1});
          }
          return ans;
      }
  private: 
      struct Rect {
          int height;
          int width;
      };
  };s
  ```



## 单调队列

- 思路： 

  - 单调队列维护的是一个候选集合，前面的较旧，后面的较新（时间有单调性）

  - 候选项的属性也有单调性

  - 确定递增递减的方法：考虑任意两个候选项j1<j2， 写出j1比j2优的条件

    - 排除冗余的关键：若j2比j1差，j1的生命周期还比j2短，那j1就没用了

  - 模版： 

    > for 每个元素
    >
    > ​		while (队首过期) 队首出列
    >
    > ​		取队首为最佳选项，计算答案
    >
    > ​		while (队尾与新元素不满足单调性) 队尾出队
    >
    > ​		新元素入队

  - 239-滑动窗口最大值

    ```c++
    class Solution {
    public:
        vector<int> maxSlidingWindow(vector<int>& nums, int k) {
            vector<int> ans;
            // 双端队列存下标，下标代表时间
            deque<int> q;
            for (int i = 0; i < nums.size(); i++) {
                // 保证队头合法性
                while (!q.empty() && q.front() <= i - k) q.pop_front();
                // 维护队列单调性，插入新的选项
                while (!q.empty() && nums[q.back()] <= nums[i]) q.pop_back();
                q.push_back(i);
                // 取队头更新答案
                if (i >= k - 1) ans.push_back(nums[q.front()]);
            }
            return ans;
        }
    };
    ```

    

## 总结

- 维护的信息时点相关的，使用双指针，是一整个候选集合的，用单调队列

-  满足区间减法性质，可以使用前缀和： 

  - 区间减法性质指的是[l, r] 的信息可以由[1, l-1]和[1, r]的信息导出

  
