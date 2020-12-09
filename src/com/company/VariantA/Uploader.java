package com.company.VariantA;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Uploader extends Thread {

    private CountDownLatch cdl;
    private Semaphore semaphore;
    private final int large = 500;
    private final int speed = 20;

    public Uploader(String name, Semaphore semaphore, CountDownLatch cdl) {
        super(name);
        this.semaphore = semaphore;
        this.cdl = cdl;
    }

    public void run() {
        try {
            System.out.println(this.getName() + " начал загружаться в сервер:");
            semaphore.acquire();
            for (int i = 0; i < 50; i++) {
                System.out.printf("\uD83D\uDFE9");
                sleep(large / speed);
            }
            semaphore.release();
            System.out.println("\n" + this.getName() + " закончил зашружаться в сервер.");
            cdl.countDown();
        } catch (Exception e) {
        }
    }

}