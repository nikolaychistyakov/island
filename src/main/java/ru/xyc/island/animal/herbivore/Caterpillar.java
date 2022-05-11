package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Plantation;

import java.util.Random;

public class Caterpillar extends Herbivore {


    public Caterpillar(String icon, double weight, int maximumNumberOfAnimals, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(icon, weight, maximumNumberOfAnimals, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Caterpillar reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                fullSaturation -= 0.0005;
                return new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1);
            }
        }
        isReproduce = false;
        return null;
    }

    @Override
    public void eat(Object food) {

        if (fullSaturation >= 0.0025) {
            return;
        }

        if (food instanceof Plantation plantation) {
            isEat = true;
            if (!isMovie && !isEat()) {
                if (fullSaturation < 0.0025) {
                    fullSaturation = 0.0025;
                }
            }
        }
        isEat = false;
    }

    @Override
    public void movie(char direction, int distance) {
        if (!isEat && !isReproduce) {
            fullSaturation -= 0.0005;
        }

    }

    @Override
    public String toString() {
        return icon;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
