package com.example.study.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 获取锁：" + lockA + "尝试获取锁：" + lockB);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 获取锁：" + lockB + "尝试获取锁：" + lockA);
            }
        }
    }
}

/**
 * 手写一个死锁并定位进行分析
 *  jps 定位进程号
 *  jstack 进程id 查看进程详情
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "AAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "BBB").start();
    }

}
