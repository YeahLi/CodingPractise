package com.stream;

public class FunctionalInterfaceDemo {

    // 函数式接口在 java 中是指: 有且仅有一个抽象方法的接口
    @FunctionalInterface
    interface MyFunctionalInterface {
        void myMethod();
    }

    private static void doSomething(MyFunctionalInterface inner) {
        inner.myMethod();
    }

    public static void main(String[] args) {
        //典型的使用场景就是作为方法的参数
        doSomething(()-> System.out.println("Lambda is executing"));
    }
}
