/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.jvm.memory.gc;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/1/25 14:57
 */
public class ReferenceCountGC {

    public Object instance = null;

    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        ReferenceCountGC referenceCountGCA = new ReferenceCountGC();
        ReferenceCountGC referenceCountGCB = new ReferenceCountGC();
        referenceCountGCA.instance = referenceCountGCB;
        referenceCountGCB.instance = referenceCountGCA;

        referenceCountGCA = null;
        referenceCountGCB = null;

        System.gc();
    }
}
