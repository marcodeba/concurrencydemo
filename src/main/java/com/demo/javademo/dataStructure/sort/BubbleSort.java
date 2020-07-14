package com.demo.javademo.dataStructure.sort;

import java.util.Arrays;

public class BubbleSort {
//    /**
//     * 本次比较后续数据已经有序，但是还会执行后续比较
//     * @param array
//     */
//    private static void sort(int[] array) {
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array.length - i - 1; j++) {
//                if (array[j] > array[j + 1]) {
//                    int temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
//                }
//            }
//        }
//    }

//    /**
//     * 缺点：数据本身已经有序，但是还会执行后续比较
//     * @param array
//     */
//    private static void sort(int[] array) {
//        for (int i = 0; i < array.length; i++) {
//            boolean isSorted = true;
//            for (int j = 0; j < array.length - i - 1; j++) {
//                if (array[j] > array[j + 1]) {
//                    int temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
//                    isSorted = false;
//                }
//            }
//            if (isSorted) break;
//        }
//    }

    private static void sort(int[] array) {
        int lastExchangeIndex = 0;
        int soredBoarder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < soredBoarder; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }
            soredBoarder = lastExchangeIndex;
            if (isSorted) break;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
