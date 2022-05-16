package ru.xyc.island.animal.predator;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.herbivore.Caterpillar;
import ru.xyc.island.animal.herbivore.Duck;
import ru.xyc.island.animal.herbivore.Hamster;
import ru.xyc.island.animal.herbivore.Rabbit;

import java.util.Map;

public class Eagle extends Predator {

    public Map<Class, Integer> diet = Map.of(
            Caterpillar.class, 20,
            Snake.class, 50,
            Hamster.class, 90,
            Rabbit.class, 90,
            Duck.class, 85
    );

    public Eagle(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Eagle reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                this.fullSaturation -= 0.1;
                return new Eagle(6, 4, 1, 5);
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
