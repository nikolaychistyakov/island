package ru.xyc.island.animal.herbivore;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;

public class Hamster extends Herbivore {
    public Hamster(String icon, double weight, int maximumNumberOfAnimals, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(icon, weight, maximumNumberOfAnimals, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Hamster reproduction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            if (!isEat && !isMovie()) {
                isReproduce = true;
                fullSaturation -= 0.0005;
                return new Hamster("\uD83D\uDC39", 0.03, 10000, 1, 0.0075, 3);
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
}
