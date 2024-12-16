package lab4.task3.sensors;

public class Main {
    public static void main(String[] args) {
        // Створення датчика, який працює з Цельсіями
        TemperatureSensor celsiusSensor = new CelsiusSensor(25.0);
        System.out.println("Celsius Sensor: " + celsiusSensor.getTemperature() + " °C");

        // Створення датчика, який працює з Фаренгейтами
        FahrenheitSensor fahrenheitSensor = new FahrenheitSensor(77.0);
        TemperatureSensor adapter = new TemperatureAdapter(fahrenheitSensor);

        // Отримання температури через адаптер (перераховується у Цельсії)
        System.out.println("Fahrenheit Sensor (via Adapter): " + adapter.getTemperature() + " °C");
    }
}

// Інтерфейс для всіх датчиків
interface TemperatureSensor {
    double getTemperature();
}

// Датчик, що надає температуру в Цельсіях
class CelsiusSensor implements TemperatureSensor {
    private double temperature;

    public CelsiusSensor(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double getTemperature() {
        System.out.println("Temperature in Celsius: " + temperature);
        return temperature;
    }
}

// Датчик, що надає температуру в Фаренгейтах
class FahrenheitSensor {
    private double temperatureFahrenheit;

    public FahrenheitSensor(double temperatureFahrenheit) {
        this.temperatureFahrenheit = temperatureFahrenheit;
    }

    public double getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }
}

// Адаптер для перетворення температури з Фаренгейтів у Цельсії
class TemperatureAdapter implements TemperatureSensor {
    private FahrenheitSensor fahrenheitSensor;

    public TemperatureAdapter(FahrenheitSensor fahrenheitSensor) {
        this.fahrenheitSensor = fahrenheitSensor;
    }

    @Override
    public double getTemperature() {
        // Перерахунок з Фаренгейтів у Цельсії
        double fahrenheit = fahrenheitSensor.getTemperatureFahrenheit();
        double celsius = (fahrenheit - 32) * 5 / 9;
        System.out.println("Converting Fahrenheit to Celsius: " + celsius);
        return celsius;
    }
}
