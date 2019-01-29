package com.jvm.memory.gc;


/**
 * VM args：-verbose:gc -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 *          -XX:MaxTenuringThreshold=1 -XX:+UseSerialGC
 * @author Leon
 * @version 2019/1/29 11:47
 */
public class TestDynamicTenuringshold {

    private final static int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allcation1, allcation2, allcation3, allcation4;
        // 什么时候进入老年代取决于MaxTenuringThreshold的值
        allcation1 = new byte[_1MB / 4];
        allcation2 = new byte[_1MB / 4];
        allcation3 = new byte[4 * _1MB];
        allcation4 = new byte[4 * _1MB];
        allcation4 = null;
        allcation4 = new byte[4 * _1MB];

    }
}
