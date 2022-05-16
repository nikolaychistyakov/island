package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.herbivore.*;

import java.util.Map;

public class Fox extends Predator {

    public Map<Class, Integer> diet = Map.of(
            Kangaroo.class, 5,
            Deer.class, 5,
            Eagle.class, 10,
            Snake.class, 20,
            Goat.class, 20,
            Sheep.class, 20,
            Rabbit.class, 70,
            Duck.class, 80,
            Hamster.class, 90

//            Fox.class, 10,
//            Horse.class, 30,
    );


    public Fox(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Fox reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                this.fullSaturation -= 0.1;
                return new Fox(4, 3, 1, 8);
            }
        }
        isReproduce = false;
        return null;
    }

    @Override
    public void eat(Object food) {

        if (this.fullSaturation >= 1) {
            return;
        }

        if (food instanceof Animal animal) {
            isEat = true;
            if (!isMovie && !isEat()) {
                if (fullSaturation < 1) {
                    fullSaturation += animal.getWeight();
                    if (fullSaturation >= 1) {
                        fullSaturation = 1;
                    }
                }
            }
        }
        isEat = false;
    }

    @Override
    public void movie(char direction, int distance) {
        if (!isEat && !isReproduce) {
            fullSaturation -= 0.1;
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
