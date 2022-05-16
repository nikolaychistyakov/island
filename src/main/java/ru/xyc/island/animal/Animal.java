package ru.xyc.island.animal;

import java.util.Objects;

public abstract class Animal implements Breedable, Eatable, Movable {

    public boolean isEat;
    public boolean isMovie;
    public boolean isReproduce;
    double weight;
    int travelSpeed;
    public double fullSaturation;
    public int canLiveAfterSaturation;

    public Animal(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        this.weight = weight;
        this.travelSpeed = travelSpeed;
        this.fullSaturation = fullSaturation;
        this.canLiveAfterSaturation = canLiveAfterSaturation;
    }

    public boolean isEat() {
        return isEat;
    }

    public void setEat(boolean eat) {
        isEat = eat;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public void setMovie(boolean movie) {
        isMovie = movie;
    }

    public boolean isReproduce() {
        return isReproduce;
    }

    public void setReproduce(boolean reproduce) {
        isReproduce = reproduce;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getTravelSpeed() {
        return travelSpeed;
    }

    public void setTravelSpeed(int travelSpeed) {
        this.travelSpeed = travelSpeed;
    }

    public double getFullSaturation() {
        return fullSaturation;
    }

    public void setFullSaturation(double fullSaturation) {
        this.fullSaturation = fullSaturation;
    }

    public int getCanLiveAfterSaturation() {
        return canLiveAfterSaturation;
    }

    public void setCanLiveAfterSaturation(int canLiveAfterSaturation) {
        this.canLiveAfterSaturation = canLiveAfterSaturation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return isEat == animal.isEat && isMovie == animal.isMovie && isReproduce == animal.isReproduce && Double.compare(animal.weight, weight) == 0 && travelSpeed == animal.travelSpeed && Double.compare(animal.fullSaturation, fullSaturation) == 0 && canLiveAfterSaturation == animal.canLiveAfterSaturation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEat, isMovie, isReproduce, weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }
}
