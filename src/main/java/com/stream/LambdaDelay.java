package com.stream;

public class LambdaDelay {
    @FunctionalInterface
    public interface MessageBuilder {
        String buildMessage();
    }

    private static void log(int level, MessageBuilder builder) {
        if (level >= 2) {
            System.out.println(builder.buildMessage());
        }
    }

    //而Lambda表达式是延迟执行的，这正好可以 作为解决方案，提升性能。
    public static void main(String[] args) {
        String msgA = "Hello";
        String msgB = "World";
        String msgC = "Java";
        log(2, () -> {
            System.out.println("Lambda执行!");
            return msgA + msgB + msgC;
        });
    }
}
