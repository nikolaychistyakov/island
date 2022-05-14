package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Animal;

public class Duck extends Herbivore {


    public Duck(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Duck reproduction() {
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
        return "\uD83E\uDD86";
    }
}
