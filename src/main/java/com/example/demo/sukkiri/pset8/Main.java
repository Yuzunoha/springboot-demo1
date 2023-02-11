package com.example.demo.sukkiri.pset8;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Cleric c = new Cleric();
        int min, max;
        min = max = 100;
        for (int i = 0; i < 10000; i++) {
            int x = c.pray(99);
            if (x < min) {
                min = x;
            }
            if (max < x) {
                max = x;
            }
        }
        System.out.println(min + ":" + max);
    }
}
