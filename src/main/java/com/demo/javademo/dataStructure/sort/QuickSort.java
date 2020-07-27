package com.demo.javademo.dataStructure.sort;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] array, int start, int end) {
        if (array == null)
            throw new IllegalArgumentException("参数错误");

        if (start >= end) return;

        int pivotIndex = partition(array, start, end);
        quickSort(array, start, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivotValue = array[start];
        int mark = start;

        for (int i = start + 1; i <= end; i++) {
            if (array[i] < pivotValue) {
                mark++;
                int temp = array[mark];
                array[mark] = array[i];
                array[i] = temp;
            }
        }
        array[start] = array[mark];
        array[mark] = pivotValue;

        return mark;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
