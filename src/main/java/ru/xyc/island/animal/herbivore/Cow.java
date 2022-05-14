package ru.xyc.island.animal.herbivore;

public class Cow extends Herbivore{


    public Cow(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
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
        return "\uD83D\uDC2E";
    }
}

