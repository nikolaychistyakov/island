package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.herbivore.Caterpillar;
import ru.xyc.island.animal.herbivore.Duck;
import ru.xyc.island.animal.herbivore.Hamster;
import ru.xyc.island.animal.herbivore.Rabbit;

import java.util.Map;

public class Snake extends Predator {

    public Map<Class, Integer> diet = Map.of(
            Rabbit.class, 50,
            Hamster.class, 90,
            Duck.class, 50,
            Caterpillar.class, 90
    );

    public Snake(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Snake reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                this.fullSaturation -= 0.05;
                return new Snake(2, 1, 0.3, 15);
            }
        }
        isReproduce = false;
        return null;
    }

    @Override
    public void eat(Object food) {

        if (this.fullSaturation >= 0.3) {
            return;
        }

        if (food instanceof Animal animal) {
            isEat = true;
            if (!isMovie && !isEat()) {
                if (fullSaturation < 0.3) {
                    fullSaturation += animal.getWeight();
                    if (fullSaturation >= 0.3) {
                        fullSaturation = 0.3;
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

    @Override
    public String toString() {
        return "Snake{" +
                "fullSaturation=" + fullSaturation +
                ", canLiveAfterSaturation=" + canLiveAfterSaturation +
                '}';
    }
}
