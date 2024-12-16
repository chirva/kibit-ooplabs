package lab4.task1.paymentprocessor;

import java.util.HashMap;
import java.util.Map;

// Інтерфейс для стратегії знижок
interface DiscountStrategy {
    double calculateDiscount(double amount);
}

// Стратегія для Regular клієнтів
class RegularDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.05; // 5% знижка
    }
}

// Стратегія для Premium клієнтів
class PremiumDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.10; // 10% знижка
    }
}

// Стратегія для випадків, коли знижки немає
class NoDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return 0; // Без знижки
    }
}

// Основний клас PaymentProcessor
class PaymentProcessor {
    private final Map<String, DiscountStrategy> discountStrategies;

    // Ініціалізація стратегій у конструкторі
    public PaymentProcessor() {
        discountStrategies = new HashMap<>();
        discountStrategies.put("Regular", new RegularDiscount());
        discountStrategies.put("Premium", new PremiumDiscount());
    }

    // Метод для розрахунку знижки з використанням стратегії
    public double calculateDiscount(String customerType, double amount) {
        DiscountStrategy strategy = discountStrategies.getOrDefault(customerType, new NoDiscount());
        return strategy.calculateDiscount(amount);
    }
}

// Головний клас для запуску програми
public class Main {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // Приклад для Regular клієнта
        double regularDiscount = paymentProcessor.calculateDiscount("Regular", 1000);
        System.out.println("Regular Discount: " + regularDiscount);

        // Приклад для Premium клієнта
        double premiumDiscount = paymentProcessor.calculateDiscount("Premium", 1000);
        System.out.println("Premium Discount: " + premiumDiscount);

        // Приклад для невідомого типу клієнта
        double noDiscount = paymentProcessor.calculateDiscount("VIP", 1000);
        System.out.println("No Discount: " + noDiscount);
    }
}
