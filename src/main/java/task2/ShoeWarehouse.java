package task2;

import java.util.LinkedList;
import java.util.Queue;

public class ShoeWarehouse {
    private final Queue<Order> orders = new LinkedList<>();
    private final int capacity;
    public static final String[] SHOE_TYPES = {"Nike", "Adidas", "Puma", "Reebok", "New Balance"};

    public ShoeWarehouse(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void receiveOrder(Order order) throws InterruptedException {
        while (orders.size() == capacity) {
            wait();
        }
        orders.add(order);
        System.out.println("Принят заказ: " + order.id() + " (" + order.shoeType() + ")");
        notifyAll();
    }

    public synchronized Order fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        Order order = orders.poll();
        System.out.println("Выполнен заказ: " + order.id() + " (" + order.shoeType() + ")");
        notifyAll();
        return order;
    }
}