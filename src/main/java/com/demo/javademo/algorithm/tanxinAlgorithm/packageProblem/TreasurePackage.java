package com.demo.javademo.algorithm.tanxinAlgorithm.packageProblem;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 阿里巴巴与四十大盗背包问题
 */
public class TreasurePackage {
    public static void main(String[] args) {
        System.out.print("请输入毛驴的承载能力：");
        Scanner input = new Scanner(System.in);
        // 背包最大容量
        double maxCapacity = input.nextDouble();
        // 每件宝物重量，价值和单位重量价值
        Treasure[] treasures = initializData();
        // 根据单位重量价值做排序
        Arrays.sort(treasures);

        // 装入宝箱的宝物的价值
        double sumValue = 0.0;
        for (Treasure treasure : treasures) {
            // 背包装得下
            if (maxCapacity > treasure.getWeight()) {
                maxCapacity -= treasure.getWeight();
                sumValue += treasure.getValue();
            } else {
                // 背包装不下完整宝物，将宝物拆分
                sumValue += maxCapacity * treasure.getUnitValue();
                break;
            }
        }
        System.out.println("装入宝箱的宝物最大总价值是:" + sumValue);
    }

    private static Treasure[] initializData() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入宝物数量：");
        int treasureCount = input.nextInt();

        Treasure[] treasures = new Treasure[treasureCount];
        System.out.println("请输入每个宝物的重量和价值：");
        for (int i = 0; i < treasureCount; i++) {
            System.out.println("请第" + (i + 1) + "个宝物的重量");
            double weight = input.nextDouble();
            System.out.println("请第" + (i + 1) + "个宝物的价值");
            double value = input.nextDouble();
            treasures[i] = new Treasure(weight, value);
        }

        return treasures;
    }
}

// 宝物对象
class Treasure implements Comparable<Treasure> {
    // 重量
    private double weight;
    // 价值
    private double value;
    // 单位重量价值
    private double unitValue;

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public double getUnitValue() {
        return this.value / this.weight;
    }

    public Treasure(double weight, double value) {
        this.value = value;
        this.weight = weight;
        unitValue = this.value / this.weight;
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "value=" + value +
                ", weight=" + weight +
                ", unitValue=" + (value / weight) +
                '}';
    }

    @Override
    public int compareTo(Treasure o) {
        return (this.unitValue < o.unitValue) ? 1 : -1;
    }
}