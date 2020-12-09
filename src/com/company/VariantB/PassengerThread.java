package com.company.VariantB;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class PassengerThread extends Thread {

    private Semaphore semaphore;
    private CountDownLatch cdl;

    public PassengerThread(String name, Semaphore semaphore, CountDownLatch cdl) {
        super(name);
        this.cdl = cdl;
        this.semaphore = semaphore;
    }

    public synchronized void run() {
        try {
            System.out.println(this.getName() + " пришел на автовокзал");
            semaphore.acquire();
            System.out.println(this.getName() + " подошел к кассе");
            sleep(1000);
            semaphore.release();
            System.out.println(this.getName() + " закончил регистрацию и сел в автобус");
            cdl.countDown();
        } catch (Exception e) {
        }
    }
}