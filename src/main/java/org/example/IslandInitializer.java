package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.random;

public class IslandInitializer {
    private static final Random random = new Random();

    public static void populateIsland(Island island, IslandConfig config) {
        // Заполняем каждую клетку острова
        for (int y = 0; y < island.getWidth(); y++) {
            for (int x = 0; x < island.getHeight(); x++) {
                Location location = island.getLocation(x, y);

                // Добавляем растения
                int plantsCount = random.nextInt(config.plants.max_per_cell);
                for (int i = 0; i < plantsCount; i++) {
                    location.addPlant(new Plant());
                }

                // Добавляем животных
                    //String animalName = config.animals.keySet(random(6)).toString();
                //for (String animalName : config.animals.keySet()) {
                ArrayList <String> animal = new ArrayList<>();
                for(String animal1: config.animals.keySet()){
                    animal.add(animal1);
                }
                    String animalName = animal.get(random.nextInt(animal.size()));
                    AnimalConfig animalConfig = config.animals.get(animalName);
                    int animalCount = random.nextInt(animalConfig.max_per_cell);
                    for (int i = 0; i < animalCount; i++) {
                        Animal animal1 = AnimalFactory.createAnimal(animalName, animalConfig);
                        if (animal != null) {
                            location.addAnimal(animal1);
                        }
                    }

            }
        }
    }
}
