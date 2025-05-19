package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Класс Location представляет клетку острова, содержащую животных и растения
public class Location {
    // TODO: Список животных, находящихся в данной клетке
    private final List<Animal> animals = new ArrayList<>();
    // TODO: Список растений
    private final List<Plant> plants = new ArrayList<>();

    // TODO: Метод для добавления животного в клетку
    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // TODO: Метод для удаления животного из клетки
    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    // TODO: Метод для добавления растения в клетку
    public synchronized void addPlant(Plant plant) {
        plants.add(plant);
    }

    // TODO: Метод для получения копии списка животных (снимок текущего состояния)
    public synchronized List<Animal> getAnimalsSnapshot() {
        return new ArrayList<>(animals);
    }

    // TODO: Метод для получения списка растений
    public synchronized List<Plant> getPlantsSnapshot() {
        return new ArrayList<>(plants);
    }
}
