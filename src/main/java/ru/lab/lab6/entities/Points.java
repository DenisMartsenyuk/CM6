package ru.lab.lab6.entities;

import java.util.ArrayList;
import java.util.List;

public class Points {
    private List<Double> pointsX;
    private List<Double> pointsY;

    public Points() {
        pointsX = new ArrayList<>();
        pointsY = new ArrayList<>();
    }

    public void addPoint(double x, double y) {
        pointsX.add(x);
        pointsY.add(y);
    }

    public int getSize() {
        return pointsX.size();
    }

    public List<Double> getPointsX() {
        return pointsX;
    }

    public List<Double> getPointsY() {
        return pointsY;
    }

    public double getMinX() {
        return pointsX.get(0);
    }

    public double getMaxX() {
        return pointsX.get(pointsX.size() - 1);
    }
}
