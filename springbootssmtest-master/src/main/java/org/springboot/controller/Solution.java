package org.springboot.controller;

import java.util.ArrayList;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/3 18:07
 * @Version 1.0
 */
public class Solution {
    //Insert one char from stringstream
    //请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
    //输出描述:
    //如果当前字符流没有存在出现一次的字符，返回#字符。

    //return the first appearence once char in current stringstream
    public void firstAppearingOnce(String a)
    {
        ArrayList<Character> list = new ArrayList<>();
        for(int i =0;i<a.length();i++){
            if(list.size()>0) {
                for (int j =0;j<list.size();j++
                ) {
                    if(list.get(j).equals(a.charAt(i))){
                        list.remove(list.get(j));
                        System.out.println(list);
                    }
                    if(j == list.size()-1 && !list.get(j).equals(a.charAt(i))){
                        list.add(a.charAt(i));
                        System.out.println(list);
                    }
                }
            }else{
                list.add(a.charAt(i));
                System.out.println(list);
            }
        }

    }
    public static void main(String[] args) {
        Solution a = new Solution();
        a.firstAppearingOnce("googlwqqfwwql");
    }
}