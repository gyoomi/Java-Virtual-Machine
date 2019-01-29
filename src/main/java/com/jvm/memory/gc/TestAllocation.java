/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.jvm.memory.gc;

/**
 * VM args：-verbose:gc -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 *
 * @author Leon
 * @version 2019/1/29 9:39
 */
public class TestAllocation {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[4 * _1MB];
        // 至少发生了MinorGC
    }
}
