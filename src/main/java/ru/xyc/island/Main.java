package ru.xyc.island;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;
import ru.xyc.island.animal.herbivore.Caterpillar;
import ru.xyc.island.animal.predator.Wolf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static int gameBeat = 0;

    static public Cell[][] island;

    public static void main(String[] args) {

        island = new Cell[20][20];

        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                island[i][j] = new Cell();
            }
        }

        ExecutorService serviceStatistic = Executors.newFixedThreadPool(1);
        ExecutorService executorService = Executors.newFixedThreadPool(1);


        Cell cell = addRandomAnimals();

        island[10][10] = cell;

        while (true) {

            gameBeat++;

            try {

                Thread.sleep(1000);

                //statistics
                serviceStatistic.submit(() -> {

                    System.out.println("Ход:" + gameBeat + " | " + "Всего животных: "
                            + getAllAnimal() +  " | " + "Растений: " + getAllPlantations());
                    System.out.println("---------------------");
                });
                //***
                Thread.sleep(1000);

                executorService.submit(Cell::movie);

                executorService.submit(() -> {

                    for (int i = 0; i < island.length; i++) {
                        for (int j = 0; j < island[i].length; j++) {
                            island[i][j].toDieOfOverflowOrGolod();
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

                executorService.submit(() -> {

                    for (int i = 0; i < island.length; i++) {
                        for (int j = 0; j < island[i].length; j++) {
                            island[i][j].eatOnCell();
                        }
                    }
                });

                showIsland();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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


        animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1));
        animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1));
        animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1));
        animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1));
        animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1));
        animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1));
        animals.add(new Wolf("\uD83D\uDC3A", 50, 30, 3, 8, 10));
        animals.add(new Wolf("\uD83D\uDC3A", 50, 30, 3, 8, 10));
        animals.add(new Wolf("\uD83D\uDC3A", 50, 30, 3, 8, 10));

        cell.setAnimals(animals);
        cell.setPlantations(plantations);

                for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                for (int k = 0; k < 15; k++) {
                    island[i][j].setPlantations( new ArrayList<>(List.of(new Plantation(1, 10000))));
                }
            }
        }


        return cell;
    }
}
