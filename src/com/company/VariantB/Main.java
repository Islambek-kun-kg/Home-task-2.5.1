package com.company.VariantB;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static final int places = 100;
    public static final int oc = 4;

    public synchronized static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(places);
        Semaphore semaphore = new Semaphore(oc);
        for (int i = 1; i <= 100; i++) {
            new PassengerThread("Passenger " + i, semaphore, cdl).start();
        }
        try {
            Thread.currentThread().sleep((places / oc + 1) * 1000);
            System.out.println("Автобус заполнился и поехал в Ош");
        } catch (Exception e) {
        }
    }
}