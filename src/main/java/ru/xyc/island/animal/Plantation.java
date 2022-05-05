package ru.xyc.island.animal;

public class Plantation {

    double weight;
    int maximumNumberOfAnimals;


    public Plantation(double weight, int maximumNumberOfAnimals) {
        this.weight = weight;
        this.maximumNumberOfAnimals = maximumNumberOfAnimals;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaximumNumberOfAnimals() {
        return maximumNumberOfAnimals;
    }

    public void setMaximumNumberOfAnimals(int maximumNumberOfAnimals) {
        this.maximumNumberOfAnimals = maximumNumberOfAnimals;
    }

    @Override
    public String toString() {
        return 	"\uD83C\uDF40";
    }
}
