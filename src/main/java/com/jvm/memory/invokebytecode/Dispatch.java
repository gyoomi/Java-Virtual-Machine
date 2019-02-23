package com.jvm.memory.invokebytecode;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/23 23:04
 */
public class Dispatch {

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }

    static class QQ {}

    static class _360 {}

    public static class Father {
        public void hardChoice(QQ qq) {
            System.out.println("father hard choose qq");
        }

        public void hardChoice(_360 _360) {
            System.out.println("father hard choose 360");
        }
    }

    public static class Son extends Father {

        @Override
        public void hardChoice(QQ qq) {
            System.out.println("son hard choose qq");
        }

        @Override
        public void hardChoice(_360 _360) {
            System.out.println("son hard choose 360");
        }
    }
}
