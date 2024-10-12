public class Supplier implements Runnable {
    private final Warehouse warehouse;

    public Supplier(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(Math.round(Math.random() * (3000-1000) + 1000)); // Імітуємо час додавання товару
                System.out.println("Постачальник намагається додати товар.");
                warehouse.addItem();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}