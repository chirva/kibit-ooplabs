import java.util.Date;
import java.util.List;

public class Order {
    // Обов'язкові параметри
    private final int id;
    private final List<String> items;
    private final double totalPrice;

    // Опціональні параметри
    private final String deliveryAddress;
    private final boolean giftWrap;
    private final String note;
    private final Date deliveryDate;
    private final String couponCode;

    // Приватний конструктор, який приймає об'єкт OrderBuilder
    private Order(OrderBuilder builder) {
        this.id = builder.id;
        this.items = builder.items;
        this.totalPrice = builder.totalPrice;
        this.deliveryAddress = builder.deliveryAddress;
        this.giftWrap = builder.giftWrap;
        this.note = builder.note;
        this.deliveryDate = builder.deliveryDate;
        this.couponCode = builder.couponCode;
    }

    // Вкладений клас OrderBuilder
    public static class OrderBuilder {
        // Обов'язкові параметри
        private final int id;
        private final List<String> items;
        private final double totalPrice;

        // Опціональні параметри (ініціалізуються значеннями за замовчуванням)
        private String deliveryAddress = "";
        private boolean giftWrap = false;
        private String note = "";
        private Date deliveryDate = null;
        private String couponCode = "";

        // Конструктор з обов'язковими параметрами
        public OrderBuilder(int id, List<String> items, double totalPrice) {
            this.id = id;
            this.items = items;
            this.totalPrice = totalPrice;
        }

        // Методи для встановлення опціональних параметрів
        public OrderBuilder withDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public OrderBuilder withGiftWrap(boolean giftWrap) {
            this.giftWrap = giftWrap;
            return this;
        }

        public OrderBuilder withNote(String note) {
            this.note = note;
            return this;
        }

        public OrderBuilder withDeliveryDate(Date deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public OrderBuilder withCouponCode(String couponCode) {
            this.couponCode = couponCode;
            return this;
        }

        // Метод build для створення об'єкта Order
        public Order build() {
            return new Order(this);
        }
    }

    // Гетери для доступу до значень
    public int getId() {
        return id;
    }

    public List<String> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isGiftWrap() {
        return giftWrap;
    }

    public String getNote() {
        return note;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getCouponCode() {
        return couponCode;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", giftWrap=" + giftWrap +
                ", note='" + note + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", couponCode='" + couponCode + '\'' +
                '}';
    }
}
