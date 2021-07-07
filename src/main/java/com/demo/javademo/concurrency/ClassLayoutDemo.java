package com.demo.javademo.concurrency;

import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutDemo {
    public static void main(String[] args) {
        ClassLayoutDemo classLayoutDemo = new ClassLayoutDemo();
        synchronized (classLayoutDemo) {
            System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
        }
/**
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           e0 09 1c 0d (11100000 00001001 00011100 00001101) (219941344)
 *       4     4        (object header)                           00 70 00 00 (00000000 01110000 00000000 00000000) (28672)
 *       8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
 *      12     4        (loss due to the next object alignment)
 */
    }
}
