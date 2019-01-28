package com.wymessi.lambda;

import com.wymessi.functionalInterface.Computor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

        Apple a1 = new Apple("红色",1.2);
        Apple a2 = new Apple("绿色",1.6);
        Apple a3 = new Apple("黑色",1.4);

        List<Apple> apples = Arrays.asList(a1, a2, a3);
        apples.sort(byWeight);
        apples.forEach((a) -> System.out.println(a.getColor() + "->" + a.getWeight()));


    }

    /**
     * 通过匿名内部类得到比较器
     * @param a1
     * @param a2
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

    /**
     * 通过lambda得到比较器
     * @param a1
     * @param a2
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
        return (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

    }

}
