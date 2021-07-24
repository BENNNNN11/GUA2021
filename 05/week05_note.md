## 三分查找

1. 二分查找用在单调函数中查找
2. 三分查找用于在**单峰函数**上寻找最大值
   - 取一左一右的两个中值进行比较
   - f(lmid) <= f(rmid): 
     - 极值在lmid和rmid中间
     - lmid < rmid，极值在rmid右侧gnus
     - 函数必在lmid处递增，删除lmid左边的区域，极值在[lmid, r]上
   - f(lmid) > f(rmid): 
     - 极值在lmid和rmid中间
     - lmid > rmid，极值在lmid左侧
     - 函数必在rmid处递减，删除rmid右边的区域，极值在[l, rmid]上
   -  lmid, rmid 可以取三等分点，**也可以取lmid为二等分点，rmid为lmid加一点偏移量**



##  排序

- **初级：平均时间复杂度O(n^2)**

  - **选择排序**
    - 原地：在剩余的数中选最小的然后交换
  - **插入排序**
    - 打牌：来一张牌，找到合适的地方插进去
  - **冒泡排序**
    - 不断循环扫描，每次查看相邻的元素，如果逆序则交换

- **优化：** 

  - **选择排序 => 堆排: 利用堆高效选出最小值**

    - **时间复杂度：O(nlogn)**

      ```c++
      void heap_sort(int a[], int n) {
         	priority_queue<> q;
      	  for (int i = 0; i < n; i++) {
              // 默认是大根堆，变成负数之后加入大根堆中
              q.push(-a[i]);
          }
          for (int i = 0; i < n; i++) {
      				a[i] = -q.top();
            	q.pop();
          } 
      }
      ```

      

  - 插入排序（分组优化） => 希尔排序

    - 设置增量gap = length / 2，将整个数组分为gap个组
    - 对这gap个组分别进行插入排序
    - 缩小增量， gap /= 2，继续对这些组进行插入排序

    

  - **归并排序：基于分治**

    - **时间复杂度： O(nlogn)** **每层合并花费O(n), 一共O(logn)层**

      ```java
      public static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return; 
        int mid = (l + r) >> 1;
       	mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
      }
      
      static void merge(int[] arr, int left, int mid, int right) {
        // 建立临时数组
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1;
        // 合并两个有序数组
        for (int k = 0; k < temp.length; k++) {
          if (j > right || (i <= mid && arr[i] <= arr[j])) {
       			// i <= mid, i指向的元素较小，或者j已经遍历完了
            temp[k] = arr[i++]; // i向前移动
          } else {
            temp[k] = arr[j++];
          }
        }
        // 拷贝回原数组
        for (int k = 0; k < temp.length; k++) {
          arr[left + k] = temp[k];
        }
      }
      ```

  - **快速排序：基于分治**

    - **期望时间复杂度： O(nlogn)**

      - **pivot每次选最小的数，时间复杂度退化到O(n^2)**

    - **步骤：** 

      - **从数组中随机选取pivot**

      - **将小元素放到pivot的左边，大元素放右边**

      - **然后分别对左右两边的子数组再进行快排**

        ```java
        public static void quickSort(int[] arr, int l, int r) {
          	if (l >= r) return;
          	int pivot = partition(arr, l, r);
          	quickSort(arr, l, pivot);
          	quickSort(arr, pivot + 1, r);
        }
        
        // Hoare Partition
        // 随便选择一个数作为排序的基准，方便起见选最右边的数，标记为p
        // 开两个指针l， r， 分别指向最左边和最右边的数
        // l向右移动，遇到大于等于p的时候停止移动
        // r向左移动，遇到小于p的数则停止移动
        // l和r都停止移动时，交换
        // l 继续向右移动， r 继续向左移动
        // 若l和r碰撞时，l和r停止移动
        // 若l和r都停止移动，并指向同一个数，则和p指向的数交换
        static int partition(int[] a, int l, int r) {
          int pivot = l + (int)(Math.random() * (r - l + 1));
          int pivotVal = a[pivot];
          while (l <= r) {
            while (a[l] < pivotVal) l++;
            while (a[r] > pivotVal) r--;
            if (l == r) break;
            if (l < r) {
              int temp = a[l]; a[l] = a[r]; a[r] = temp;
              l++; 
              r--;
            }
          } 
          return r; 
        }
        ```

        

    - 与归并排序的相似性和区别： 

      - 归并排序：
        - 每次砍一半
        - 先让左右子数组分别有序，然后再合并子数组
        - **总结：先递归再处理本层的逻辑**
      - 快速排序：
        - 根据pivot分
        - 先把大小分开，小在左大在右，但内部无序，继续递归
        - **总结：先处理本层的逻辑再递归**

- 稳定性： 

  - 稳定：归并，插入，冒泡，桶排，计数，基数
  - 不稳定：快排，选择， 堆排，希尔

- **非比较的排序方法：** 

  - **计数counting sort： 记录每个元素出现的次数，然后依次把计数大于1的数填到原数组中**
    - **时间复杂度：O(N + M) 元素个数 + 数值范围**
  - 桶排bucket sort
  - 基数radix sort: 把数据切割成一位位的数字，从低位到高位每一位进行计数排序



