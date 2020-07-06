package org.springboot.leetcode;

/**
 * @ClassName FooThread
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/8 17:26
 * @Version 1.0
 */
public class FooThread implements Runnable{
    private String name;

    public FooThread(String name) {
        this.name=name;
    }
    @Override
    public void run() {
        System.out.print("foo");
    }

}