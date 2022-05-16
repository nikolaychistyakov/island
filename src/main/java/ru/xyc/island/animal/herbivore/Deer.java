package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Animal;

public class Deer extends Herbivore {


    public Deer(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Deer reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                this.fullSaturation -= 3;
                return new Deer(170, 3, 26, 4);
            }
        }
        isReproduce = false;
        return null;
    }

    @Override
    public void eat(Object food) {

        if (this.fullSaturation >= 26) {
            return;
        }

        if (food instanceof Animal animal) {
            isEat = true;
            if (!isMovie && !isEat()) {
                if (fullSaturation < 26) {
                    fullSaturation += animal.getWeight();
                    if (fullSaturation >= 26) {
                        fullSaturation = 26;
                    }
                }
            }
        }
        isEat = false;
    }

    @Override
    public void movie(char direction, int distance) {
        if (!isEat && !isReproduce) {
            fullSaturation -= 3;
        }

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
