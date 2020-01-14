package com.example.study.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{

    private volatile Map<String, Object> cache = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 开始读取：");
            try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 读取成功：" + cache.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(String key, Object value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 开始写入：");
            try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            cache.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入成功：" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}

/**
 * 验证读写锁
 *  异常：第一次运行时编号为1的线程读取到了null
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache cache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                cache.set(tempInt + " ", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                cache.get(tempInt + " ");
            }, String.valueOf(i)).start();
        }
    }

}
