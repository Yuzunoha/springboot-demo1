package com.example.demo.sukkiri.pset6;

public class Kouhan {
    public static void doDeae() {
        System.out.println("であえ。");
    }

    public static void showMondokoro() throws Exception {
        System.out.println("めにはいらぬか");
        Thread.sleep(3 * 1000);
        Zenhan.doTogame();
    }
}
