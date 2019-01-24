package com.jvm.memory.distribution;

public class JavaVMOOM {

    private void dontStop() {
        while (true) {

        }
    }

    private void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    dontStop();
                }
            };
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMOOM oom = new JavaVMOOM();
        oom.stackLeakByThread();
    }
}
