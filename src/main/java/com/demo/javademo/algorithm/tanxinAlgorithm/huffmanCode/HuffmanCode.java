package com.demo.javademo.algorithm.tanxinAlgorithm.huffmanCode;

import java.util.Scanner;

// 哈夫曼编码
public class HuffmanCode {
    //初始化一个huffuman树
    public static void initHuffmanTree(HaffmanNode[] huffmanTree, int m) {
        for (int i = 0; i < m; i++) {
            huffmanTree[i] = new HaffmanNode(0, -1, -1, -1);
        }
    }

    //初始化一个huffmanCode
    public static void initHuffmanCode(HuffmanNodeCode[] huffmanCode, int n) {
        for (int i = 0; i < n; i++) {
            huffmanCode[i] = new HuffmanNodeCode("", "");
        }
    }

    //获取huffmanCode的符号
    public static void getHuffmanCode(HuffmanNodeCode[] huffmanCode, int n) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            String character = input.next();
            huffmanCode[i] = new HuffmanNodeCode(character, "");
        }
    }

    //获取huffman树节点频数
    public static void getHuffmanWeight(HaffmanNode[] huffmanTree, int n) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            int weight = input.nextInt();
            huffmanTree[i] = new HaffmanNode(weight, -1, -1, -1);
        }
    }

    //从n个结点中选取最小的两个结点
    public static int[] selectMin(HaffmanNode[] huffmanTree, int n) {
        int min[] = new int[2];
        class TempNode {
            //存储权重
            int newWeight;
            //存储该结点所在的位置
            int place;

            TempNode(int newWeight, int place) {
                this.newWeight = newWeight;
                this.place = place;
            }

            int getNewWeight() {
                return newWeight;
            }

            int getPlace() {
                return place;
            }
        }

        TempNode[] tempTree = new TempNode[n];
        //将huffmanTree中没有双亲的结点存储到tempTree中
        int i = 0, j = 0;
        for (i = 0; i < n; i++) {
            if (huffmanTree[i].getParent() == -1 && huffmanTree[i].getWeight() != 0) {
                tempTree[j] = new TempNode(huffmanTree[i].getWeight(), i);
                j++;
            }
        }

        int m1, m2;
        m1 = m2 = 0;
        for (i = 0; i < j; i++) {
            if (tempTree[i].getNewWeight() < tempTree[m1].getNewWeight())//此处不让取到相等，是因为结点中有相同权值的时候，m1取最前的
                m1 = i;
        }
        for (i = 0; i < j; i++) {
            if (m1 == m2)
                m2++;//当m1在第一个位置的时候，m2向后移一位
            if (tempTree[i].getNewWeight() <= tempTree[m2].getNewWeight() && i != m1)//此处取到相等，是让在结点中有相同的权值的时候，

                //m2取最后的那个。
                m2 = i;
        }

        min[0] = tempTree[m1].getPlace();
        min[1] = tempTree[m2].getPlace();
        return min;
    }


    // 哈夫曼树节点
    static class HaffmanNode {
        // 节点权重
        int weight;
        int parent;
        int left;
        int right;

        public HaffmanNode(int weight, int parent, int left, int right) {
            this.weight = weight;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }

    // 哈夫曼数组
    static class HuffmanNodeCode {
        String character;
        String code;

        HuffmanNodeCode(String character, String code) {
            this.character = character;
            this.code = code;
        }

        HuffmanNodeCode(String code) {
            this.code = code;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
