package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.random;

public class IslandInitializer {
    private static final Random random = new Random();

    public static void populateIsland(Island island, IslandConfig config) {
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);

                // Добавляем растения
                int plantsCount = random.nextInt(config.plants.max_per_cell);
                for (int i = 0; i < plantsCount; i++) {
                    location.addPlant(new Plant(config.plants.weight));
                }

                // Добавляем животных
                for (String animalName : config.animals.keySet()) {
                    AnimalConfig animalConfig = config.animals.get(animalName);
                    int animalCount = random.nextInt(animalConfig.max_per_cell);
                    for (int i = 0; i < animalCount; i++) {
                        Animal animal = AnimalFactory.createAnimal(animalName, animalConfig);
                        location.addAnimal(animal);
                    }
                }
            }
        }
    }
}
