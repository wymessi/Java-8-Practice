package com.wymessi.functionalInterface;

/**
 * Created by wy on 2019/1/28
 * 自定义函数式接口，根据参数计算出结果
 * 函数式接口：只有一个抽象方法的接口
 */
@FunctionalInterface
public interface Computor {
    int compute(int a, int b);

    //@FunctionalInterface 保证该接口一定位函数式接口，超过一个抽象方法就会编译失败
    //int substarct(int a, int b);
}
