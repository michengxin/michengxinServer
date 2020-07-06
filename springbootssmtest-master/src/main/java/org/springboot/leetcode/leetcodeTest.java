package org.springboot.leetcode;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName leetcodeTest
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/5 9:14
 * @Version 1.0

 */
public  class  leetcodeTest {
    public static int[] twoSum(int[] nums, int target) {
        for(int i =0;i<nums.length;i++){
            for(int j= i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target){
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum2(int[] nums, int target) {
      Map<Integer,Integer> map = new HashMap<Integer,Integer> ();
      for(int i=0;i<nums.length;i++){
          int temp = target -nums[i];
          if(map.containsKey(temp)){
            return new int[]{ map.get(temp),i};
          }
          map.put(nums[i], i);
      }
        throw new IllegalArgumentException("没有匹配的数组元素");
    }

    public static ListNode test(ListNode l1, ListNode l2) {
        if(l1 == null && l2 ==null) return null;
        ListNode dummy = new ListNode(0);  //合并的链表头.作为l1.l2合并的总链表头
        ListNode head = dummy;
        int addOne = 0;
        while(l1 !=null || l2 != null || addOne !=0){
            int val1 =l1 ==null ? 0:l1.val;
            int val2 =l2 ==null ? 0:l2.val;
            int sum = val1 +val2+addOne;
            head.next = new ListNode(sum%10);
            head = head.next;
            addOne = sum/10;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }
    @Data
    public static  class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public static void Getnext(int next[],String t)
    {
        int j=0,k=-1;
        next[0]=-1;
        while(j<t.length()-1)
        {
            if(k == -1 || t.charAt(j) == t.charAt(k))
            {
                j++;k++;
                next[j] = k;
            }
            else k = next[k];
        }
        System.out.println(j);
        System.out.println(k);
    }

    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
 // 示例:
  //
  // 给定 nums = [2, 7, 11, 15], target = 9
  //
  //因为 nums[0] + nums[1] = 2 + 7 = 9
  //所以返回 [0, 1]
 //    --------------第一题------------------------------------------------------
        leetcodeTest a = new leetcodeTest();
        int[] nums ={2,7, 11, 15};
        int target = 17;
        //System.out.println(a.twoSum(nums,target));
        //System.out.println(a.twoSum1(nums,target));
        System.out.println(a.twoSum2(nums,target)[0]);
        System.out.println(a.twoSum2(nums,target)[1]);
//     ------------第二题---------------------------------------------------------
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
// 示例：
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
//
//       ListNode l1 =  new ListNode(2);
//       l1.setNext(new ListNode(4));
//       ListNode l2 =  new ListNode(5);
//       l2.setNext(new ListNode(6));
//       System.out.println(leetcodeTest.test(l1,l2));
//       int [] a ={2,3,4,5,6};
//       leetcodeTest.Getnext(a,"abc");
//       leetcodeTest.lengthOfLongestSubstring("abcaaaabcd");
    }
}