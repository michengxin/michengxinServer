package org.springboot.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Stack
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/11 15:13
 * @Version 1.0
 */
public class Stack {
    ArrayList<Object> list = new ArrayList<>();

    //入栈
    public void push(Object o){
        list.add(o);
    }
    //出栈
    public Object pop(){
        Object o = list.get(list.size() - 1);
        list.remove(o);
        return o;
    }
    //栈是否为空
    public boolean isEmpty(){
        return list.isEmpty();
    }
    //栈大小
    public int size(){
        return list.size();
    }
    //栈进1348(10进制) 转为8进制 2504(8进制)
//    public List<Integer> convesertEight(Stack stack){
//
//    }
    //打印栈元素
    @Override
    public String toString(){
        return String.valueOf(list);
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(3);
        stack.push(4);
        stack.push(8);
    }
}