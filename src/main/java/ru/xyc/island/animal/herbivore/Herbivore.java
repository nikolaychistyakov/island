package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super( weight,travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
