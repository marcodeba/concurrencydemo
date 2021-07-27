package com.demo.javademo.algorithm.fenzhi;

public class PowOf2 {
    public static void main(String[] args) {
        System.out.println(pow2(4));
    }

    private static String pow2(int n) {
        StringBuilder res = new StringBuilder("1");
        // 重复N次
        for (int i = 0; i < n; i++) {
            // 进位标志，每轮清零
            int flag = 0;
            // result中的字符，从前往后逐位*2
            for (int j = res.length() - 1; j >= 0; j--) {
                // 乘法运算,需要加上进位
                flag = ((res.charAt(j) - '0') << 1) + flag / 10;
                // 替换此位结果
                res.setCharAt(j, (char) (flag % 10 + '0'));
            }
            // 产生进位则需添加新的数字
            if (flag / 10 >= 1)
                res.insert(0, '1');
        }
        return res.toString();
    }
}
