package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Animal;

public class Rabbit extends Herbivore {


    public Rabbit(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Rabbit reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                this.fullSaturation -= 0.05;
                return new Rabbit(3, 3, 0.45, 7);
            }
        }
        isReproduce = false;
        return null;
    }

    @Override
    public void eat(Object food) {

        if (this.fullSaturation >= 0.45) {
            return;
        }

        if (food instanceof Animal animal) {
            isEat = true;
            if (!isMovie && !isEat()) {
                if (fullSaturation < 0.45) {
                    fullSaturation += animal.getWeight();
                    if (fullSaturation >= 0.45) {
                        fullSaturation = 0.45;
                    }
                }
            }
        }
        isEat = false;
    }

    @Override
    public void movie(char direction, int distance) {
        if (!isEat && !isReproduce) {
            fullSaturation -= 0.05;
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
