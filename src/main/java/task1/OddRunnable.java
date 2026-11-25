package task1;

public class OddRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 9; i += 2) {
            System.out.println("Нечетный поток: " + i);
            try { Thread.sleep(100); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}