/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.jvm.memory.gc;

/**
 * VM args：-verbose:gc -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 *          -XX:PretenureSizeThreshold=3M -XX:+UseSerialGC
 * @author Leon
 * @version 2019/1/29 11:47
 */
public class TestPretenureThreshold {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
        // 直接分配在老年代
    }
}
