package com.example.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题复现及解决方式
 */
public class ABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        method02();
    }

    /**
     * 问题复现
     */
    private static void method01() {
        new  Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        }, "甲").start();

        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t" + atomicInteger.compareAndSet(100, 2020));
        }, "乙").start();
    }

    /**
     * 解决方式：原子更新引用
     */
    private static void method02() {
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "线程获得的版本号：" + stamp);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            stampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "线程结束后版本号：" + stampedReference.getStamp() + "，修改后的值：" + stampedReference.getReference());
        }, "甲").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "线程获得的版本号：" + stamp);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean result = stampedReference.compareAndSet(100, 2020, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "线程结束后版本号：" + stampedReference.getStamp() + "，是否进行修改：" + result);
        }, "乙").start();
    }

}
