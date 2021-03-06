package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.herbivore.*;

import java.util.Map;

public class Wolf extends Predator {

    public Map<Class, Integer> diet = Map.of(
            Snake.class, 10,
            Fox.class, 10,
            Eagle.class, 10,
            Kangaroo.class, 20,
            Horse.class, 30,
            Deer.class, 40,
            Goat.class, 60,
            Sheep.class, 70,
            Rabbit.class, 70,
            Duck.class, 80
    );

    public Wolf(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Wolf reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                this.fullSaturation -= 1;
                return new Wolf(50, 3, 8, 10);
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
                if (fullSaturation < 8) {
                    fullSaturation += animal.getWeight();
                    if (fullSaturation >= 8) {
                        fullSaturation = 8;
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "fullSaturation=" + fullSaturation +
                ", canLiveAfterSaturation=" + canLiveAfterSaturation +
                '}';
    }
}
