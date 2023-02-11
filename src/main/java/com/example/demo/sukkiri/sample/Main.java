package com.example.demo.sukkiri.sample;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(getList());
    }

    public static List getList() {
        List<String> list = new ArrayList<String>();
        list.add("アイテム1");
        list.add("アイテム2");
        list.add("アイテム3");
        return list;
    }
}
