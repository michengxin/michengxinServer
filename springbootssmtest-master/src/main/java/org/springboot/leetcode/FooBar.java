package org.springboot.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName FooBar
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/8 16:26
 * @Version 1.0
 */
public class FooBar {
    private ReentrantLock lock = new ReentrantLock();
    private Condition fooCondition = lock.newCondition();
    private Condition barCondition = lock.newCondition();
    private int count = 1;
    private int n;

    public FooBar(int n) {
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            if(count != 1) {
                fooCondition.await();//等待
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            System.out.println("foo");
            printFoo.run();
            barCondition.signal();//通知
            count=2;
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            if(count != 2) {
                barCondition.await();
            }
            System.out.println("bar");
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooCondition.signal();
            count=1;
            lock.unlock();
        }
    }


}