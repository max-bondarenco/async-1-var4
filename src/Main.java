public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(10, 9, 17); // Створюємо склад з ємністю 10 товарів
        Supplier supplier = new Supplier(warehouse);
        Customer customer = new Customer(warehouse);

        // Запускаємо потоки постачальника та покупця
        new Thread(supplier).start();
        new Thread(customer).start();
    }
}