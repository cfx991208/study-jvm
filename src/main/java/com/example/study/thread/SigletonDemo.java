package com.example.study.thread;

public class SigletonDemo {

    private static volatile SigletonDemo instance = null;

    private SigletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法");
    }

    /**
     * DCL模式（Double Check Lock双端检索机制）
     *  该模式有极小的概率出现多次初始化问题
     *      创建对象过程：分配空间 -> 初始化 -> 引用地址，这三个步骤可能会被指令重排，导致顺序错乱，从而引发bug
     *      解决方式：加volatile
      */
    public static SigletonDemo getInstance(){
        if (null == instance){
            synchronized (SigletonDemo.class){
                if (null == instance){
                    instance = new SigletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SigletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }

}
