package com.company.VariantA;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static final int counter = 10;

    public static void main(String[] args) {

        CountDownLatch cdl = new CountDownLatch(1);
        Semaphore semaphore = new Semaphore(1);
        new Uploader("Файл", semaphore, cdl).start();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownLatch cld = new CountDownLatch(counter);
        Semaphore semaphore1 = new Semaphore(3);
        for (int i = 1; i <= counter; i++) {
            try {
                Thread.currentThread().sleep(100);
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Downloaders("User " + i, cld, semaphore1).start();
        }

        try {
            cld.await();
            System.out.println("По скольку достигунт лимит скачивания, файл удален с сервера!");
        } catch (Exception e) {
        }
    }
}