package app;

import task1.EvenThread;
import task1.OddRunnable;
import task2.WarehouseDemo;
import task3.ExecutorDemo;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Задание № 1: Создание потоков ---");
        Thread evenThread = new EvenThread();
        Thread oddThread = new Thread(new OddRunnable());
        evenThread.start();
        oddThread.start();
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("\n--- Задание № 2: Producer-Consumer (ручное управление) ---");
        try {
            WarehouseDemo.run();
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("\n--- Задание № 3: Producer-Consumer (ExecutorService) ---");
        try {
            ExecutorDemo.run();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}