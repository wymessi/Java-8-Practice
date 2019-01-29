package com.wymessi.lambda;


import java.util.Arrays;
import static java.util.Comparator.*;

import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.*;

/**
 * Created by wy on 2019/1/27
 *
 * Lambda的基本语法
 * 1. (parameters) -> expression 如果是有返回值的表达式，不需要显示声明return，已经隐含了return
 * 2. (parameters) -> {statements;}  如果是有返回值的语句，必须加return ，否则编译不通过
 *
 */
public class LambdaGrammar {
    public static void main(String[] args) {
        //得到根据苹果重量的比较器
        Comparator<Apple> byWeight = getByLambda();

        Apple a1 = new Apple("红色",1.8);
        Apple a2 = new Apple("绿色",1.6);
        Apple a3 = new Apple("黑色",1.4);

        List<Apple> apples = Arrays.asList(a1, a2, a3);
        apples.sort(byWeight);
        apples.forEach((a) -> System.out.println(a.getColor() + "->" + a.getWeight()));


        //利用java8 新特性对集合集合进行排序完整简写示例
        System.out.println("一般模式：");
        apples.sort(comparing(Apple::getWeight));
        apples.forEach((a) -> System.out.println(a.getColor() + "->" + a.getWeight()));

        //Comparator还提供了比较特定类型的方法，上面苹果重量类型为Double,所以可以写为下面这种形式
        System.out.println("Comparator具体类型模式：");
        apples.sort(comparingDouble(Apple::getWeight));
        apples.forEach((a) -> System.out.println(a.getColor() + "->" + a.getWeight()));

        //逆序
        System.out.println("Comparator具体类型模式：逆序");
        apples.sort(comparingDouble(Apple::getWeight).reversed());
        apples.forEach((a) -> System.out.println(a.getColor() + "->" + a.getWeight()));

        //流初步接触，按重量排序，取颜色集合返回，后面会具体学习介绍
        System.out.println("流初探：");
        List<String> colors = apples.stream()
                .sorted(comparing(Apple::getWeight))
                .map(Apple::getColor)
                .collect(toList());
        colors.forEach(System.out::println);
    }

    /**
     * 通过lambda得到比较器
     * @return
     */
    public static Comparator<Apple> getByLambda(){

        //compile fail
        // 提示缺少return 语句，因为打了中括号，表达式又有返回值，必须显示return
        /*return (Apple a1, Apple a2) -> {
            a1.getWeight().compareTo(a2.getWeight());
        };*/

        //compile pass
        // 完整lambda语法,显示return
        /*return (Apple a1, Apple a2) -> {
            return a1.getWeight().compareTo(a2.getWeight());
        };*/
        //compile pass
        //由于只有一条语句，可以省略return语句和花括号
        //return (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        //compile pass
        //由于lambda会根据上下文进行类型推断，所以可以省略具体类型
        //return (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

        //采用方法引用替代lambda表达式 Comparator.comparing(Function<? super T, ? extends U>>)
        return comparing(Apple::getWeight);
    }


    /**
     * 通过匿名内部类得到比较器
     * @return
     */
    public static Comparator<Apple> getByAnonymousClass(){
        return new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                //return o1.getWeight() == o2.getWeight() ? 0 : (o1.getWeight() > o2.getWeight() ? -1 : 1);
                return o1.getWeight().compareTo(o2.getWeight());
            }
        };
    }
}
