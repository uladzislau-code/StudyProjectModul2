package org.example;

import java.util.Map;

public class AnimalFactory {
    public static Animal createAnimal(String name, AnimalConfig config) {
        switch (name) {
            case "Wolf":
                return new Wolf(config);
            case "Boa":
                return new Boa(config);
            case "Fox":
                return new Fox(config);
            case "Bear":
                return new Bear(config);
            case "Eagle":
                return new Eagle(config);
            case "Horse":
                return new Herbivore("Horse", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Deer":
                return new Herbivore("Deer", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Rabbit":
                return new Herbivore("Rabbit", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Mouse":
                return new Herbivore("Mouse", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Goat":
                return new Herbivore("Goat", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Sheep":
                return new Herbivore("Sheep", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Boar":
                return new Herbivore("Boar", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Buffalo":
                return new Herbivore("Buffalo", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Duck":
                return new Herbivore("Duck", config.weight, config.max_per_cell, config.speed, config.food_needed);
            case "Caterpillar":
                return new Herbivore("Caterpillar", config.weight, config.max_per_cell, config.speed, config.food_needed);
            default:
                return null;
        }
    }
}
