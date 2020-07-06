package org.springboot.leetcode;

/**
 * @ClassName Thread2
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/9 8:38
 * @Version 1.0
 */
public class Thread2 implements Runnable {
    private String name;

    public Thread2(String name) {
        this.name=name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "执行  :  " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


        public static void main(String[] args) {
            new Thread(new Thread2("C")).start();
            new Thread(new Thread2("D")).start();
        }


}