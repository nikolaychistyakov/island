package ru.xyc.island.animal.predator;

public class Wolf extends Predator{
    public Wolf(String icon, int weight, int maximumNumberOfAnimals, int travelSpeed, int fullSaturation, int canLiveAfterSaturation) {
        super(icon, weight, maximumNumberOfAnimals, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Wolf reproduction() {
        return null;
    }

    @Override
    public void eat(Object obj) {

    }

    @Override
    public void movie(char direction, int distance) {

    }

    @Override
    public String toString() {
        return icon;
    }
}
