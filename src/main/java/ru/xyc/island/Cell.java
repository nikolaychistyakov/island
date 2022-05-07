package ru.xyc.island;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;
import ru.xyc.island.animal.herbivore.Caterpillar;
import ru.xyc.island.animal.predator.Wolf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static ru.xyc.island.Main.island;

public class Cell {
    private List<Animal> animals = new CopyOnWriteArrayList<>();
    private List<Plantation> plantations = new CopyOnWriteArrayList<>();


    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plantation> getPlantations() {
        return plantations;
    }

    public void setPlantations(List<Plantation> plantations) {
        this.plantations = plantations;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }


    public void reproductionOnCell() {

        List<Animal> collectCaterpillar = animals.stream().filter(animal -> animal instanceof Caterpillar).toList();

        if (collectCaterpillar.size() >= 2) {

            for (int i = 0; i < collectCaterpillar.size() / 2; i++) {
                animals.add((Caterpillar) collectCaterpillar.get(i).reproduction());
            }
        }
    }

    public static void movie() {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {


                List<Animal> animals = island[i][j].getAnimals();

                for (int k = 0; k < animals.size(); k++) {

                    Animal animal = animals.get(k);

                    int random = (int) (Math.random() * 4);

                    if (animal != null && !animal.isEat && !animal.isReproduce) {
                        animal.isMovie = true;
                        animal.fullSaturation -= animal.fullSaturation / 10;
                        int travelSpeed = animal.getTravelSpeed();

                        if (random == 0) {
                            if (i - travelSpeed >= 0) {
                                island[i - travelSpeed][j].getAnimals().add(animal);
                                island[i][j].getAnimals().remove(animal);
                            }
                        }
                        if (random == 1) {
                            if (j + travelSpeed <= island[i].length) {
                                island[i][j + travelSpeed].getAnimals().add(animal);
                                island[i][j].getAnimals().remove(animal);
                            }
                        }
                        if (random == 2) {
                            if (i + travelSpeed <= island.length) {
                                island[i + travelSpeed][j].getAnimals().add(animal);
                                island[i][j].getAnimals().remove(animal);
                            }
                        }
                        if (random == 3) {
                            if (j - travelSpeed >= 0) {
                                island[i][j - travelSpeed].getAnimals().add(animal);
                                island[i][j].getAnimals().remove(animal);
                            }
                        }
                        animal.isMovie = false;
                    }
                }
            }
        }
    }

    public void eatOnCell() {

        for (int i = 0; i < animals.size(); i++) {
            for (int j = 0; j < plantations.size(); j++) {
                    animals.get(i).eat(plantations.get(j));
                    plantations.remove(j);
            }
        }


    }

    public void toDieOfOverflowOrGolod() {

        long countCaterpillar = animals.stream().filter(a -> a instanceof Caterpillar).count();
        if (countCaterpillar > 10000) {
            animals.removeIf(animal -> animal instanceof Caterpillar);
        }

        for (int i = 0; i < animals.size(); i++) {
            if(animals.get(i) != null && animals.get(i).fullSaturation <= 0) {
                animals.remove(i);
            }
        }


    }

    public String getTopAnimalOnCell() {
        long countPlantations = plantations.size();
        long countCaterpillar = animals.stream().filter(a -> a instanceof Caterpillar).count();
        long countWolf = animals.stream().filter(a -> a instanceof Wolf).count();

        List<Long> longs = new ArrayList<>();
        longs.add(countCaterpillar);
        longs.add(countWolf);

        Collections.sort(longs);
        Collections.reverse(longs);

        long max = longs.get(0);

        if (max >= countPlantations) {
            if (countCaterpillar != 0 && max == countCaterpillar) {
                return "\uD83D\uDC1B" + countCaterpillar;
            }
            if (countWolf != 0 && max == countWolf) {
                return "\uD83D\uDC3A" + countWolf;
            }
        } else {
            if (countPlantations != 0) {
                return "\uD83C\uDF40";
            } else {
                return "\u26AA";
            }
        }

        return  "\u26AA";
    }

}
