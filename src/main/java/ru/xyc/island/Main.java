package ru.xyc.island;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;
import ru.xyc.island.animal.herbivore.*;
import ru.xyc.island.animal.predator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static int gameBeat = 0;

    static public Cell[][] island;

    public static void main(String[] args) {

        island = new Cell[20][80];

        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                island[i][j] = new Cell();
            }
        }

        ExecutorService serviceStatistic = Executors.newFixedThreadPool(1);
        ExecutorService executorService = Executors.newFixedThreadPool(4);


        Cell cell = addRandomAnimals();

        island[10][10] = cell;

        while (true) {

            gameBeat++;

            //statistics
            serviceStatistic.submit(() -> {
                System.out.println("Ход:" + gameBeat + " | " + "Всего животных: "
                        + getAllAnimal() + " | " + "Растений: " + getAllPlantations());

                showIsland();

            });
            //***
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executorService.submit(Cell::movie);

            executorService.submit(() -> {

                for (int i = 0; i < island.length; i++) {
                    for (int j = 0; j < island[i].length; j++) {
                        island[i][j].eatOnCell();
                    }
                }
            });

            executorService.submit(() -> {

                for (int i = 0; i < island.length; i++) {
                    for (int j = 0; j < island[i].length; j++) {
                        island[i][j].toDieOfOverflowOrHunger();
                    }
                }
            });

            executorService.submit(() -> {

                for (int i = 0; i < island.length; i++) {
                    for (int j = 0; j < island[i].length; j++) {
                        island[i][j].reproductionOnCell();
                    }
                }
            });

        }
    }

    private static void showIsland() {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                System.out.print(island[i][j].getTopAnimalOnCell());
            }
            System.out.println();

        }
    }

    private static int getAllAnimal() {
        int count = 0;
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                count += island[i][j].getAnimals().size();
            }
        }
        return count;
    }

    private static int getAllPlantations() {
        int count = 0;
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                count += island[i][j].getPlantations().size();
            }
        }
        return count;
    }

    private static Cell addRandomAnimals() {
        Cell cell = new Cell();

        List<Animal> animals = new ArrayList<>();
        List<Plantation> plantations = new ArrayList<>();

        plantations.add(new Plantation(1, 10000));


        for (int k = 0; k < 5; k++) {
            animals.add(new Hamster("\uD83D\uDC39", 0.03, 10000, 1, 0.0075, 3));
            animals.add(new Duck("\uD83E\uDD86", 1, 500, 1, 0.15, 4));
            animals.add(new Cow( "\uD83D\uDC2E", 350, 20, 1, 53, 4));
            animals.add(new Deer("\uD83E\uDD8C", 170, 41, 3, 26, 4));
            animals.add(new Goat( "\uD83D\uDC10", 65, 107, 1, 10, 5));
            animals.add(new Horse("\uD83D\uDC0E", 300, 23, 3, 45, 5));
            animals.add(new Kangaroo("\uD83E\uDD98", 47, 149, 2, 7, 8));
            animals.add(new Rabbit("\uD83D\uDC07", 3, 750, 3, 0.45, 7));
            animals.add(new Sheep("\uD83D\uDC11", 45, 156, 1, 7, 5));
            animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.0025, 1));
            animals.add(new Wolf("\uD83D\uDC3A", 50, 30, 3, 8, 10));
            animals.add(new Eagle("\uD83E\uDD85", 6, 166, 4, 1, 5));
            animals.add(new Bear("\uD83D\uDC3B", 250, 7, 2, 38, 15));
            animals.add(new Fox("\uD83E\uDD8A", 4, 50, 3, 1, 8));
            animals.add(new Snake("\uD83D\uDC0D", 2, 123, 1, 0.3, 15));

        }


        cell.setAnimals(animals);
        cell.setPlantations(plantations);

        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                for (int k = 0; k < 1; k++) {
                    island[i][j].getPlantations().add(new Plantation(1, 10000));
                }
            }
        }


        return cell;
    }
}

//    while(true){
//
//            showIsland();
//
//            executorService.submit(Cell::movie);
//            }

