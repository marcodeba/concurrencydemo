package com.demo.javademo.dataStructure.search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fibonacci Search 斐波那契查找，利用黄金分割原理实现
 *  
 * 算法复杂度 O(logn)
 *
 * 二分查找分割方式 middle = (low+high)/2  加法和除法
 * 插值查找分割方式 middle = low + (high-low)*(key-a[low])/(a[high]-a[low]) 加减乘除都用上了
 * 斐波那契查找分割方式 middle = low + Fibonacci[index - 1] - 1
 *
 * Fibonacci Search examines relatively closer elements in subsequent steps.
 * So when input array is big that cannot fit in CPU cache or even in RAM, Fibonacci Search can be useful.
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] a = new int[]{ 1, 5, 15, 22, 25, 31, 39, 42, 47, 49, 59, 68, 88, 88};
        System.out.println("待查找数组 a：" + Arrays.toString(a));
        System.out.println("待查找数组长度为：" + a.length);
        int key = 59;
        System.out.println("寻找的key为：" + key);
        System.out.println("结果在数组 a 角标的 [" + fibonacciSearch(key, a) + "] 位");
    }

    public static int fibonacciSearch(int key, int[] a) {
        //斐波那契数列中的值-1 
        int fibo = 0;
        //斐波那契数列中的角标值 
        int index = 0;
        //用于展示斐波那契数列 
        ArrayList<Integer> fibonacciArray = new ArrayList();
        //计算length位于斐波那契数列的位置 
        while (a.length > fibo) {
            fibo = getFibonacci(index);
            fibonacciArray.add(fibo);
            index++;
        }
        //要找到在斐波那契数列中大于待查数组长度值的最小值的角标，或者等于待查数组长度值的角标
        index -= 1;

        //用于展示 
        System.out.println("斐波那契数列为：" + fibonacciArray.toString());
        System.out.println();

        //定义临时数组来扩展待查数组的长度，长度为 fibo 
        int[] temp = new int[fibo - 1];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
        }

        for (int i = a.length; i < temp.length; i++) {
            temp[i] = a[a.length - 1];
        }
        System.out.println("补充后的数组 temp 为：" + Arrays.toString(temp));
        System.out.println("补充后的数组长度为：" + temp.length);

        //初始化记录首位 末位 middle非字面意义上的中间值，仅是将数组分割为两部分
        int low = 0;
        int high = a.length - 1;
        int middle;
        while (low <= high) {
            //计算分割数组处的角标
            middle = low + fibonacciArray.get(index - 1) - 1;
            System.out.println("斐波那契角标 =  " + index + "   将key与数组的角标 [" + (middle) + "] 所在的值做比较");
            if (key < temp[middle]) {
                high = middle - 1;
                index -= 1;
            } else if (key > temp[middle]) {
                low = middle + 1;
                index -= 2;
            } else {
                //如果值相等且角标小于或等于待查数组的最大角标 返回middle 表示找到 
                if (middle <= a.length - 1) {
                    return middle;
                } else {
                    //如果值相等但角标大于待查数组的最大角标 
                    //这样表示在与temp数组比较时，比较的角标超过了待查数组的角标 
                    //但在补充temp数组时，后面的值都是待查数组的最后一位值 
                    //所以所寻找的key正好是待查找数组的最后一位 
                    System.out.println("所寻找的key正好是待查找数组的最后一位");
                    return a.length - 1;
                }
            }
        }
        return 0;
    }

    //生成斐波那契数
    public static int getFibonacci(int k) {
        if (k == 0) {
            return 0;
        } else if (k == 1) {
            return 1;
        } else if (k > 1) {
            return getFibonacci(k - 1) + getFibonacci(k - 2);
        } else {
            return -1;
        }
    }
}