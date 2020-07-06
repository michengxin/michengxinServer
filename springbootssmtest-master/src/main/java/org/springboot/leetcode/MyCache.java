package org.springboot.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName MyCache
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/11 8:56
 * @Version 1.0
 */
public class MyCache {
    private volatile Map<String, String> map = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, String value) {

        //rwLock.writeLock().lock();

        System.out.println(Thread.currentThread().getName() + "正在写......");

        try {

            TimeUnit.MILLISECONDS.sleep(500);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally {
           // rwLock.writeLock().unlock();
        }

        map.put(key, value);

        System.out.println(Thread.currentThread().getName() + "写已经完成");



    }


    public String get(String key) {

        rwLock.readLock().lock();

        System.out.println(Thread.currentThread().getName() + "正在读......");

        try {

            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {

            e.printStackTrace();

        }finally {
            rwLock.readLock().unlock();
        }
        String value = map.get(key);

        System.out.println(Thread.currentThread().getName() + "读已经完成");



        return value;

    }

}