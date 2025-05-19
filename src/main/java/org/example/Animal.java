package org.example;

import java.util.Random;

public abstract class Animal {
    protected String name;
    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected double currentSatiety; // Текущая сытость

    private static final Random random = new Random();

    public Animal(String name, double weight, int maxPerCell, int speed, double foodNeeded) {
        this.name = name;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.currentSatiety = foodNeeded / 2; // Начальная сытость
    }

    public abstract void eat(Location location);
    public abstract void reproduce(Location location);
    public void move(Island island, int currentX, int currentY) {
        int dx = random.nextInt(speed * 2 + 1) - speed;
        int dy = random.nextInt(speed * 2 + 1) - speed;
        int newX = Math.max(0, Math.min(island.getWidth() - 1, currentX + dx));
        int newY = Math.max(0, Math.min(island.getHeight() - 1, currentY + dy));

        if (newX != currentX || newY != currentY) {
            Location currentLocation = island.getLocation(currentX, currentY);
            Location newLocation = island.getLocation(newX, newY);

            synchronized (currentLocation) {
                currentLocation.removeAnimal(this);
            }
            synchronized (newLocation) {
                newLocation.addAnimal(this);
            }
        }
    }

    public boolean isStarving() {
        return currentSatiety <= 0;
    }

    public void die(Location location) {
        location.removeAnimal(this);
    }
    public void updateSatiety() {
        currentSatiety -= foodNeeded * 0.1; // Уменьшаем сытость на 10% от нужного количества еды
        if (currentSatiety < 0) currentSatiety = 0;
    }

}
