package com.example.study.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Numberss{

    private int num;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void jia() throws InterruptedException {
        lock.lock();
        try {
            while (num == 1){
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + num);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void jian() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * Lock版
 */
public class ThreadWaitNotifyDemo1 {

    public static void main(String[] args) {
        Numberss numbers = new Numberss();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    numbers.jia();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA：").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    numbers.jian();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB：").start();
    }

}
