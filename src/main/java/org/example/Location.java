package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Location {
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public synchronized void addPlant(Plant plant) {
        plants.add(plant);
    }

    public synchronized List<Animal> getAnimalsSnapshot() {
        return new ArrayList<>(animals);
    }

    public synchronized List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }
}
