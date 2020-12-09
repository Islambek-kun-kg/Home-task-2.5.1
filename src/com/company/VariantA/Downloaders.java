package com.company.VariantA;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders extends Thread {

    private CountDownLatch cld;
    private Semaphore semaphore;
    private final int large = 500;
    private final int speed = 100;

    public Downloaders(String name, CountDownLatch cld, Semaphore semaphore) {
        super(name);
        this.cld = cld;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            System.out.println(this.getName() + " зашел в север");
            semaphore.acquire();
            System.out.println(this.getName() + " начал загрузку с сервера");
            for (int i = 0; i <= speed; i++) {
                sleep(large / speed);
            }
            System.out.println(this.getName() + " закончил загрузку файла с сервера");
            semaphore.release();
            cld.countDown();
        } catch (Exception e) {
        }

    }
}