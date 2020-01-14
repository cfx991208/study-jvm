package com.example.study.thread.jvm.gc;

/**
 * @author: 飞羽
 * @description:
 * @date: 2020/1/14 13:11
 *
 * jps -l
 * jinfo -flag/-flags
 */
public class HelloGC {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("************Hello GC!");
        Thread.sleep(Integer.MAX_VALUE);
    }

}
