package com.stream;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaUseCase {
    /**
     * Java中的Lambda表达式可以被当作是匿名内部类的替代品。如果方法的参数是一个函数 式接口类型，那么就可以使用Lambda表达式进行替代。使用Lambda表达式作为方法参数，其实就是使用函数式 接口作为方法参数。
     */

    private void lambdaAsParam() {
        new Thread(() -> System.out.println("Thread is running")).start();
    }

    private Comparator<String> newComparator() {
        return (a, b) -> b.length() - a.length();
    }

    private void sort(String A[]) {
        Arrays.sort(A, newComparator());
    }
}
