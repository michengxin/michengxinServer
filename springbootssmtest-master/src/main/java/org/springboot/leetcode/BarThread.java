package org.springboot.leetcode;

/**
 * @ClassName BarThread
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/8 17:29
 * @Version 1.0
 */
public class BarThread implements Runnable{
    private String name;

    public BarThread(String name) {
        this.name=name;
    }
    @Override
    public void run() {
        System.out.print("bar");
    }
}