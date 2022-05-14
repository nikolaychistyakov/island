package ru.xyc.island;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;
import ru.xyc.island.animal.herbivore.Caterpillar;
import ru.xyc.island.animal.herbivore.Hamster;
import ru.xyc.island.animal.herbivore.Herbivore;
import ru.xyc.island.animal.predator.Wolf;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

        Map<String, Integer> countAnimalsOnCell = getCountAnimalsOnCell();

        for (Map.Entry<String, Integer> countAnimals: countAnimalsOnCell.entrySet()) {
            if(countAnimals.getValue() >= 2) {
                for (int i = 0; i < animals.size(); i++) {
                    if(animals.get(i).getClass().getSimpleName().equals(countAnimals.getKey())) {
                        animals.add((Animal) animals.get(i).reproduction());
                    }
                }
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

                if (animals.get(i) instanceof Wolf) {

                }
            }
        }
    }

    public void toDieOfOverflowOrHunger() {

        Map<String, Integer> countAnimalsOnCell = getCountAnimalsOnCell();

        Map<String, Integer> getMax = getMaxOnCell();

        //overflow
        for (Map.Entry<String, Integer> countAnimals : countAnimalsOnCell.entrySet()) {
            for (Map.Entry<String, Integer> maxAnimals : getMax.entrySet()) {
                if(countAnimals.getKey().equals(maxAnimals.getKey())) {
                    if (countAnimals.getValue() > maxAnimals.getValue()) {
                        String simpleName = countAnimals.getKey().getClass().getSimpleName();
                        animals.removeIf(animal -> animal.getClass().getSimpleName().equals(simpleName));
                    }
                }
            }
        }
        //hunger
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i) != null && animals.get(i).fullSaturation <= 0) {
                animals.remove(i);
            }
        }
    }


    public String showMaxCountAnimalsOnCell() {

        Map<String, Integer> map = getCountAnimalsOnCell();

        int max = map.values().stream().max(Integer::compareTo).orElse(0);
        String s = "?";
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            if (m.getValue() == max) {
                s = m.getKey();
            }
        }

        long countPlantations = plantations.size();


        if (animals.size() > 0) {
            Map<String, String> icon = getIcon();
            for (Map.Entry<String, String> m : icon.entrySet()) {
                if (m.getKey().equals(s)) {
                    return m.getValue() + max;
                }
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

    private Map<String, Integer> getCountAnimalsOnCell() {
        Map<String, Integer> map = new ConcurrentHashMap<>();

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal != null) {
                String name = animal.getClass().getSimpleName();
                if (!map.containsKey(name)) {
                    map.put(name, 1);
                } else {
                    map.put(name, map.get(name) + 1);
                }
            }
        }
        return map;
    }

    private Map<String, String> getIcon() {
        Map<String, String> iconsAnimals = new ConcurrentHashMap<>();
        iconsAnimals.put("Hamster", "\uD83D\uDC39");
        iconsAnimals.put("Duck", "\uD83E\uDD86");
        iconsAnimals.put("Cow", "\uD83D\uDC2E");
        iconsAnimals.put("Deer", "\uD83E\uDD8C");
        iconsAnimals.put("Goat", "\uD83D\uDC10");
        iconsAnimals.put("Horse", "\uD83D\uDC0E");
        iconsAnimals.put("Kangaroo", "\uD83E\uDD98");
        iconsAnimals.put("Rabbit", "\uD83D\uDC07");
        iconsAnimals.put("Sheep", "\uD83D\uDC11");
        iconsAnimals.put("Caterpillar", "\uD83D\uDC1B");
        iconsAnimals.put("Wolf", "\uD83D\uDC3A");
        iconsAnimals.put("Eagle", "\uD83E\uDD85");
        iconsAnimals.put("Bear", "\uD83D\uDC3B");
        iconsAnimals.put("Fox", "\uD83E\uDD8A");
        iconsAnimals.put("Snake", "\uD83D\uDC0D");

        return iconsAnimals;
    }

    private Map<String, Integer> getMaxOnCell() {
        Map<String, Integer> getMaxAnimalsOnCell = new ConcurrentHashMap<>();
        getMaxAnimalsOnCell.put("Hamster", 10000);
        getMaxAnimalsOnCell.put("Duck", 500);
        getMaxAnimalsOnCell.put("Cow", 20);
        getMaxAnimalsOnCell.put("Deer", 41);
        getMaxAnimalsOnCell.put("Goat", 107);
        getMaxAnimalsOnCell.put("Horse", 23);
        getMaxAnimalsOnCell.put("Kangaroo", 149);
        getMaxAnimalsOnCell.put("Rabbit", 750);
        getMaxAnimalsOnCell.put("Sheep", 156);
        getMaxAnimalsOnCell.put("Caterpillar", 10000);
        getMaxAnimalsOnCell.put("Wolf", 30);
        getMaxAnimalsOnCell.put("Eagle", 166);
        getMaxAnimalsOnCell.put("Bear", 7);
        getMaxAnimalsOnCell.put("Fox", 50);
        getMaxAnimalsOnCell.put("Snake", 123);

        return getMaxAnimalsOnCell;
    }
}