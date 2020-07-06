package org.springboot.leetcode;

/**
 * @ClassName ConcurrencyTest
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/10 9:05
 * @Version 1.0
 */
public class ConcurrencyTest {
    private static final long count = 10000l;

    public static void main(String[] args) throws InterruptedException {
        concurrency();

        //serial();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
            System.out.println("main :" + "ms,b=" + b);
        }

    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();//.Join 就是加入的意思,也就是说新创建的线程加入到进程中,并马上执行
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}