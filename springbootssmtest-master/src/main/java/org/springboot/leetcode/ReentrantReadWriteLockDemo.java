package org.springboot.leetcode;

/**
 * @ClassName ReentrantReadWriteLockDemo
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/11 9:01
 * @Version 1.0
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {

            int finalI = i;

            new Thread(() -> {
                myCache.put(finalI + "", finalI + "");

            }, i + "").start();
        }


        for (int i = 0; i < 5; i++) {

            int finalI = i;

            new Thread(() -> {

                myCache.get(finalI + "");

            }, i + "").start();

        }


    }

}