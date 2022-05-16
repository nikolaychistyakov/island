package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.herbivore.*;

import java.util.Map;

public class Bear extends Predator {
    public Map<Class, Integer> diet = Map.of(
            Wolf.class, 20,
            Fox.class, 20,
            Eagle.class, 30,
            Horse.class, 70,
            Deer.class, 80,
            Hamster.class, 90,
            Sheep.class, 70,
            Goat.class, 70,
            Rabbit.class, 80,
            Duck.class, 85
    );

    public Bear(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Bear reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                this.fullSaturation -= 1;
                return new Bear(250, 2, 38, 15);
            }
        }
        isReproduce = false;
        return null;
    }

    @Override
    public void eat(Object food) {

        if (this.fullSaturation >= 38) {
            return;
        }

        if (food instanceof Animal animal) {
            isEat = true;
            if (!isMovie && !isEat()) {
                if (fullSaturation < 38) {
                    fullSaturation += animal.getWeight();
                    if (fullSaturation >= 38) {
                        fullSaturation = 38;
                    }
                }
            }
        }
        isEat = false;
    }

    @Override
    public void movie(char direction, int distance) {
        if (!isEat && !isReproduce) {
            fullSaturation -= 1;
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
