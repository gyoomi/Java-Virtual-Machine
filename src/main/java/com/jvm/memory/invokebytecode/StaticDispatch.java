package com.jvm.memory.invokebytecode;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/23 22:14
 */
public class StaticDispatch {

    public static void main(String[] args) throws Exception {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }

    static class Human {}

    static class Man extends Human {}

    static class Woman extends Human {}

    public static void sayHello(Human guy) {
        System.out.println("hello guy");
    }

    public static void sayHello(Man guy) {
        System.out.println("hello gentlemen");
    }

    public static void sayHello(Woman guy) {
        System.out.println("hello lady");
    }
}
