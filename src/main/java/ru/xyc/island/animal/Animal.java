package ru.xyc.island.animal;

import ru.xyc.island.Breedable;
import ru.xyc.island.Eatable;
import ru.xyc.island.Movable;

import java.util.Objects;

public abstract class Animal implements Breedable, Eatable, Movable {

    public boolean isEat;
    public boolean isMovie;
    public boolean isReproduce;
    public String icon;
    public double weight;
    public int maximumNumberOfAnimals;
    public int travelSpeed;
    public double fullSaturation;
    public int canLiveAfterSaturation;

    public Animal(String icon, double weight, int maximumNumberOfAnimals, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        this.icon = icon;
        this.weight = weight;
        this.maximumNumberOfAnimals = maximumNumberOfAnimals;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaximumNumberOfAnimals() {
        return maximumNumberOfAnimals;
    }

    public void setMaximumNumberOfAnimals(int maximumNumberOfAnimals) {
        this.maximumNumberOfAnimals = maximumNumberOfAnimals;
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
        return Double.compare(animal.weight, weight) == 0 && maximumNumberOfAnimals == animal.maximumNumberOfAnimals && travelSpeed == animal.travelSpeed && Double.compare(animal.fullSaturation, fullSaturation) == 0 && canLiveAfterSaturation == animal.canLiveAfterSaturation && Objects.equals(icon, animal.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icon, weight, maximumNumberOfAnimals, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }
}
