package org.example;

import java.util.Random;

// TODO: Абстрактный класс Animal представляет базовую модель животного
public abstract class Animal {
    private final String type;
    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected double currentSatiety; // Текущая сытость

    private static final Random random = new Random();

    // TODO: Конструктор для создания животного с заданными параметрами
    public Animal(String type, double weight, int maxPerCell, int speed, double foodNeeded) {
        this.type =type;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.currentSatiety = foodNeeded / 2; // Начальная сытость
    }


    // TODO: Абстрактный метод для реализации поведения питания
    public abstract void eat(Location location);
    // TODO: Абстрактный метод для реализации размножения
    public abstract void reproduce(Location location);
     // TODO: Метод для перемещения животного по острову
    public void move(Island island, int currentX, int currentY) {
        int dx = random.nextInt(speed * 2 + 1) - speed;
        int dy = random.nextInt(speed * 2 + 1) - speed;
        int newX = Math.max(0, Math.min(island.getWidth() - 1, currentX + dx));
        int newY = Math.max(0, Math.min(island.getHeight() - 1, currentY + dy));

        // Проверка, что животное действительно переместилось
        if (newX != currentX || newY != currentY) {
            Location currentLocation = island.getLocation(currentX, currentY);
            Location newLocation = island.getLocation(newX, newY);
            // Удаляем животное из текущей локации
            synchronized (currentLocation) {
                currentLocation.removeAnimal(this);
            }
            // Добавляем животное в новую локацию
            synchronized (newLocation) {
                newLocation.addAnimal(this);
            }
        }
    }

    // TODO: Метод для проверки, голодает ли животное
    public boolean isStarving() {
        return currentSatiety <= 0;
    }

    // TODO: Метод для удаления животного из локации
    public void die(Location location) {
        location.removeAnimal(this);
    }
    // TODO: Метод для обновления уровня сытости
    public void updateSatiety() {
        currentSatiety -= foodNeeded * 0.1; // Уменьшаем сытость на 10% от нужного количества еды
        if (currentSatiety < 0) currentSatiety = 0;
    }

    protected String getType() {
        return type;
    }

    protected double getWeight() {
        return weight;
    }
}
