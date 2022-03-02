package com.stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 这里的 filter 、 map 、 skip 都是在对函数模型进行操作，集合元素并没有真正被处理。只有当终结方法 count 执行的时候，整个模型才会按照指定策略执行操作。而这得益于Lambda的延迟执行特性。
 */
public class StreamDemo {
    private void getStream() {
        List<String> list = new ArrayList<>();
        Stream<String> listStream = list.stream();

        int[] A = new int[] {1, 2, 3};
        IntStream intStream = Arrays.stream(A);

        String[] strs = {"abc", "def"};
        Stream<String> strStream = Stream.of(strs);

        Map<String, String> map = new HashMap<>();
        Stream<String> keyStream = map.keySet().stream();
        Stream<String> valueStream = map.values().stream();
        Stream<Map.Entry<String,String>> entryStream = map.entrySet().stream();
    }

    //延迟方法:返回值类型仍然是 Stream 接口自身类型的方法
    //终结方法: 返回值类型不再是 Stream 接口自身类型的方法 如:count 和 foreach
    private void filterDemo(String[] strings) {
        Predicate<String> isEmpty = str -> str == null || str.length() == 0;
        Arrays.stream(strings).filter(isEmpty);
    }

    //map将流中元素映射到另一个流中
    //collect 将流转换成一个数据结构
    private void mapCollectDemo(String[] strings) {
        List<Integer> list = Arrays.stream(strings).map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * map vs flatMap
     * 有二箱鸡蛋，每箱5个，现在要把鸡蛋加工成煎蛋，然后分给学生。
     *
     * map做的事情：把二箱鸡蛋分别加工成煎蛋，还是放成原来的两箱；
     *
     * flatMap做的事情：把二箱鸡蛋分别加工成煎蛋，然后放到一起【10个煎蛋】；
     */
    private void flatMapDemo(List<List<String>> stringlists) {
        stringlists.stream().map(
                list -> list.stream().map(str -> str+"@").collect(Collectors.toList())
        ).forEach(
                list -> list.stream().forEach(System.out::println)
        );

        stringlists.stream().flatMap(
                list -> list.stream().map(str -> str+"@")
        ).forEach(System.out::println);
    }

    private void concatDemo() {
        Stream<String> streamA = Stream.of("张无忌");
        Stream<String> streamB = Stream.of("张翠山");
        Stream<String> result = Stream.concat(streamA, streamB);
    }

    //Pagination: limit(num), skip(num),
    private void paginationDemo() {
        Stream<String> original = Stream.of("张无忌", "张三丰", "周芷若");

        Stream<String> result = original.skip(1).limit(2); //"张三丰", "周芷若"
        System.out.println(result.count()); // 2
    }

    //distinct(): 通过流中元素的 hashCode() 和 equals() 去除重复元素

    //sorted() and sorted(comparator)

    //终结函数:findFirst() == get first 用于判空, findAny() 用于 parallel, max(), min(), count()

    //终结函数2: allMatch, nonMatch, anyMatch -> 返回 boolean, 接收 Predicate

    //二维数组的遍历
}
