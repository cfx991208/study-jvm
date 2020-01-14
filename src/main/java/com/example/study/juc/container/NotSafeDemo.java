package com.example.study.juc.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * 题目：请举例集合类是不安全的
 */
public class NotSafeDemo {

    public static void main(String[] args) {
        mapNotSafe();
    }

    /**
     * map容器解决方法，同样类似于list容器
     */
    private static void mapNotSafe() {
        Map<String, String> map  = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }).start();
        }
    }

    /**
     * list容器解决方法:
     *  1.使用线程安全的Vector(但是效率较慢)
     *  2.使用Collections.synchronizedList(new ArrayList<>()方法将ArrayList转换为线程安全的容器(常用)
     *  3.使用读写分离的CopyOnWriteArrayList<>()(逼格较高，面试时装逼用)
     */
    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();//new Vector<>();//Collections.synchronizedList(new ArrayList<>());//new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    /**
     * Set容器与List容器解决方式类似
     * HashSet底层调用的HashMap，但是只是把值当作HashMap的key放进去了，value是固定的一个泛型对象（private static final Object PRESENT = new Object(); ）
     */
    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

}
