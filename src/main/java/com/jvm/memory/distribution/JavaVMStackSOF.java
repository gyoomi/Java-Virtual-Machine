/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.jvm.memory.distribution;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/1/24 16:58
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Exception {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Exception e) {
            System.out.println("stackLength = " + oom.stackLength);
            throw e;
        }
    }
}
