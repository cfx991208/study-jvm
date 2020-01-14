package com.example.study.juc;

class Numbers{

    private int num = 0;

    public synchronized void add() throws InterruptedException {
        while (num == 1){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + num);
        this.notifyAll();
    }

    public synchronized void jian() throws InterruptedException {
        while (num == 0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + num);
        this.notifyAll();
    }

}

/**
 *
 *
 * synchronized关键字版
 */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {

        Numbers numbers = new Numbers();
        // 添加
        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    numbers.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AAA添加：").start();

        // 减少
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    numbers.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA减少：").start();



        // 添加
        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(400);
                    numbers.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BBB添加：").start();

        // 减少
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    numbers.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB减少：").start();
    }

}
