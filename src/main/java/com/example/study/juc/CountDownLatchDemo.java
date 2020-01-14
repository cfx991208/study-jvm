package com.example.study.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 减法计数器
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t开始离开");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("全部走完，" + Thread.currentThread().getName() + "开始关闭");
    }

}
