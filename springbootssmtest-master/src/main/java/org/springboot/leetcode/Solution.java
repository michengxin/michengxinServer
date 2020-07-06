package org.springboot.leetcode;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/12 8:32
 * @Version 1.0
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            // 枚举 b  在枚举a的数组指针的后一位跟着
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] + nums[first] > 0) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] + nums[first] == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    //List<Boolean>
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        if (candies.length == 0) return null;
        int max = 0;//将数组中最大的数
        List<Boolean> returnList = new ArrayList<Boolean>();//初始化boolean数组
        for (int i = 0; i < candies.length; i++) {//将数组中最大的数取出来
            if (i == 0 || candies[i] > max) max = candies[i];
        }
        for (int i = 0; i < candies.length; i++) {//将数组每个数+extraCandies 跟数组中最大的数比较
            returnList.add(candies[i] + extraCandies >= max);
        }
        return returnList;
    }

    //    输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
//
//             
//
//    示例 1：
//
//    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//    输出：[1,2,3,6,9,8,7,4,5]
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] returnInt = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                returnInt[index++] = matrix[top][i];
            }
            for (int i = top + 1; i <= bottom; i++) {
                returnInt[index++] = matrix[i][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    returnInt[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    returnInt[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return returnInt;
    }

    /**
     * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * 示例 1：
     * 输入: n = 3
     * 输出: 6
     * 示例 2：
     * 输入: n = 9
     * 输出: 45
     **/
    public int sumNums(int n) {
        int a = n == 0 ? n : sumNums(n - 1);
        return n += a;
    }

    /**
     * 找出数组中重复的数字。
     * <p>
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：[2,3]
     */
    public Integer[] findRepeatNumber(int[] nums) {
        List<Integer> returnArr = new ArrayList<>();
        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    length++;
                    returnArr.add(nums[i]);
                }
            }
        }
        Integer[] targetArr = new Integer[length];
        return returnArr.toArray(targetArr);
    }

    /**
     * 找出数组中重复的数字。
     * <p>
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出2
     */
    public int findRepeatNumber1(int[] nums) {
        Set<Integer> middleset = new HashSet<Integer>();
        int returnInt = -1;
        for (int num : nums) {
            if (!middleset.add(num)) {
                returnInt = num;
                break;
            }
        }
        return returnInt;
    }

    /**
     * //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * //
     * // 示例 1:
     * //
     * // 输入: "abcabcbb"
     * //输出: 3
     * //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * //
     * //
     * // 示例 2:
     * //
     * // 输入: "bbbbb"
     * //输出: 1
     * //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * //
     * //
     * // 示例 3:
     * //
     * // 输入: "pwwkew"
     * //输出: 3
     * //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * //
     */
    public String lengthOfLongestSubstring(String s) {
        return null;
    }

    /**
     * 输入两个字符串 输出两个字符串的连接
     * 示例 1：
     * 输入：
     * "qwer" ,"tyui"
     * 输出 "qwertyui" "tyuiqwer" 以第一个字符串开头，或者以第二个字符串开头
     * <p>
     * 示例 2：
     * 输入：
     * "qwer" ,"rq"
     * 输出"qwerq" "rqwer" 以第一个字符串开头，或者以第二个字符串开头
     * <p>
     * 示例 3：
     * 输入：
     * "qwer" ,"wer"
     * 输出"qwer" 两个字符串相互包含，取大字符串
     */
    public String[] concact(String str1, String str2) {
        List<String> returnArr = new ArrayList<>();
        //第三种情况 判断str1 和str2 相互是否包含,只要包含 返回大的str
        if (str1.contains(str2)) {
            returnArr.add(str1);
            String[] targetArr = new String[1];
            return returnArr.toArray(targetArr);
        }
        if (str2.contains(str1)) {
            returnArr.add(str2);
            String[] targetArr = new String[1];
            return returnArr.toArray(targetArr);
        }
        char[] str1Char = str1.toCharArray();
        char[] str2Char = str2.toCharArray();
        //第一种情况 str1 的头 和 str2 的尾没有相同 且 str2的头 和str1的尾 没有相同
        if (str1Char[str1.length() - 1] != str2Char[0] && str2Char[str2.length() - 1] != str1Char[0]) {
            returnArr.add(str2.concat(str1));
            returnArr.add(str1.concat(str2));
            String[] targetArr = new String[1];
            return returnArr.toArray(targetArr);
        }
        //第二种情况,半包含 连接  qwer   erbn
        for (int i = 0; i < str1Char.length; i++) {

//            for(int j=0;j<str2Char.length;j++){
//
//            }
        }
        return null;
    }

    /**
     * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
     * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
     * 返回一对观光景点能取得的最高分。
     * 示例：
     * 输入：[8,1,5,2,6]
     * 输出：11
     * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
     * 提示：
     * 2 <= A.length <= 50000
     * 1 <= A[i] <= 1000
     **/
    public int maxScoreSightseeingPair(int[] A) {
//      int max = 0;
//      for(int i =0;i<A.length;i++){
//          for(int j= i+1;j<A.length;j++){
//              if(A[i]+A[j]+i-j>max) max = A[i]+A[j]+i-j;
//          }
//      }
//      return max;
        int ans = 0, mx = A[0] + 0;
        for (int j = 1; j < A.length; ++j) {
            ans = Math.max(ans, mx + A[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, A[j] + j);
        }
        return ans;
    }

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 121
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * <p>
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * 进阶:
     * <p>
     * 你能不将整数转为字符串来解决这个问题吗？
     */
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        //定义倒序的char[]数组
        char[] sChar1 = new char[s.length()];
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            sChar1[s.length() - i - 1] = sChar[i];
        }
        String s1 = String.valueOf(sChar1);
        if (s1.equals(s)) return true;
        return false;
    }

    /**
     * 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）
     * 所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。
     * 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。
     * 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。
     */
    public Solution() {

    }

    //1个Fork视为1个ReentrantLock，5个叉子即5个ReentrantLock，将其都放入数组中
    private final ReentrantLock[] lockList = {new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()};

    //限制 最多只有4个哲学家去持有叉子
    private Semaphore eatLimit = new Semaphore(4);

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int leftFork = (philosopher + 1) % 5;    //左边的叉子 的编号
        int rightFork = philosopher;    //右边的叉子 的编号
        System.out.println(leftFork);
        System.out.println(rightFork);
        //在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入
        eatLimit.acquire();    //限制的人数 -1

        lockList[leftFork].lock();    //拿起左边的叉子
        lockList[rightFork].lock();    //拿起右边的叉子

        pickLeftFork.run();    //拿起左边的叉子 的具体执行
        pickRightFork.run();    //拿起右边的叉子 的具体执行

        eat.run();    //吃意大利面 的具体执行

        putLeftFork.run();    //放下左边的叉子 的具体执行
        putRightFork.run();    //放下右边的叉子 的具体执行

        lockList[leftFork].unlock();    //放下左边的叉子
        lockList[rightFork].unlock();    //放下右边的叉子

        eatLimit.release();//限制的人数 +1

    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <p>
     * 示例 1:
     * 输入: "()"
     * 输出: true
     * <p>
     * 示例 2:
     * 输入: "()[]{}"
     * 输出: true
     * <p>
     * 示例 3:
     * 输入: "(]"
     * 输出: false
     * <p>
     * 示例 4:
     * 输入: "([)]"
     * 输出: false
     * <p>
     * 示例 5:
     * 输入: "{[]}"
     * 输出: true
     */
    public boolean isValid(String s) {
        s = s.trim();
        if (s.length() == 0) return true;
        else if (s.length() % 2 == 1) {
            return false;
        } else {
            char[] returnArr = s.toCharArray();
            if (!(((byte) returnArr[0] == 123 && (byte) returnArr[s.length() - 1] == 125) ||
                    (((byte) returnArr[0] == 40 && (byte) returnArr[s.length() - 1] == 41))
                    || (((byte) returnArr[0] == 91 && (byte) returnArr[s.length() - 1] == 93)))) {
                for (int i = 0; i < returnArr.length; i++) {
                    if (i % 2 == 0) { //[]{}()这种情况
                        if (!(((byte) returnArr[i] == 123 && (byte) returnArr[i + 1] == 125) ||
                                (((byte) returnArr[i] == 40 && (byte) returnArr[i + 1] == 41))
                                || (((byte) returnArr[i] == 91 && (byte) returnArr[i + 1] == 93)))) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                //do to something!!!
                for (int i = 1; i < returnArr.length / 2; i++) {
                    System.out.println((byte) returnArr[i]);
                    System.out.println((byte) returnArr[s.length() - 1 - i]);
                    if (!(((byte) returnArr[i] == 123 && (byte) returnArr[s.length() - 1 - i] == 125) ||
                            (((byte) returnArr[i] == 40 && (byte) returnArr[s.length() - 1 - i] == 41))
                            || (((byte) returnArr[i] == 91 && (byte) returnArr[s.length() - 1 - i] == 93)))) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

//    给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小
//    的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
//    示例: 
//
//    输入: s = 7, nums = [2,3,1,2,4,3]
//    输出: 2
//    解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
//    进阶:
//    如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
    public int minSubArrayLen(int s, int[] nums) { //O(2n)
        if (nums.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max += nums[i];
        }
        if (max < s) return 0;
        int numberInt = 100000;//储存加了几个数的集合
        int i = 0;
        int j = 0;//存了加了几个数(count)
        int k = 0;//存了几个数加的总和
        int index = 0;
        while (i < nums.length) {
            ++j;
            k += nums[i];
            ++i;
            if (k >= s) {
                if (j < numberInt) {
                    numberInt = j;
                }
                index++;
                i = index;
                j = 0;
                k = 0;
            }
        }
        return numberInt;
    }

    //    在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
//    示例 1:
//    输入: [3,2,1,5,6,4] 和 k = 2
//    输出: 5
//    示例 2:
//    输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//    输出: 4
//    说明:
//    你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
    public int findKthLargest(int[] nums, int k) {
        if(nums.length < k) return 0;
        //快速排序后 取下标
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length/2-1; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length-i-1];
            nums[nums.length-i-1] = temp;
        }
        return nums[k-1];
    }
//    用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
//    示例 1：
//    输入：
//            ["CQueue","appendTail","deleteHead","deleteHead"]
//            [[],[3],[],[]]
//    输出：[null,null,3,-1]
//    示例 2：
//    输入：
//            ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//            [[],[],[5],[2],[],[]]
//    输出：[null,-1,null,null,5,2]
class CQueue {
    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public CQueue() {
        stack1 = new LinkedList<Integer>();
        stack2 = new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        // 如果第二个栈为空
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            return -1;
        } else {
            int deleteItem = stack2.pop();
            return deleteItem;
        }
    }
}
//    给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
//    注意：1 ≤ k ≤ n ≤ 109。
//    示例 :
//    输入:
//    n: 13   k: 2
//    输出:
//            10
//    解释:
//    字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
    public int findKthNumber(int n, int k) {
        int cur=1;//第一字典序小的(就是1)
        int prefix=1;// 前缀从1开始
        while(cur<k) {
            int tmp=count(n,prefix); //当前prefix峰的值
            if(tmp+cur>k) {// 找到了
                prefix*=10; //往下层遍历
                cur++;//一直遍历到第K个推出循环
            }else {
                prefix++;//去下个峰头(前缀)遍历
                cur+=tmp;//跨过了一个峰(前缀)
            }
        }//退出循环时 cur==k 正好找到
        return prefix;
    }
    private int count(int n, int prefix) {
        //不断向下层遍历可能一个乘10就溢出了, 所以用long
        long cur=prefix;
        long next=cur+1;//下一个前缀峰头
        int count=0;
        while(cur<=n) {
            count+=Math.min(n+1,next)-cur;//下一峰头减去此峰头
            // 如果说刚刚prefix是1，next是2，那么现在分别变成10和20
            // 1为前缀的子节点增加10个，十叉树增加一层, 变成了两层
            // 如果说现在prefix是10，next是20，那么现在分别变成100和200，
            // 1为前缀的子节点增加100个，十叉树又增加了一层，变成了三层
            cur*=10;
            next*=10; //往下层走
        }
        return count;
    }
//    写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
//    F(0) = 0,   F(1) = 1
//    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//    斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
//    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//    示例 1：
//    输入：n = 2
//    输出：1
//    示例 2：
//    输入：n = 5
//    输出：5
    public int fib(int n) {
        if(n ==0) return 0;
        if(n ==1) return 1;
        return fib(n-1)+fib(n-2);
    }
//    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
//    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//    示例:
//
//    输入："23"
//    输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//      public List<String> letterCombinations(String digits) {
//         char[] digitsArr = digits.toCharArray();
//         Map<Integer,List<String>> map = new HashMap<>();
//
//         for(int i =0;i<digitsArr.length;i++){
//             map.put(i,numberToLetterArr(String.valueOf(digitsArr[i])));
//         }
//         int j = digitsArr.length-1;//从最后一个下标开始递归
//         List<String> returnList1 = digui(map,j,null);
//         return returnList1;
//      }

//      public static List<String> digui(Map<Integer,List<String>> map,int j,List<String> returnList){
//          if(returnList == null){
//              returnList = new ArrayList<>();
//          }
//          while(j < 0){
//              List<String> arr1 = map.get(j);
//              List<String> arr2 = map.get(j-1);
//              List<String> arr3= new ArrayList<>();
//              for (String str1:arr1
//                   ) {
//                  for (String str2:arr2
//                   ) {
//
//                  }
//              }
//              map.put(j-1,arr3);
//              j--;
//              digui(map,j,returnList);
//          }
//          return null;
//      }
      //将数字转化为对应的字母数组
//      public static List<String> numberToLetterArr(String number){
//          List<String> returnList = new ArrayList<>();
//             switch (number) {
//                 case "1":
//                     break;
//                 case "2":
//                     returnList.add("a");
//                     returnList.add("b");
//                     returnList.add("c");
//                     break;
//                 case "3":
//                     returnList.add("d");
//                     returnList.add("e");
//                     returnList.add("f");
//                     break;
//                 case "4":
//                     returnList.add("g");
//                     returnList.add("h");
//                     returnList.add("i");
//                     break;
//                 case "5":
//                     returnList.add("j");
//                     returnList.add("k");
//                     returnList.add("l");
//                     break;
//                 case "6":
//                     returnList.add("m");
//                     returnList.add("n");
//                     returnList.add("o");
//                     break;
//                 case "7":
//                     returnList.add("p");
//                     returnList.add("q");
//                     returnList.add("r");
//                     returnList.add("s");
//                     break;
//                 case "8":
//                     returnList.add("t");
//                     returnList.add("u");
//                     returnList.add("v");
//                     break;
//                 case "9":
//                     returnList.add("w");
//                     returnList.add("x");
//                     returnList.add("y");
//                     returnList.add("z");
//                     break;
//             }
//             return returnList;
//      }
//    给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
//    示例 1:
//    输入:
//    A: [1,2,3,2,1]
//    B: [3,2,1,4,7]
//    输出: 3
//    解释:
//    长度最长的公共子数组是 [3, 2, 1]。
//    说明:
//            1 <= len(A), len(B) <= 1000
//            0 <= A[i], B[i] < 100
//public int findLength(int[] A, int[] B) {
//    int n = A.length, m = B.length;
//    int ret = 0;
//    for (int i = 0; i < n; i++) {
//        int len = Math.min(m, n - i);
//        int maxlen = maxLength(A, B, i, 0, len);
//        ret = Math.max(ret, maxlen);
//    }
//    for (int i = 0; i < m; i++) {
//        int len = Math.min(n, m - i);
//        int maxlen = maxLength(A, B, 0, i, len);
//        ret = Math.max(ret, maxlen);
//    }
//    return ret;
//}
//
//    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
//        int ret = 0, k = 0;
//        for (int i = 0; i < len; i++) {
//            if (A[addA + i] == B[addB + i]) {
//                k++;
//            } else {
//                k = 0;
//            }
//            ret = Math.max(ret, k);
//        }
//        return ret;
//    }
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
//    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//
//             
//
//    示例:
//
//    给定 nums = [2, 7, 11, 15], target = 9
//
//    因为 nums[0] + nums[1] = 2 + 7 = 9
//    所以返回 [0, 1]
      public int[] twoSum(int[] nums, int target) {
          Map<Integer, Integer> map = new HashMap<>();
          for (int i = 0; i < nums.length; i++) {
              int complement = target - nums[i];
              if (map.containsKey(complement)) {
                  return new int[] { map.get(complement), i };
              }
              map.put(nums[i], i);
          }
          throw new IllegalArgumentException("No two sum solution");
      }
//    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
//    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
//    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//    示例：
//
//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
    public static class ListNode {  //链表实体类
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

//    测试用例	说明
//    l1=[0,1],l2=[0,1,2]	当一个列表比另一个列表长时
//    l1=[],l2=[0,1]	当一个列表为空时，即出现空列表
//    l1=[9,9],l2=[1]	求和运算最后可能出现额外的进位，这一点很容易被遗忘

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;

    }
//    哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
//    像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
//    在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，
//    有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，
//    返回未识别的字符数。
//    注意：本题相对原题稍作改动，只需返回未识别的字符数
//    示例：
//    输入：
//    dictionary = ["looked","just","like","her","brother"]
//    sentence = "jesslookedjustliketimherbrother"
//    输出： 7
//    解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
    public int respace(String[] dictionary, String sentence) {
        Set<String> dic = new HashSet<>();
        for(String str: dictionary) dic.add(str);
        int n = sentence.length();
        //dp[i]表示sentence前i个字符所得结果
        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++){
            dp[i] = dp[i-1]+1;  //先假设当前字符作为单词不在字典中
            for(int j=0; j<i; j++){
                if(dic.contains(sentence.substring(j,i))){
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }
//    给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
//    注意:
//    输入只包含小写英文字母。
//    输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
//    输入字符串的长度小于 50,000。
//    示例 1:
//    输入: "owoztneoer"
//    输出: "012" (zeroonetwo)
//    示例 2:
//    输入: "fviefuro"
//    输出: "45" (fourfive)
    public static String originalDigits(String s) {
       //zero one two three four five six seven eight nine
        // building hashmap letter -> its frequency
        char[] count = new char[26 + (int)'a'];
        for(char letter: s.toCharArray()) {
            count[letter]++;
        }
        // building hashmap digit -> its frequency
        int[] out = new int[10];
        // letter "z" is present only in "zero"
        out[0] = count['z'];
        // letter "w" is present only in "two"
        out[2] = count['w'];
        // letter "u" is present only in "four"
        out[4] = count['u'];
        // letter "x" is present only in "six"
        out[6] = count['x'];
        // letter "g" is present only in "eight"
        out[8] = count['g'];
        // letter "h" is present only in "three" and "eight"
        out[3] = count['h'] - out[8];
        // letter "f" is present only in "five" and "four"
        out[5] = count['f'] - out[4];
        // letter "s" is present only in "seven" and "six"
        out[7] = count['s'] - out[6];
        // letter "i" is present in "nine", "five", "six", and "eight"
        out[9] = count['i'] - out[5] - out[6] - out[8];
        // letter "n" is present in "one", "nine", and "seven"
        out[1] = count['n'] - out[7] - 2 * out[9];

        // building output string
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < 10; i++)
            for (int j = 0; j < out[i]; j++)
                output.append(i);
        return output.toString();
    }
//    给你一个由若干 0 和 1 组成的二维网格 grid，
//    请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。
//    如果不存在，则返回 0。
//    示例 1：
//    输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
//    输出：9
//    示例 2：
//    输入：grid = [[1,1,0,0]]
//    输出：1
      public int largest1BorderedSquare(int[][] grid) {
        if(grid.length == 0) return 0;
        if(grid[0].length == 0) return 0;
        int i = grid.length;
        int j = grid[0].length;

        return 0;
      }
//    给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
//    请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
//    示例：
//    matrix = [
//            [ 1,  5,  9],
//            [10, 11, 13],
//            [12, 13, 15]
//            ],
//    k = 8,
//    返回 13。
    public static int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length, columns = matrix[0].length;
        int[] sorted = new int[rows * columns];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sorted[index++] = num;
            }
        }
        Arrays.sort(sorted);
        return sorted[k - 1];
    }
//    给定仅有小写字母组成的字符串数组 A，
//    返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
//    例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
//    你可以按任意顺序返回答案。
//    示例 1：
//    输入：["bella","label","roller"]
//    输出：["e","l","l"]
//    示例 2：
//    输入：["cool","lock","cook"]
//    输出：["c","o"]
//    提示：
//    1 <= A.length <= 100
//     1 <= A[i].length <= 100
//    A[i][j] 是小写字母
    public static List<String> commonChars(String[] A) {
        int[] hash = new int[26];
        int[] temp = new int[26];
        for(int i = 0; i < 26; i++)
            hash[i] = 100;//每个字符出现次数最多100次，因为题目有限制每个单词最多含有100字符
        for(int i = 0; i < A.length; i++)
        {
            for(char c : A[i].toCharArray())
                temp[c - 'a']++;
            for(int j = 0; j < 26; j++)
            {
                hash[j] = Math.min(hash[j], temp[j]);
                temp[j] = 0;
            }
        }
        List<String> list = new LinkedList<String>();
        for(int i = 0; i < 26; i++)
        {
            while(hash[i] > 0)
            {
                list.add(String.valueOf((char)(i + 'a')));
                hash[i]--;
            }
        }
        return list;

    }

    /**
     * 输入两数组，第二个数组是第一个数组的子集
     * 输出第一个数组和第二数组的交集
     * @param s1
     * @param s2  s1的子集
     * @return
     */
    public static List<String> findNotRepeat(String[] s1,String[] s2){
        List<String> s3 = Arrays.asList(s1);
        s3 = new ArrayList<>(s3);
        List<String> reduce1 = s3.stream().filter(item -> !Arrays.asList(s2).contains(item)).collect(toList());
       return reduce1;
    }
//    在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
//    示例:
//    s = "abaccdeff"
//    返回 "b"
//    s = ""
//    返回 " "
    public static char firstUniqChar(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc)
            dic.put(c, !dic.containsKey(c));
        for(char c : sc)
            if(dic.get(c)) return c;
        return ' ';
    }
        //    编写一个函数来查找字符串数组中的最长公共前缀。
        //
        //    如果不存在公共前缀，返回空字符串 ""。
        //
        //    示例 1:
        //
        //    输入: ["flower","flow","flight"]
        //    输出: "fl"
        //    示例 2:
        //
        //    输入: ["dog","racecar","car"]
        //    输出: ""
        //    解释: 输入不存在公共前缀。
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            int length = strs[0].length();
            int count = strs.length;
            for (int i = 0; i < length; i++) {
                char c = strs[0].charAt(i);
                for (int j = 1; j < count; j++) {
                    if (i == strs[j].length() || strs[j].charAt(i) != c) {
                        return strs[0].substring(0, i);
                    }
                }
            }
            return strs[0];
        }
//    给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
//    其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
//    示例:
//    输入: [1,2,3,4,5]
//    输出: [120,60,40,30,24]
      public static int[] constructArr(int[] a) {
          if(a.length == 0) return new int[0];
          int[] b = new int[a.length];
          b[0] = 1;
          int tmp = 1;
          for(int i = 1; i < a.length; i++) {
              b[i] = b[i - 1] * a[i - 1];
          }
          for(int i = a.length - 2; i >= 0; i--) {
              tmp *= a[i + 1];
              b[i] *= tmp;
          }
          return b;
      }
//    一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
//    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//    示例 1：
//    输入：n = 2
//    输出：2
//    示例 2：
//    输入：n = 7
//    输出：21
      public int numWays(int n) {
          int a = 1, b = 1, sum;
          for(int i = 0; i < n; i++){
              sum = (a + b) % 1000000007;
              a = b;
              b = sum;
          }
          return a;
      }
//    输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
//    示例：
//    输入：nums = [1,2,3,4]
//    输出：[1,3,2,4]
//    注：[3,1,2,4] 也是正确的答案之一。
      public static int[] exchange(int[] nums) {
          if (nums == null || nums.length == 0)
              return new int[]{};
          List<Integer> qishuList = new ArrayList<>();//奇数集合
          List<Integer> oushuList = new ArrayList<>();//偶数集合
          for(int i =0;i<nums.length;i++){
              if(nums[i]%2 ==1) qishuList.add(nums[i]);
              else oushuList.add(nums[i]);
          }
          qishuList.addAll(oushuList);
          int[] ints = new int[qishuList.size()];
          for(int i = 0;i<qishuList.size();i++) {
              ints[i] = qishuList.get(i);
          }
          return ints;
      }
//      输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
//      示例：
//
//      给定一个链表: 1->2->3->4->5, 和 k = 2.
//      返回链表 4->5.
      public static ListNode getKthFromEnd(ListNode head, int k) {
          int i =0;
          while(head.next !=null){

          }
      }

      public static void main(String[] args) throws InterruptedException {
//        Solution s = new Solution();
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        s.threeSum(nums);
//        Solution s = new Solution();
//        int[] candies = {2,3,5,1,3};
//        int extraCandies = 3;
//        System.out.println(s.kidsWithCandies(candies,extraCandies));
//        Solution s = new Solution();
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
//        s.spiralOrder(matrix);
//        int[] b = s.spiralOrder(matrix);
//
//        for(int i =0;i<b.length;i++){
//            System.out.print(b[i]+" ");
//        }
//        Solution s = new Solution();
//        int a = 9;
//        int b = s.sumNums(a);
//        System.out.println(b);
//        Solution s = new Solution();
//        int[] a ={2, 3, 1, 0, 2, 5, 3};
//        System.out.println(s.findRepeatNumber1(a));
//        Solution s = new Solution();
//        String[] a = s.concact("qwer","tyui");
//        System.out.println(Arrays.toString(a));
//        Solution s = new Solution();
//        int[] a= {8,1,5,2,6};
//        System.out.println(s.maxScoreSightseeingPair(a));
//        Solution s = new Solution();
//        int x = 12321;
//        System.out.println(s.isPalindrome(x));
//          Solution s = new Solution();
//          int philosopher = 4;
//        Runnable pickLeftFork = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("使用左边的叉子");
//            }
//        };
//        Runnable pickRightFork = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("使用右边的叉子");
//            }
//        };
//        Runnable eat = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("吃意大利面中......");
//            }
//        };
//        Runnable putLeftFork = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("放下左边的叉子");
//            }
//        };
//        Runnable putRightFork = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("放下右边的叉子");
//            }
//        };
//          s.wantsToEat(philosopher,
//          pickLeftFork,
//          pickRightFork,
//          eat,
//          putLeftFork,
//          putRightFork);
//        Solution s = new Solution();
//        String a = "{[]}";
//        System.out.println("正确结果:" + s.isValid(a));

//        Solution s = new Solution();
//        int a = 7;
//        int[] nums = {2,3,1,2,4,3};
//        System.out.println(s.minSubArrayLen(a,nums));
//          Solution s = new Solution();
//          s.findKthNumber(13,2);
//          int[] num ={3,2,1,5,6,4};
//          int a = 2;
//          s.findKthLargest(num,a);
//        Solution s = new Solution();
//        System.out.println(s.fib(5));
//          Solution s = new Solution();
//          s.letterCombinations("23");
//          int[] a ={1,2,3,2,1};
//          int[] b ={3,2,1,4,7};
//
//          System.out.println(s.findLength(a,b));
        //  (2 -> 4 -> 3) + (5 -> 6 -> 4)
//        Solution s = new Solution();
//        ListNode l1 = new ListNode(2);
//        ListNode l11 = new ListNode(4);
//        l1.next =l11;
//        ListNode l12 = new ListNode(3);
//        l11.next=l12;
//        ListNode l2 = new ListNode(5);
//        ListNode l21 = new ListNode(6);
//        l2.next =l21;
//        ListNode l22 = new ListNode(4);
//        l21.next=l22;
//        addTwoNumbers(l1,l2);
//        String s = "owoztneoer";
//        originalDigits(s);
//          int[][] matrix = {
//                 {1, 5, 9},
//                 {10, 11, 13},
//                 {12, 13, 15}
//          };
//          int  k = 8;
//          System.out.println(kthSmallest(matrix,k));
//            System.out.println(getWay());
//        String[] A ={"ccc","bbb","ab","dddddd","cc","a"};
//        commonChars(A);
//        String[] s1 = {"1","2","3","4","5"};
//        String[] s2 = {"2","5","1"};
//        System.out.println(findNotRepeat(s1,s2));
//          String s = "abaccdeff";
//          System.out.println(firstUniqChar(s));
//        int[] a ={1,2,3,4,5};
//        int[] b =exchange(a);
          ListNode l1 = new ListNode(1);
          ListNode l2 = new ListNode(2);
          l1.next =l2;
          ListNode l3 = new ListNode(3);
          l2.next =l3;
          ListNode l4 = new ListNode(4);
          l3.next =l4;
          ListNode l5 = new ListNode(5);
          l4.next =l5;
          getKthFromEnd(l1,2);


    }
}