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
 * @version 2019/1/25 17:40
 */
public class FinalizerEscapeGC {

    public static FinalizerEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive!!!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalizer method execute");
        FinalizerEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception {
        SAVE_HOOK = new FinalizerEscapeGC();
        // 对象第一次进行成功的拯救
        SAVE_HOOK = null;
        System.gc();
        // 因为finalizer方法的优先级很低，所以暂停0.5s,以等待他运行
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead.");
        }

        // 下面的代码和上面的代码一样。但是对象却自救失败了。
        // 对象第二次进行成功的拯救
        SAVE_HOOK = null;
        System.gc();
        // 因为finalizer方法的优先级很低，所以暂停0.5s,以等待他运行
        Thread.sleep(600);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead.");
        }
    }
}
