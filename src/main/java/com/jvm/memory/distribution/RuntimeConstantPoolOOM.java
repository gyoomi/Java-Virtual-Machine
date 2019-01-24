package com.jvm.memory.distribution;

import java.util.ArrayList;
import java.util.List;

/**
 * VM ags: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @author Leon
 * @version 2019/1/24 16:58
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
