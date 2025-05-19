package org.example;

public class Plant {
    private double weight; // Вес растения

    public Plant(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void grow(double growthRate) {
        weight += growthRate; // Увеличиваем вес растения при росте
        if (weight < 0) {
            weight = 0; // Вес растения не может быть отрицательным
        }
    }
}