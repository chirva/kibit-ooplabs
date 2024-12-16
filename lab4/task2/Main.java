import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Список товарів у замовленні
        List<String> itemsList = Arrays.asList("Ноутбук", "Мишка", "Клавіатура");

        // Створення об'єкта Order з обов'язковими та опціональними параметрами
        Order order1 = new Order.OrderBuilder(1, itemsList, 1500.0)
                .withDeliveryAddress("Київ, вул. Хрещатик, 1")
                .withGiftWrap(true)
                .withNote("Будь ласка, зателефонуйте за годину до доставки")
                .withDeliveryDate(new Date())
                .withCouponCode("DISCOUNT2024")
                .build();

        System.out.println("Order 1: " + order1);

        // Створення об'єкта Order тільки з обов'язковими параметрами
        Order order2 = new Order.OrderBuilder(2, itemsList, 2000.0).build();

        System.out.println("Order 2: " + order2);

        // Створення об'єкта Order з частковими опціональними параметрами
        Order order3 = new Order.OrderBuilder(3, itemsList, 1800.0)
                .withDeliveryAddress("Львів, вул. Городоцька, 10")
                .build();

        System.out.println("Order 3: " + order3);
    }
}
