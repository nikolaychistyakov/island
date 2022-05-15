package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;

public class Hamster extends Herbivore {

    public Hamster(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Hamster reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                fullSaturation -= 0.0005;
                return new Hamster(0.03,  1, 0.0075, 3);
            }
        }
        isReproduce = false;
        return null;
    }

    @Override
    public void eat(Object food) {
        if (fullSaturation >= 0.0075) {
            return;
        }

        if (food instanceof Plantation plantation) {
            isEat = true;
            if (!isMovie && !isEat()) {
                if (fullSaturation < 0.0075) {
                    fullSaturation = 0.0075;
                }
            }
        }
        isEat = false;
    }

    @Override
    public void movie(char direction, int distance) {

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
