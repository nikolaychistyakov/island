package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Animal;

public class Duck extends Herbivore {


    public Duck(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Duck reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                this.fullSaturation -= 0.025;
                return new Duck(1, 1, 0.15, 4);
            }
        }
        isReproduce = false;
        return null;
    }

    @Override
    public void eat(Object food) {

        if (this.fullSaturation >= 8) {
            return;
        }

        if (food instanceof Animal animal) {
            isEat = true;
            if (!isMovie && !isEat()) {
                if (fullSaturation < 0.15) {
                    fullSaturation += animal.getWeight();
                    if (fullSaturation >= 0.15) {
                        fullSaturation = 0.15;
                    }
                }
            }
        }
        isEat = false;
    }

    @Override
    public void movie(char direction, int distance) {
        if (!isEat && !isReproduce) {
            fullSaturation -= 0.025;
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

    @Override
    public String toString() {
        return "\uD83E\uDD86";
    }
}
