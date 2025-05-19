package org.example;

import java.util.Map;
import java.util.Random;

public class Predator extends Animal {
    private final Map<String, Integer> prey; // Карта вероятностей охоты на других животных

    public Predator(String type, double weight, int maxPerCell, int speed, double foodNeeded, Map<String, Integer> prey) {
        super(type, weight, maxPerCell, speed, foodNeeded);
        this.prey = prey;
    }

    Random random = new Random();
    @Override
    public void eat(Location location) {
        for (Animal potentialPrey : location.getAnimalsSnapshot()) {
            if (prey.containsKey(potentialPrey.getType())) { // Проверяем, может ли хищник охотиться на это животное
                int chance = prey.get(potentialPrey.getType());
                if (random.nextInt(100) < chance) { // Генерация случайного числа для проверки вероятности охоты
                    location.removeAnimal(potentialPrey); // Удаляем жертву из локации
                    currentSatiety += potentialPrey.getWeight(); // Увеличиваем сытость хищника
                    if (currentSatiety > foodNeeded) currentSatiety = foodNeeded; // Ограничиваем сытость
                    break; // Хищник съел одну жертву и остановился
                }
            }
        }
    }

    @Override
    public void reproduce(Location location) {
        if (location.getAnimalsSnapshot().size() < maxPerCell) {
            //location.addAnimal(new Predator(name, weight, maxPerCell, speed, foodNeeded, prey));
        }
    }
}
