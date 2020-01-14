package com.example.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 比较和交换  -> compareAndSwap
 *  原理：主要是unsafe的compareAndSwap方法
 *  缺点：1.循环时间长的话内存开销很大
 *       2.只能保证一个共享变量的原子操作
 *       3.会引起ABA问题
 *
 * 类似于git的版本控制
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2020) + "\t 赋值得：" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 996) + "\t 赋值得：" + atomicInteger.get());
    }

}
