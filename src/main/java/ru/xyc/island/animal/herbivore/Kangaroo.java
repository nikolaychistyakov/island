package ru.xyc.island.animal.herbivore;

public class Kangaroo extends Herbivore {


    public Kangaroo(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
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

    @Override
    public String toString() {
        return  "\uD83E\uDD98";
    }
}
