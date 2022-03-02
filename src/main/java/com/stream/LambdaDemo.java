package com.stream;

import java.util.Arrays;

public class LambdaDemo {
    // lambda 的是用于替换只有一个抽象方法的接口 - 即函数式接口
    // 使用 lambda 可以避免构造匿名内部类的复杂语法
    private void startThread1() {
        // 匿名内部类
        Runnable task = new Runnable() {
            @Override
            public void run() { // 覆盖重写抽象方法
                System.out.println("多线程任务执行!");
            } };
        new Thread(task).start(); // 启动线程
    }

    private void startThread2() {
        new Thread(() -> System.out.println("多线程任务执行!")).start();
    }

    /**
     * Lambda表达式的标准格式为: (参数类型 参数名称) ‐> { 代码语句 }
     */
    static interface Cook {
        void makeFood();
    }

    private void invokeCook(Cook cook) {
        cook.makeFood();
    }

    private void noParamNoReturn() {
        invokeCook(() -> System.out.println("cook steak"));
    }

    private void paramWithReturn(Integer[] A) {
        Arrays.sort(A, (Integer a, Integer b) -> {return a - b;});
    }

    /* 省略规则:
        1. 小括号内参数的类型可以省略;
        2. 如果小括号内有且仅有一个参，则小括号可以省略;
        3. 如果大括号内有且仅有一个语句，则无论是否有返回值，都可以省略大括号、return关键字及语句分号。
     */
    private void paraWithReturn1(Integer[] A) {
        Arrays.sort(A, (a, b) -> {return a - b;});
    }

    private void paraWithReturn3(Integer[] A) {
        Arrays.sort(A, (a, b) -> a - b);
    }

    private void paraWithReturn2(Integer[] A) {
       Arrays.stream(A).forEach(num -> System.out.println(num));
    }

    // Method references
    // 引用方法的参数必须与函数式接口相同
    private void paraWithReturnMethodReference(Integer[] A) {
        Arrays.stream(A).forEach(System.out::println);
    }
}
