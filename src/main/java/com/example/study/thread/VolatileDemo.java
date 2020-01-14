package com.example.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Number{

    volatile int num = 0;

    public void addTo60(){
        this.num = 60;
    }

    public void addPlusPlus(){
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void myAddAtomic(){
        atomicInteger.getAndIncrement();
    }

}

/**
 * @author: 飞羽
 * @description: volatile特性：可见性，不保证原子性，禁止指令重排
 * @date: 2020/1/3 12:57
 */
public class VolatileDemo {

    public static void main(String[] args) {

    }

    /**
     * 不保证原子性及其解决方案
     */
    private static void notAtom() {
        Number number = new Number();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    number.addPlusPlus();
                    number.myAddAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 默认后台有两个线程：GC和main
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println("int 类型，计算完成:" + number.num);
        System.out.println("AtomicInteger 类型，计算完成:" + number.atomicInteger);
    }

    /**
     * volatile的可见性
     */
    private static void isLook() {
        Number number = new Number();
        new Thread(() -> {
            System.out.println("进来===============");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            number.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t " + number.num);
        }, "A").start();

        while (number.num == 0){

        }

        System.out.println(Thread.currentThread().getName() + "\t 执行完毕！" );
    }

}
