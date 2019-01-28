package com.wymessi.functionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by wy on 2019/1/28
 * 用于学习记录java8中的主要常用函数式接口及自定义函数式接口
 */
public class FunctionalInterfaceTest {
    public static void main(String[] args) {

        //测试自定义函数式接口
        int addResult = compute(1, 2, (a, b) -> a + b);
        int subResult = compute(1, 2, (a, b) -> a - b);
        int supplyResult = compute(1, 2, (a, b) -> a * b);
        System.out.println(addResult); //打印出来3
        System.out.println(subResult); //打印出来-1
        System.out.println(supplyResult); //打印出来2

        List<String> names = Arrays.asList("Tom", "Jake", "Bruce Lee");

        //测试Predicate<T>接口
        List<String> validNames = getValidStr(names, (s) -> s.length() > 3);
        System.out.println(validNames); // ["Jake", "Bruce Lee"]


        //测试Consumer<T>接口,这里仅仅是简单地打印出来，实际业务中可以做很多操作
        print(names, (s) -> System.out.println("^_^ " + s));

        List<String> numberStrs = Arrays.asList("1", "2", "3");

        //测试Consumer<T>
        List<Integer> numbers = transfer(numberStrs, (s) -> Integer.valueOf(s));
        System.out.println(numbers);

    }

    //利用自定义的计算器接口计算结果
    public static int compute(int a, int b, Computor computor) {
        return computor.compute(a, b);
    }

    //利用Predicate<T> 接受一个类型，返回一个布尔值
    public static List<String> getValidStr(List<String> names, Predicate<String> predicate) {
        List<String> validsNames = new ArrayList<>();
        for (String name : names) {
            if (predicate.test(name)) {
                validsNames.add(name);
            }
        }
        return validsNames;
    }

    //Consumer<T> 接受一个类型，对其进行操作
    public static void print(List<String> names, Consumer<String> consumer) {
        for (String name : names) {
            consumer.accept(name);
        }
    }

    //利用Function<T,R> 接受一个类型，返回一个布尔值
    public static List<Integer> transfer(List<String> names, Function<String, Integer> function) {
        List<Integer> numbers = new ArrayList<>();
        for (String name : names) {
            numbers.add(function.apply(name));
        }
        return numbers;
    }
}
