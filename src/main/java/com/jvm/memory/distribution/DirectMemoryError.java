package com.jvm.memory.distribution;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM ags: -Xms20M -XX:MaxDirectMemorySize=10M
 *
 * @author Leon
 * @version 2019/1/24 16:58
 */
public class DirectMemoryError {

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }
}
