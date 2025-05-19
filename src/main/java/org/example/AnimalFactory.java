package org.example;

//TODO создание объекта(определённого животного)

public class AnimalFactory {
    public static Animal createAnimal(String name, AnimalConfig config) {
        switch (config.type) {
            case "Predator":
                return new Predator(name, config.weight, config.max_per_cell, config.speed, config.food_needed, config.prey);
            case "Herbivore":
                return new Herbivore(name, config.weight, config.max_per_cell, config.speed, config.food_needed, config.prey);
            default:
                throw new IllegalArgumentException("Неизвестный тип животного: " + config.type);
        }
    }
}
