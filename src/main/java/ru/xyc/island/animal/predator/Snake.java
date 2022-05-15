package ru.xyc.island.animal.predator;

public class Snake extends Predator{

    public Snake(double weight, int travelSpeed, double fullSaturation, int canLiveAfterSaturation) {
        super(weight, travelSpeed, fullSaturation, canLiveAfterSaturation);
    }

    @Override
    public Snake reproduction() {
return null;
    }

    @Override
    public void eat(Object obj) {

    }

    @Override
    public void movie(char direction, int distance) {

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Snake{" +
                "fullSaturation=" + fullSaturation +
                ", canLiveAfterSaturation=" + canLiveAfterSaturation +
                '}';
    }
}
