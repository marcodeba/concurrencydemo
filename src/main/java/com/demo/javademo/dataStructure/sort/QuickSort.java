package com.demo.javademo.dataStructure.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class QuickSort {
    /**
     * 递归算法
     * @param array
     * @param start
     * @param end
     */
//    public static void quickSort(int[] array, int start, int end) {
//        if (array == null)
//            throw new IllegalArgumentException("参数错误");
//        if (start >= end) return;
//
//        int pivotIndex = partition(array, start, end);
//        quickSort(array, start, pivotIndex - 1);
//        quickSort(array, pivotIndex + 1, end);
//    }

    /**
     * 非递归算法
     *
     * @param array
     * @param start
     * @param end
     */
    public static void quickSort(int[] array, int start, int end) {
        Stack<Map<String, Integer>> quickSortStack = new Stack<>();
        Map<String, Integer> rootParam = new HashMap<>();
        rootParam.put("start", start);
        rootParam.put("end", end);
        quickSortStack.push(rootParam);

        while (!quickSortStack.isEmpty()) {
            Map<String, Integer> param = quickSortStack.pop();
            int pivotIndex = partition(array, param.get("start"), param.get("end"));
            if (param.get("start") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("start", param.get("start"));
                leftParam.put("end", pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if (param.get("end") > pivotIndex + 1) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("start", pivotIndex + 1);
                rightParam.put("end", param.get("end"));
                quickSortStack.push(rightParam);
            }
        }
    }

    private static int partition(int[] array, int start, int end) {
        int pivotValue = array[start];
        // 小于基准元素的区域边界
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
