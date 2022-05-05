package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.Animal;

public abstract class Predator extends Animal {
    public Predator(String icon,double weight, int maximumNumberOfAnimals, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(icon,weight, maximumNumberOfAnimals, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }
}
