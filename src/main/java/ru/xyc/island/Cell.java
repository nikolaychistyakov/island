package ru.xyc.island;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;
import ru.xyc.island.animal.herbivore.Caterpillar;
import ru.xyc.island.animal.predator.Wolf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Cell {
    private List<Animal> animals = Collections.synchronizedList(new ArrayList<>());
    private List<Plantation> plantations = new ArrayList<>();


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


        if(collectCaterpillar.size() >= 2) {

            for (int i = 0; i < collectCaterpillar.size() / 2; i++) {
                animals.add((Caterpillar) collectCaterpillar.get(i).reproduction());
            }

        }

    }

    public void toDieOfOverflow() {
        long countCaterpillar = animals.stream().filter(a -> a instanceof Caterpillar).count();
        if(countCaterpillar > 10000) {
            animals.removeIf(animal -> animal instanceof Caterpillar);
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

        if(max > countPlantations) {
            if (countCaterpillar != 0 && max == countCaterpillar) {
                return "\uD83D\uDC1B";
            }
            if (countWolf != 0 && max == countWolf) {
                return "\uD83D\uDC3A";
            }
        } else {
            if(countPlantations != 0) {
                return "\uD83C\uDF40";
            } else {
                return "\u26AA";
            }
        }

        return null;
    }

}
