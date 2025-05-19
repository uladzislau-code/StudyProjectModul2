package org.example;

import java.util.Map;

//TODO инициализация полей обьекта

public class Herbivore extends Animal {
    String type;
    double weight;
    int maxPerCell;
    int speed;
    double foodNeeded;
    Map<String, Integer> prey;

    //TODO конструктор травоядных с параметрами из файла с конфигурацией животных

    public Herbivore(String type, double weight, int maxPerCell, int speed, double foodNeeded, Map<String, Integer> prey) {
        super(type, weight, maxPerCell, speed, foodNeeded);
        this.type = type;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.prey = prey;

    }

    @Override
    public void eat(Location location) {
        if (prey.containsKey("plants")) { // Проверяем возможность питания растениями
            for (Plant plant : location.getPlantsSnapshot()) {
                if (plant.getWeight() > 0) {
                    double foodConsumed = Math.min(foodNeeded, plant.getWeight()); // Травоядное съедает часть растения
                    plant.grow(-foodConsumed); // Уменьшаем вес растения
                    currentSatiety += foodConsumed; // Увеличиваем сытость травоядного
                    if (currentSatiety > foodNeeded) currentSatiety = foodNeeded; // Ограничиваем сытость
                    break;
                }
            }
        }
    }


    @Override
    public void reproduce(Location location) {
        if (location.getAnimalsSnapshot().size() < maxPerCell) {
            location.addAnimal(new Herbivore(type, weight, maxPerCell, speed, foodNeeded, prey));
        }
    }
}
