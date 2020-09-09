package com.demo.javademo.algorithm.tanxinAlgorithm.packageProblem;

import java.util.Arrays;
import java.util.Scanner;

public class TreasurePackage {
    public static void main(String[] args) {
        System.out.print("请输入毛驴的承载能力：");
        Scanner input = new Scanner(System.in);
        double capacity = input.nextDouble();
        Treasure[] treasures = initializData();
        Arrays.sort(treasures);

        double sumValue = 0.0;
        for (int i = 0; i < treasures.length; i++) {
            if (capacity > treasures[i].getWeight()) {
                capacity -= treasures[i].getWeight();
                sumValue += treasures[i].getValue();
            } else {
                sumValue += capacity * treasures[i].getUnitValue();
                break;
            }
        }
        System.out.println("装入宝箱的最大价值是:" + sumValue);
    }

    public static Treasure[] initializData() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入宝物数量：");
        int treasureCount = input.nextInt();

        Treasure[] treasures = new Treasure[treasureCount];
        System.out.println("请输入每个宝物的重量和价值，用空格分开：");
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

class Treasure implements Comparable<Treasure> {
    private double weight;
    private double value;
    private double unitValue;

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public double getUnitValue() {
        return this.getValue() / this.getWeight();
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