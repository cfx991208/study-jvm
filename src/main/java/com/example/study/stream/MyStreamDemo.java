package com.example.study.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    private Integer id;
    private String userName;
    private Integer age;
}

/**
 *  题目：
 *      请按照给出数据，找出同时满足以下条件的用户；
 *      偶数id且年龄大于24且用户名转为大写且用户名字字母倒叙排列
 *      只输出一个名字
 */
public class MyStreamDemo {

    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream().
                filter(user -> { return user.getId() % 2 == 0; }).
                filter(user -> { return user.getAge() > 24; }).
                map(user -> {return user.getUserName().toUpperCase();})
//                .sorted((o1, o2) -> {return o2.compareTo(o1);})
                .sorted()
                .limit(1).forEach(System.out::println);
    }

    /**
     * Java内置核心四大函数式接口
     */
    private static void functionDemo() {
        // 供给型接口，不用参数，返回T类型的变量
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return null;
//            }
//        };
        Supplier<String> supplier = () -> { return "二狗子"; };
        System.out.println(supplier.get());

        // 消费型接口，输入T类型的参数，不返回值
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//
//            }
//        };
        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("你好，二狗子");

        // 函数型接口，输入T类型的参数，返回R类型的结果
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return null;
//            }
//        };
        Function<String, Integer> function = s -> { return s.length(); };
        System.out.println(function.apply("二狗子的长度"));

        // 断定型接口，输入T类型参数，返回boolean类型的结果
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return false;
//            }
//        };
        Predicate<String> predicate = s -> {return s.isEmpty(); };
        System.out.println(predicate.test("二狗子是不是空"));
    }

}
