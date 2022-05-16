package ru.xyc.island;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;
import ru.xyc.island.animal.herbivore.Herbivore;
import ru.xyc.island.animal.predator.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

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


    public void reproductionInCell() {

        Map<String, Integer> countAnimalsOnCell = getCountAnimalsInCell();
        for (Map.Entry<String, Integer> countAnimals : countAnimalsOnCell.entrySet()) {
            if (countAnimals.getValue() >= 2) {
                for (int i = 0; i < animals.size(); i++) {
                    if (animals.get(i).getClass().getSimpleName().equals(countAnimals.getKey())) {
                        animals.add((Animal) animals.get(i).reproduction());
                    }
                }
            }
        }
    }

    public static void movieAnimalsAroundTheIsland() {

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

    public void eatingInCell() {

        for (int i = 0; i < animals.size(); i++) {
            for (int j = 0; j < plantations.size(); j++) {
                if (animals.get(i) instanceof Herbivore) {
                    animals.get(i).eat(plantations.get(j));
                    animals.get(i).fullSaturation += 1;

                    plantations.remove(plantations.get(j));
                }
            }
        }

        for (int i = 0; i < animals.size(); i++) {
            Animal animal1 = animals.get(i);
            for (int j = 0; j < animals.size(); j++) {
                Animal animal2 = animals.get(j);
                int random = ThreadLocalRandom.current().nextInt(100);
                if (animal1 instanceof Wolf wolf) {
                    for (Map.Entry<Class, Integer> diet : wolf.diet.entrySet()) {
                        if (animal2.getClass().getSimpleName().equals(diet.getKey().getSimpleName())) {
                            if (diet.getValue() < random) {
                                animal1.eat(animal2);
                                animals.remove(animal2);
                            }
                        }
                    }
                }
                if (animal1 instanceof Bear bear) {
                    for (Map.Entry<Class, Integer> diet : bear.diet.entrySet()) {
                        if (animal2.getClass().getSimpleName().equals(diet.getKey().getSimpleName())) {
                            if (diet.getValue() < random) {
                                animal1.eat(animal2);
                                animals.remove(animal2);
                            }
                        }
                    }
                }
                if (animal1 instanceof Eagle eagle) {
                    for (Map.Entry<Class, Integer> diet : eagle.diet.entrySet()) {
                        if (animal2.getClass().getSimpleName().equals(diet.getKey().getSimpleName())) {
                            if (diet.getValue() < random) {
                                animal1.eat(animal2);
                                animals.remove(animal2);
                            }
                        }
                    }
                }
                if (animal1 instanceof Fox fox) {
                    for (Map.Entry<Class, Integer> diet : fox.diet.entrySet()) {
                        if (animal2.getClass().getSimpleName().equals(diet.getKey().getSimpleName())) {
                            if (diet.getValue() < random) {
                                animal1.eat(animal2);
                                animals.remove(animal2);
                            }
                        }
                    }
                }
                if (animal1 instanceof Snake snake) {
                    for (Map.Entry<Class, Integer> diet : snake.diet.entrySet()) {
                        if (animal2.getClass().getSimpleName().equals(diet.getKey().getSimpleName())) {
                            if (diet.getValue() < random) {
                                animal1.eat(animal2);
                                animals.remove(animal2);
                            }
                        }
                    }
                }
            }
        }
    }


    public void toDieOfOverflowOrHunger() {

        //remove null
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i) == null) {
                animals.remove(i);
            }
        }

        Map<String, Integer> countAnimalsOnCell = getCountAnimalsInCell();

        Map<String, Integer> getMax = getMaxAnimalsOnCell();

        //overflow
        for (Map.Entry<String, Integer> countAnimals : countAnimalsOnCell.entrySet()) {
            for (Map.Entry<String, Integer> maxAnimals : getMax.entrySet()) {
                if (countAnimals.getKey().equals(maxAnimals.getKey())) {
                    if (countAnimals.getValue() >= maxAnimals.getValue()) {
                        animals.removeIf(animal -> animal.getClass().getSimpleName()
                                .equalsIgnoreCase(countAnimals.getKey()));
                    }
                }
            }
        }
        //hunger
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal != null && animal.fullSaturation <= 0) {
                animal.canLiveAfterSaturation -= 1;
            }
            if (animal != null && animal.getCanLiveAfterSaturation() <= 0) {
                animals.remove(animal);
            }
        }
    }


    public String showPopularAnimalInCell() {

        Map<String, Integer> map = getCountAnimalsInCell();

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
                    return m.getValue();
                }
            }
        } else {
            if (countPlantations != 0) {
                return "\uD83C\uDF3F";
            } else {
                return "\u2796";
            }
        }
        return "\u2796";
    }

    private Map<String, Integer> getCountAnimalsInCell() {
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

    private Map<String, Integer> getMaxAnimalsOnCell() {
        Map<String, Integer> maxAnimalsOnCell = new ConcurrentHashMap<>();
        maxAnimalsOnCell.put("Hamster", 10000);
        maxAnimalsOnCell.put("Duck", 500);
        maxAnimalsOnCell.put("Cow", 20);
        maxAnimalsOnCell.put("Deer", 41);
        maxAnimalsOnCell.put("Goat", 107);
        maxAnimalsOnCell.put("Horse", 23);
        maxAnimalsOnCell.put("Kangaroo", 149);
        maxAnimalsOnCell.put("Rabbit", 750);
        maxAnimalsOnCell.put("Sheep", 156);
        maxAnimalsOnCell.put("Caterpillar", 10000);
        maxAnimalsOnCell.put("Wolf", 30);
        maxAnimalsOnCell.put("Eagle", 166);
        maxAnimalsOnCell.put("Bear", 7);
        maxAnimalsOnCell.put("Fox", 50);
        maxAnimalsOnCell.put("Snake", 123);

        return maxAnimalsOnCell;
    }
}