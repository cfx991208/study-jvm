package com.example.study.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{

    static Lock lock = new ReentrantLock();

    public static synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName() + "\t 发送短信");
        sendEmail();
    }

    public static synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + "\t ##################发送邮件");
    }

    public static void get(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t invoke get");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void set(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t #######invoke set");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        get();
    }
}


/**
 * 可重入锁（又叫递归锁）
 *  同一线程外函数获得锁后，内层递归函数仍能获取该锁的代码，
 *  同一个线程外层获得锁后，在进入内层方法会自动获取锁
 *
 *  即：线程可以进入任意一个他已经拥有的锁所同步着的代码（操蛋的官方文档）
 *
 *  ReenterLock和Synchronized都是可重入锁
 */
public class ReenterLockDemo {

    public static void main(String[] args) {

        new Thread(() -> {
            Phone.sendSMS();
        }, "t1").start();

        new Thread(() -> {
            Phone.sendSMS();
        }, "t2").start();

        new Thread(new Phone(), "t3").start();
        new Thread(new Phone(), "t4").start();
    }

}
