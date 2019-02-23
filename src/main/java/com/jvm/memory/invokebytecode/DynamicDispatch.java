package com.jvm.memory.invokebytecode;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/23 22:41
 */
public class DynamicDispatch {

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
    }

    static abstract class Human {
        abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        void sayHello() {
            System.out.println("man sayHello");
        }
    }

    static class Woman extends Human {
        @Override
        void sayHello() {
            System.out.println("woman sayHello");
        }
    }
}
