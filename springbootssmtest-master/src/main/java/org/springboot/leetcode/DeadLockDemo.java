package org.springboot.leetcode;

/**
 * @ClassName DeadLockDemo
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/10 13:45
 * @Version 1.0
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) throws InterruptedException {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try { Thread.currentThread().sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println("1");
                    synchronized (B) {
                        System.out.println("2");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("3");
                    }
                }
            }
        });
            t1.start();
            t2.start();

    }
}