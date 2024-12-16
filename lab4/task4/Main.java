package lab4.task4.weathermonitor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створюємо об'єкт WeatherData
        WeatherData weatherData = new WeatherData();

        // Створюємо дисплеї та реєструємо їх
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        // Встановлюємо нові дані про погоду
        weatherData.setMeasurements(25.0f, 65.0f, 30.4f);
        System.out.println("-----------------------------------");
        weatherData.setMeasurements(27.0f, 70.0f, 29.2f);
        System.out.println("-----------------------------------");
        weatherData.setMeasurements(23.0f, 90.0f, 29.2f);
        System.out.println("-----------------------------------");

        // Відписуємо один з дисплеїв
        weatherData.removeObserver(statisticsDisplay);

        // Встановлюємо нові дані про погоду
        weatherData.setMeasurements(22.0f, 85.0f, 28.5f);
    }
}

// Інтерфейс спостерігача
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// Інтерфейс суб'єкта (Subject)
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Клас WeatherData, що зберігає дані погоди та сповіщає спостерігачів
class WeatherData implements Subject {
    private final List<Observer> observers; // Список спостерігачів
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<>(); // Ініціалізація списку спостерігачів
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    // Метод викликається, коли з'являються нові дані
    public void measurementsChanged() {
        notifyObservers();
    }

    // Метод для встановлення нових даних про погоду
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

// Дисплей, що показує поточні погодні умови
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    // Конструктор з реєстрацією спостерігача
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Поточні умови: " + temperature + "°C та " + humidity + "% вологості.");
    }
}

// Дисплей, що показує статистику температур
class StatisticsDisplay implements Observer {
    private float maxTemp = Float.MIN_VALUE;
    private float minTemp = Float.MAX_VALUE;
    private float tempSum = 0.0f;
    private int numReadings;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }

        display();
    }

    public void display() {
        System.out.println("Статистика: Середня/Макс/Мін температура = " +
                (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}

// Дисплей, що прогнозує погоду на основі змін тиску
class ForecastDisplay implements Observer {
    private float lastPressure;
    private float currentPressure = 29.92f; // Початкове значення
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }

    public void display() {
        System.out.print("Прогноз: ");
        if (currentPressure > lastPressure) {
            System.out.println("Покращення погоди на шляху!");
        } else if (currentPressure == lastPressure) {
            System.out.println("Більше того ж самого.");
        } else if (currentPressure < lastPressure) {
            System.out.println("Обережно, можливе похолодання.");
        }
    }
}
