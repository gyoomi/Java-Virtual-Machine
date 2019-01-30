/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.jvm.memory.gc;

/**
 * VM args：-verbose:gc -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
 *
 * @author Leon
 * @version 2019/1/30 10:47
 */
public class TestHandlePromotion {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        byte[] a1, a2, a3, a4, a5, a6, a7;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        a1 = null;
        a4 = new byte[2 * _1MB];
        a5 = new byte[2 * _1MB];
        a6 = new byte[2 * _1MB];
        a4 = null;
        a5 = null;
        a6 = null;
        a7 = new byte[2 * _1MB];
    }
}
