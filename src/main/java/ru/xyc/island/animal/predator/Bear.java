package ru.xyc.island.animal.predator;

public class Bear extends Predator {
    public Bear(String icon, double weight, int maximumNumberOfAnimals, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
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
