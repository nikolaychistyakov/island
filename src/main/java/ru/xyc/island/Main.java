package ru.xyc.island;

import ru.xyc.island.animal.Animal;
import ru.xyc.island.animal.Plantation;
import ru.xyc.island.animal.herbivore.*;
import ru.xyc.island.animal.predator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Main {

    static int gameBeat = 0;
    static int count;
    static int countPlant;
    static public Cell[][] island;

    public static void main(String[] args) {

        island = new Cell[20][80];

        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                island[i][j] = new Cell();
            }
        }

        island[10][45] = addAnimals();

        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        ExecutorService executorService = Executors.newFixedThreadPool(4);


        while (true) {
            gameBeat++;
            scheduledExecutorService.submit(() -> {

                System.out.println("Такт:" + gameBeat + " | " + " Животных: " + getAllAnimal() + " | "
                        + "Растений: " + getAllPlantations());
                count = 0;
                countPlant = 0;
            });

            scheduledExecutorService.submit(Main::plantGrowth);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            showIsland();

            executorService.submit(() -> {

                for (int i = 0; i < island.length; i++) {
                    for (int j = 0; j < island[i].length; j++) {
                        island[i][j].toDieOfOverflowOrHunger();
                    }
                }
            });

            executorService.submit(Cell::movieAnimalsAroundTheIsland);

            executorService.submit(() -> {

                for (int i = 0; i < island.length; i++) {
                    for (int j = 0; j < island[i].length; j++) {
                        island[i][j].eatingInCell();
                    }
                }
            });

            executorService.submit(() -> {

                for (int i = 0; i < island.length; i++) {
                    for (int j = 0; j < island[i].length; j++) {
                        island[i][j].reproductionInCell();
                    }
                }
            });
        }
    }

    private static void showIsland() {
        for (Cell[] cells : island) {
            for (Cell cell : cells) {
                System.out.print(cell.showPopularAnimalInCell());
            }
            System.out.println();

        }
    }

    private static int getAllAnimal() {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                for (int k = 0; k < island[i][j].getAnimals().size(); k++) {
                    if (island[i][j].getAnimals().get(k) != null) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static int getAllPlantations() {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                for (int k = 0; k < island[i][j].getPlantations().size(); k++) {
                    if (island[i][j].getPlantations().get(k) != null) {
                        countPlant++;
                    }
                }
            }
        }
        return countPlant;
    }

    private static void plantGrowth() {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                for (int k = 0; k < 3; k++) {
                    if (island[i][j].getPlantations().size() == 0) {
                        island[i][j].getPlantations().add(new Plantation(1, 10000));
                    }
                }
            }
        }
    }

    private static Cell addAnimals() {
        Cell cell = new Cell();

        List<Animal> animals = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            animals.add(new Hamster(0.03, 1, 0.0075, 3));
        }
        for (int i = 0; i < 50; i++) {
            animals.add(new Caterpillar(0.01, 1, 0.0025, 1));
        }

        for (int k = 0; k < 5; k++) {
            animals.add(new Duck(1, 1, 0.15, 4));
            animals.add(new Cow(350, 1, 53, 4));
            animals.add(new Deer(170, 3, 26, 4));
            animals.add(new Goat(65, 1, 10, 5));
            animals.add(new Horse(300, 3, 45, 5));
            animals.add(new Kangaroo(47, 2, 7, 8));
            animals.add(new Rabbit(3, 3, 0.45, 7));
            animals.add(new Sheep(45, 1, 7, 5));
            animals.add(new Wolf(50, 3, 8, 2));
            animals.add(new Eagle(6, 4, 1, 5));
            animals.add(new Bear(250, 2, 38, 15));
            animals.add(new Fox(4, 3, 1, 8));
            animals.add(new Snake(2, 1, 0.3, 15));

        }

        cell.setAnimals(animals);

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

