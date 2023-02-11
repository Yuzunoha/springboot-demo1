package com.example.demo.sukkiri.pset8;

import java.util.Random;

public class Cleric {
    private String name;
    private int hp = 10;
    private int mp = 10;
    private final int MAX_HP = 50;
    private final int MAX_MP = 50;

    private final Random random = new Random();

    public void selfAid() {
        this.mp -= 5;
        this.hp = this.MAX_HP;
    }

    public int pray(int sec) {
        // 実際に回復したmpの量: sec + (0~2)
        // ただしmpはMAX_MPを超えない
        int newMp = this.mp + sec + this.random.nextInt(0, 3);
        if (this.MAX_MP < newMp) {
            newMp = this.MAX_MP;
        }
        int diff = newMp - this.mp;
        this.mp = newMp;
        return diff;
    }
}