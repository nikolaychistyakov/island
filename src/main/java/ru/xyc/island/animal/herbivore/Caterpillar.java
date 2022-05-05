package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;

public class Caterpillar extends Herbivore {


    public Caterpillar(String icon,double weight, int maximumNumberOfAnimals, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(icon,weight, maximumNumberOfAnimals, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Caterpillar reproduction() {
        if(!isEat && !isMovie()) {
            return new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1);
        } else {
            return null;
        }
    }

    @Override
    public void eat(Object food) {

        if(food instanceof Plantation plantation) {
            if (!isMovie && !isEat()) {
                isEat = true;
                if (fullSaturation < 0.0025) {
                    fullSaturation += plantation.getWeight();
                }
                if (fullSaturation > 0.0025) {
                    fullSaturation = 0.0025;
                }
            }
            isEat = false;
        }
    }

    @Override
    public void movie(char direction, int distance) {
            if(!isEat && !isReproduce) {

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
