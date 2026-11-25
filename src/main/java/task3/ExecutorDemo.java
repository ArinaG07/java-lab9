package task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {
    public static void run() throws InterruptedException {
        ShoeWarehouseExecutor warehouse = new ShoeWarehouseExecutor();
        int totalOrders = 15;


        ExecutorService producerPool = Executors.newSingleThreadExecutor();
        ExecutorService consumerPool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= totalOrders; i++) {
            final int orderId = i;
            producerPool.submit(() -> {
                try {
                    String type = ShoeWarehouseExecutor.SHOE_TYPES[orderId % ShoeWarehouseExecutor.SHOE_TYPES.length];
                    warehouse.receiveOrder(new Order(orderId, type, (orderId % 5) + 1));
                } catch (InterruptedException e) { e.printStackTrace(); }
            });
        }


        for (int i = 0; i < 3; i++) {
            consumerPool.submit(() -> {
                try {
                    for (int j = 0; j < 5; j++) {
                        warehouse.fulfillOrder();
                    }
                } catch (InterruptedException e) { e.printStackTrace(); }
            });
        }

        producerPool.shutdown();
        consumerPool.shutdown();
        producerPool.awaitTermination(10, TimeUnit.SECONDS);
        consumerPool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Задание 3 завершено.");
    }
}