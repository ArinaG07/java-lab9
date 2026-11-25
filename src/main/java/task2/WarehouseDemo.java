package task2;

public class WarehouseDemo {
    public static void run() throws InterruptedException {
        ShoeWarehouse warehouse = new ShoeWarehouse(5);
        int totalOrders = 15;


        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= totalOrders; i++) {
                    String type = ShoeWarehouse.SHOE_TYPES[i % ShoeWarehouse.SHOE_TYPES.length];
                    warehouse.receiveOrder(new Order(i, type, (i % 5) + 1));
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        });


        Thread[] consumers = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 5; j++) {
                        warehouse.fulfillOrder();
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) { e.printStackTrace(); }
            });
            consumers[i].start();
        }

        producer.start();
        producer.join();
        for (Thread c : consumers) c.join();

    }
}