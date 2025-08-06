package com.example.lnacos;

public class duoxiangcheng {
    public static void sleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread test = new Thread(() -> {
            while (true) {
                System.out.println("test");
                duoxiangcheng.sleep();
            }
        });
//        test.start();
//        test.join();
//        System.out.println("结束");
    }
}
