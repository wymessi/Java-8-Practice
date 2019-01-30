package com.wymessi.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by wy on 2019/1/30
 *
 *  流是“从支持数据处理操作的源生成的一系列元素”。
 *  流利用内部迭代：迭代通过filter、 map、 sorted等操作被抽象掉了。
 *  流操作有两类：中间操作和终端操作。
 *  filter和map等中间操作会返回一个流，并可以链接在一起。可以用它们来设置一条流水线，但并不会生成任何结果。
 *  forEach和count等终端操作会返回一个非流的值，并处理流水线以返回结果。
 *  流中的元素是按需计算的。
 *
 * 总而言之，流的使用一般包括三件事：
 *  一个数据源（如集合）来执行一个查询；
 *  一个中间操作链，形成一条流的流水线；
 *  一个终端操作，执行流水线，并能生成结果。
 */

public class StreamTest {
    //初始化数据，便于测试
    private static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 1450, Dish.Type.FISH) );
    
    
    public static void main(String[] args) {
        System.out.println("======选出热量高于300卡路里的前三个菜肴的名称======");
        // 选出热量高于300卡路里的前三个菜肴的名称
        List<String> highCaloriesLimit = menu.stream()
                .filter((d) -> d.getCalories() > 300) //筛选出卡路里大于300的
                .map(Dish::getName) // 得到菜肴名
                .limit(3) // 只选择头三个
                .collect(toList()); // 将结果保存在另一个集合中
        System.out.println(highCaloriesLimit); //打印出[pork, beef, chicken]

        System.out.println("======测试流只能被消费一次======");
        //请注意，和迭代器类似，流只能遍历一次。遍历完之后，我们就说这个流已经被消费掉了
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        //s.forEach(System.out::println); // 抛异常 java.lang.IllegalStateException: stream has already been operated upon or closed

        System.out.println("======观察内部迭代具体流程======");
        //观察内部具体流程
        List<String> highCaloriesLimitDeatil = menu.stream()
                .filter((d) -> {
                    System.out.println("filtering: " + d.getName());
                    return d.getCalories() > 300;
                })
                .map((d) -> {
                    System.out.println("mapping: " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());

        System.out.println("======筛选出素食======");
        //筛选出素食
        menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList()).forEach(System.out::println);

        System.out.println("======筛选出各异的元素======");
        //筛选出各异的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println("======跳过元素======");
        //筛选出各异的元素
        //跳过元素
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2) //跳过前两个元素
                .collect(toList());
        System.out.println(dishes); //打印出[chicken, french fries, rice, pizza, salmon]

        // 需求背景：给定单词列表["Hello","World"]，返回列表["H","e","l", "o","W","r","d"]。
        System.out.println("======map======");
        //Arrays.stream()的方法可以接受一个数组并产生一个流，例如：
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        //如果不使用flatMap,返回的是List<Stream<String>>
        List<String> words = Arrays.asList("Goodbye", "World");
        List<Stream<String>> mapWords = words.stream()
                //将每个单词转换为由其字母构成的数组
                .map(word -> word.split(""))
                //让每个数组变成一个单独的流
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(mapWords);//打印出[java.util.stream.ReferencePipeline$Head@1d81eb93, java.util.stream.ReferencePipeline$Head@7291c18f]

        System.out.println("======flatMap======");
        List<String> flatMapWords =
                words.stream()
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList());
        System.out.println(flatMapWords); //打印出[G, o, d, b, y, e, W, r, l]


        //查找
        boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println(isHealthy);
        boolean isHealthy1 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealthy1);
        boolean notHealthy = menu.stream().anyMatch(d -> d.getCalories() >= 1000);
        System.out.println(notHealthy);

        //查找任意一个
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        //查找第一个
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst() // 9
                        .ifPresent(System.out::println);
    }
}
