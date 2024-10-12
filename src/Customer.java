public class Customer implements Runnable {
    private final Warehouse warehouse;

    public Customer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if(warehouse.isWorkingHours()) {
                    Thread.sleep(Math.round(Math.random() * (5000-1000) + 1000)); // Імітуємо час між покупками
                    System.out.println("Клієнт намагається забрати товар.");
                    warehouse.removeItem();
                } else {
                    System.out.println("Спроба забрати товар в неробочий час.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
