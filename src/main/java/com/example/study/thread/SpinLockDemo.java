package com.example.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁
 *  好处：循环比较知道成功为止，没有类似于wait的方法
 *  坏处：循环的时间过长，会导致CPU的性能严重下降
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t 成功锁住！");
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "解锁");
    }

    public static void main(String[] args) {
        SpinLockDemo lockDemo = new SpinLockDemo();
        new Thread(() -> {
            lockDemo.lock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            lockDemo.unlock();
        }, "AA").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            lockDemo.lock();
            lockDemo.unlock();
        },"BB").start();
    }

}
