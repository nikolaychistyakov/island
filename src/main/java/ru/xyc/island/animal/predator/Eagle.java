package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.predator.Predator;

public class Eagle extends Predator {
    public Eagle(String icon, double weight, int maximumNumberOfAnimals, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(icon, weight, maximumNumberOfAnimals, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Object reproduction() {
        return null;
    }

    @Override
    public void eat(Object obj) {

    }

    @Override
    public void movie(char direction, int distance) {

    }
}