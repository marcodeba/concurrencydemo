package com.demo.javademo.dataStructure.string;

public class KMPAlgorithm {
    public static void main(String[] args) {
        System.out.println(kmpMatch("abaabaabbabaaabaabbabaab", "abaabbabaab"));
//        int[] arr = getNext("abaabcac".toCharArray());
//        System.out.println(java.util.Arrays.toString(arr));
    }

    // 字符组成的这个子串最长的相同前缀后缀的长度数组
    public static int[] getNext(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        next[1] = 0;
        int k;
        for (int i = 2; i < t.length; i++) {
            k = next[i - 1];
            while (k != -1) {
                if (t[i - 1] == t[k]) {
                    next[i] = k + 1;
                    break;
                } else {
                    k = next[k];
                    next[i] = 0;
                }
            }
        }
        return next;
    }

    // i不回退
    public static int kmpMatch(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        int[] next = getNext(tArray);
        int i = 0, j = 0;
        while (i < sArray.length && j < tArray.length) {
            if (j == -1 || (sArray[i] == tArray[j])) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return (j == tArray.length) ? (i - j) : -1;
    }
}
