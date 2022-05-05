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

    public static void main(String[] args) {

        Cell[][] island = new Cell[5][10];

        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                island[i][j] = new Cell();
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(1);





        Cell cell = new Cell();

        List<Animal> animals = new ArrayList<>();
        List<Plantation> plantations = new ArrayList<>();

        plantations.add(new Plantation(1,10000));



        animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1));
        animals.add(new Caterpillar("\uD83D\uDC1B", 0.01, 10000, 1, 0.15, 1));
        animals.add(new Wolf("\uD83D\uDC3A", 50, 30, 3, 8, 10));
        animals.add(new Wolf("\uD83D\uDC3A", 50, 30, 3, 8, 10));
        animals.add(new Wolf("\uD83D\uDC3A", 50, 30, 3, 8, 10));

        cell.setAnimals(animals);
        cell.setPlantations(plantations);

        List<Plantation> plantations1 = new ArrayList<>();

        plantations1.add(new Plantation(1,10000));
        Cell cell1 = new Cell();
        cell1.setPlantations(plantations1);

        island[0][0] = cell;
        island[2][5] = cell1;


        while (true) {

            gameBeat++;

            try {
                Thread.sleep(1000);

                //statistics
                executorService.submit(() -> {
                    int allAnimals = 0;

                    for (int k = 0; k < island.length; k++) {
                        for (int k1 = 0; k1 < island[k].length; k1++) {
                            allAnimals += island[k][k1].getAnimals().size();
                        }
                    }

                    System.out.println("Всего животных: " + allAnimals + " | " + "Такт: " + gameBeat);
                    System.out.println("---------------------");
                });
                //***


                executorService.submit(() -> {
                    for (int i = 0; i < island.length; i++) {
                        for (int j = 0; j < island[i].length; j++) {

                            island[i][j].toDieOfOverflow();

                            // show topAnimal
                            System.out.print(island[i][j].getTopAnimalOnCell());

                            island[i][j].reproductionOnCell();

                        }
                        System.out.println();
                    }

                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }

    }
}
