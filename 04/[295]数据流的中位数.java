//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 
// 👍 444 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {
    /** 使用最大堆和最小堆，取两个的最值用来计算中位数 */
    private PriorityQueue<Integer> maxP;
    private PriorityQueue<Integer> minP;
    /** initialize your data structure here. */
    public MedianFinder() {
        // 小根堆
        minP = new PriorityQueue<>();
        // 大根堆
        maxP = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
    }
    /** 添加元素的时候控制两个堆的元素个数之差在1之内 */
    public void addNum(int num) {
        // 如果大根堆的数量大于或者等于小根堆的数量
        //      先向大根堆添加元素，然后取出最大值给小根堆
        // 如果大根堆的元素数量小于小根堆的元素数量
        //      先向小根堆添加元素，然后取出最小值给大根堆
        if (maxP.size() >= minP.size()) {
            maxP.offer(num);
            minP.offer(maxP.poll());
        } else {
            minP.offer(num);
            maxP.offer(minP.poll());
        }
    }

    public double findMedian() {
        // 如果两个堆的元素数量一样，则取平均值
        // 否则，取元素多的那么个堆的最值
        if (minP.size() == maxP.size()) {
            return  (maxP.peek() + minP.peek()) / 2.0;
        }
        if (minP.size() > maxP.size()) {
            return minP.peek();
        }
        return maxP.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
