package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.herbivore.Hamster;

public class Wolf extends Predator{
    public Wolf(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Wolf reproduction() {
        return null;
    }

    @Override
    public void eat(Object food) {
        if(food instanceof Hamster) {

        }
    }

    @Override
    public void movie(char direction, int distance) {

    }
}
