package com.jvm.memory.invokebytecode;

/**
 * 参数：-verbose:gc
 *
 * @author Leon
 * @version 2019/2/23 11:54
 */
public class SlotClassDemo {

    public static void main(String[] args) throws Exception {
        {
            byte[] placeholder = new byte[64 * 1024 * 1204];
        }
        int a = 0;
        System.gc();
    }
}
