package com.stream;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CommonFunctionalInterfaces {
    //Supplier - used for get data
    private Integer getResult(Supplier<Integer> supplier) {
        //T get()
        return supplier.get();
    }

    private void supplierDemo() {
        int a = 5;
        int b = -1;
        System.out.println("Bigger one " + getResult(() -> Math.max(a,b)));
        System.out.println("Smaller one " + getResult(() -> Math.min(a,b)));
    }

    //Consumer - used for consume data
    private void printInfo(Consumer<String> one, Consumer<String> two, String[] array) {
        for (String info : array) {
            // default Consumer<T> andThen(Consumer<? super T> after)
            // void accept(T t)
            one.andThen(two).accept(info);
        }
    }

    public void consumerDemo() {
        String[] array = { "迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男" };
        printInfo(s -> System.out.print("姓名:" + s.split(",")[0]),
                s -> System.out.println("。性别:" + s.split(",")[1] + "。"), array);
    }

    //Predicate - judge
    private void filter(Predicate<String> one, Predicate<String> two) {
        // Predicate<T> and(Predicate<? super T> other)
        // Predicate<T> or(Predicate<? super T> other)
        // Predicate<T> negate(Predicate<? super T> other)
        boolean isValid = one.or(two).test("Hello World");
        System.out.println(isValid);
    }

    private void predicateDemo() {
        filter(s -> s.contains("H"), s -> s.contains("W"));
    }

    //Function<T, R> - transfer
    private void transfer(Function<String, Integer> one, Function<Integer, Integer> two) {
        //R apply(T t)
        int num = one.andThen(two).apply("10");
        System.out.println(num);
    }

    private void functionDemo() {
        transfer(s -> Integer.parseInt(s), num -> num + 20);
    }
}
