package com.company.VariantA;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static final int counter = 10;

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(counter);
        Semaphore semaphore = new Semaphore(1);
        new Uploader("Файл", semaphore).start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cdl.countDown();

        Semaphore semaphore1 = new Semaphore(3);
        for (int i = 1; i <= counter; i++) {
            try {
                Thread.currentThread().sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Downloaders("User " + i, cdl, semaphore1).start();
            cdl.countDown();
        }
        try {
            if (cdl.getCount() == 0) {
                Thread.currentThread().sleep(3000);
                System.out.println("По скольку достигунт лимит скачивания, файл удален с сервера!");
            }
        } catch (Exception e) {
        }
    }
}