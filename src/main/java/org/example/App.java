package org.example;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        IslandConfig config = ConfigLoader.getInstance().getConfig();
        Island island = new Island(config.island.width, config.island.height);

        // Инициализация острова
        IslandInitializer.populateIsland(island, config);

        // Подсчёт начального количества животных и растений
        int initialAnimals = countAnimals(island);
        int initialPlants = countPlants(island);
        System.out.println("Начальная статистика:");
        System.out.println("Животных: " + initialAnimals);
        System.out.println("Растений: " + initialPlants);

        // Цикл симуляции
        int cycle = 0;
        while (true) {
            cycle++;
            System.out.println("Цикл " + cycle + ":");

            // Обработка каждого цикла
            for (int y = 0; y < island.getHeight(); y++) {
                for (int x = 0; x < island.getWidth(); x++) {
                    Location location = island.getLocation(x, y);

                    // Обновление состояния животных
                    for (Animal animal : location.getAnimalsSnapshot()) {
                        animal.updateSatiety();
                        if (animal.isStarving()) {
                            animal.die(location);
                        } else {
                            animal.eat(location);
                            animal.reproduce(location);
                            animal.move(island, x, y);
                        }
                    }

                    // Рост растений
                    for (Plant plant : location.getPlantsSnapshot()) {
                        plant.grow(config.plants.growth_rate_per_turn);
                    }
                }
            }

            // Подсчёт текущего количества животных и растений
            int currentAnimals = countAnimals(island);
            int currentPlants = countPlants(island);

            // Вывод статистики каждые 3 секунды
            System.out.println("Текущая статистика:");
            System.out.println("Животных: " + currentAnimals);
            System.out.println("Растений: " + currentPlants);

            // Проверка условия завершения симуляции
            if (currentAnimals == 0) {
                System.out.println("Симуляция завершена: все животные вымерли.");
                break;
            }

            // Задержка перед следующим циклом (3 секунды)
            TimeUnit.SECONDS.sleep(3);
        }
    }

    // Метод для подсчёта всех животных на острове
    private static int countAnimals(Island island) {
        int totalAnimals = 0;
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                totalAnimals += island.getLocation(x, y).getAnimalsSnapshot().size();
            }
        }
        return totalAnimals;
    }

    // Метод для подсчёта всех растений на острове
    private static int countPlants(Island island) {
        int totalPlants = 0;
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                totalPlants += island.getLocation(x, y).getPlantsSnapshot().size();
            }
        }
        return totalPlants;
    }
}
