package ru.xyc.island;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;
import ru.xyc.island.animal.herbivore.*;
import ru.xyc.island.animal.predator.*;

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
        List<Animal> collectHamster = animals.stream().filter(animal -> animal instanceof Hamster).toList();

        if (collectCaterpillar.size() >= 2) {
            for (int i = 0; i < collectCaterpillar.size() / 2; i++) {
                animals.add((Caterpillar) collectCaterpillar.get(i).reproduction());
            }
        }
        if (collectHamster.size() >= 2) {
            for (int i = 0; i < collectHamster.size() / 2; i++) {
                animals.add((Hamster) collectHamster.get(i).reproduction());
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

                        animal.movie('s', 1);

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
                if (animals.get(i) instanceof Herbivore) {
                    animals.get(i).eat(plantations.get(j));
                    plantations.remove(j);
                }

                if(animals.get(i) instanceof Wolf) {

                }

            }
        }


    }

    public void toDieOfOverflowOrHunger() {

        long countCaterpillar = animals.stream().filter(a -> a instanceof Caterpillar).count();
        long countHamster = animals.stream().filter(a -> a instanceof Hamster).count();
        if (countCaterpillar > 10000) {
            animals.removeIf(animal -> animal instanceof Caterpillar);
        }
        if (countHamster > 10000) {
            animals.removeIf(animal -> animal instanceof Hamster);
        }

        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i) != null && animals.get(i).fullSaturation <= 0) {
                animals.remove(i);
            }
        }


    }

    public String getTopAnimalOnCell() {
        long countPlantations = plantations.size();
        long countCaterpillar = animals.stream().filter(a -> a instanceof Caterpillar).count();
        long countHamster = animals.stream().filter(a -> a instanceof Hamster).count();
        long countDuck = animals.stream().filter(a -> a instanceof Duck).count();
        long countCow = animals.stream().filter(a -> a instanceof Cow).count();
        long countKangaroo = animals.stream().filter(a -> a instanceof Kangaroo).count();
        long countSheep = animals.stream().filter(a -> a instanceof Sheep).count();
        long countGoat = animals.stream().filter(a -> a instanceof Goat).count();
        long countRabbit = animals.stream().filter(a -> a instanceof Rabbit).count();
        long countDeer = animals.stream().filter(a -> a instanceof Deer).count();
        long countHorse = animals.stream().filter(a -> a instanceof Horse).count();
        long countEagle = animals.stream().filter(a -> a instanceof Eagle).count();
        long countSnake = animals.stream().filter(a -> a instanceof Snake).count();
        long countWolf = animals.stream().filter(a -> a instanceof Wolf).count();
        long countBear = animals.stream().filter(a -> a instanceof Bear).count();
        long countFox = animals.stream().filter(a -> a instanceof Fox).count();


        List<Long> longs = new ArrayList<>();
        longs.add(countCaterpillar);
        longs.add(countHamster);
        longs.add(countDuck);
        longs.add(countCow);
        longs.add(countSheep);
        longs.add(countGoat);
        longs.add(countRabbit);
        longs.add(countDeer);
        longs.add(countHorse);
        longs.add(countKangaroo);
        longs.add(countSnake);
        longs.add(countEagle);
        longs.add(countWolf);
        longs.add(countBear);
        longs.add(countFox);

        Collections.sort(longs);
        Collections.reverse(longs);

        long max = longs.get(0);

        if (max >= countPlantations) {
            if (countCaterpillar != 0 && max == countCaterpillar) {
                return "\uD83D\uDC1B" + countCaterpillar;
            }

            if (countHamster != 0 && max == countHamster) {
                return "\uD83D\uDC39" + countHamster;
            }

            if (countDuck != 0 && max == countDuck) {
                return "\uD83E\uDD86" + countDuck;
            }

            if (countCow != 0 && max == countCow) {
                return "\uD83D\uDC2E" + countCow;
            }

            if (countDeer != 0 && max == countDeer) {
                return "\uD83E\uDD85" + countDeer;
            }

            if (countRabbit != 0 && max == countRabbit) {
                return "\uD83D\uDC07" + countRabbit ;
            }
            if (countHorse != 0 & max == countHorse) {
                return "\uD83D\uDC0E" + countHorse;
            }
            if (countGoat != 0 && max == countGoat) {
                return  "\uD83D\uDC10" + countGoat;
            }
            if (countSheep != 0 && max == countSheep) {
                return  "\uD83D\uDC11" + countSheep;
            }
            if (countKangaroo != 0 && max == countKangaroo) {
                return "\uD83E\uDD98" + countKangaroo;
            }
            if (countEagle != 0 && max == countEagle) {
                return  "\uD83E\uDD85" + countEagle;
            }
            if (countSnake != 0 && max == countSnake) {
                return "\uD83D\uDC0D" + countSnake;
            }
            if (countBear != 0 && max == countBear) {
                return "\uD83D\uDC3B" + countBear;
            }
            if (countFox != 0 && max == countFox) {
                return  "\uD83E\uDD8A" + countFox;
            }
            if (countWolf != 0 && max == countWolf) {
                return "\uD83D\uDC3A" + countWolf;
            }


        } else {
            if (countPlantations != 0) {
                return "\uD83C\uDF40";
            } else {
                return "\u2796";
            }
        }

        return "\u2796";
    }

}
