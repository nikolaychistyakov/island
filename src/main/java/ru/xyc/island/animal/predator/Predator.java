package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.Animal;

public abstract class Predator extends Animal {
    public Predator(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }
}
