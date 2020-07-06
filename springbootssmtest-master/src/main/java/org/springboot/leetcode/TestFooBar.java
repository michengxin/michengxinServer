package org.springboot.leetcode;

/**
 * @ClassName TestFooBar
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/8 17:00
 * @Version 1.0
 */
public class TestFooBar {


    public static void main(String[] args) throws InterruptedException {
        FooBar p = new FooBar(2);
        p.bar(new BarThread("a"));

    }


}