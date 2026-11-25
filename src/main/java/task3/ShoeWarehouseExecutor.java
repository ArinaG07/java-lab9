package task3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ShoeWarehouseExecutor {
    private final BlockingQueue<Order> orders = new LinkedBlockingQueue<>(5);
    public static final String[] SHOE_TYPES = {"Nike", "Adidas", "Puma", "Reebok", "New Balance"};

    public void receiveOrder(Order order) throws InterruptedException {
        orders.put(order);
        System.out.println("Принят заказ (Executor): " + order.id() + " (" + order.shoeType() + ")");
    }

    public Order fulfillOrder() throws InterruptedException {
        Order order = orders.take();
        System.out.println("Выполнен заказ (Executor): " + order.id() + " (" + order.shoeType() + ")");
        return order;
    }
}