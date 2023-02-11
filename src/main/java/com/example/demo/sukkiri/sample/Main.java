package com.example.demo.sukkiri.sample;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        for (String s : getList()) {
            System.out.println(s);
        }
    }

    public static List<String> getList() {
        System.out.println("getList()が呼ばれた");
        List<String> list = new ArrayList<>();
        list.add("アイテム1");
        list.add("アイテム2");
        return list;
    }
}
