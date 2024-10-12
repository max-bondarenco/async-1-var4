import java.util.concurrent.Semaphore;

public class Warehouse {
    private final int workStart;
    private final int workEnd;
    private int items;
    private final Semaphore availableItems;
    private final Semaphore emptySpaces;

    public Warehouse(int capacity, int workStart, int workEnd) {
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.items = 0;
        this.availableItems = new Semaphore(0); // Кількість доступних товарів (спочатку 0)
        this.emptySpaces = new Semaphore(capacity); // Місця на складі для нових товарів
    }

    // Додавання товарів на склад
    public void addItem() throws InterruptedException {
        emptySpaces.acquire(); // Чекаємо, поки з'явиться вільне місце
        synchronized (this) {
            items++;
            System.out.println("Додано товар. На складі: " + items);
        }
        availableItems.release(); // Дозволяємо покупцеві забрати товар
    }

    // Забір товару зі складу
    public void removeItem() throws InterruptedException {
        availableItems.acquire(); // Чекаємо, поки з'являться доступні товари
        synchronized (this) {
            items--;
            System.out.println("Забрано товар. На складі: " + items);
        }
        emptySpaces.release(); // Дозволяємо постачальнику додати новий товар}
    }


    // Імітуємо робочі години: з 9:00 до 17:00
    public boolean isWorkingHours() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        return hour >= this.workStart && hour < this.workEnd;
    }
}

