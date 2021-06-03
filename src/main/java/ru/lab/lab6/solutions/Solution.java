package ru.lab.lab6.solutions;

import ru.lab.lab6.entities.InputData;
import ru.lab.lab6.entities.Points;

import java.util.List;

public abstract class Solution {
    public abstract Points solveByH(Double h, int n, InputData inputData);
    public abstract Double getOrder();
    public abstract String getName();

    public Double getAccuracy(Points previousPoints, Points currentPoints) {
        List<Double> previousPointsY = previousPoints.getPointsY();
        List<Double> currentPointsY = currentPoints.getPointsY();
        Double accuracy = Double.MIN_VALUE;
        for (int i = 0; i < previousPointsY.size(); ++i) {
            Double currentDifference = Math.abs(previousPointsY.get(i) - currentPointsY.get(2 * i)) / (Math.pow(2, getOrder()) - 1);
            accuracy = Math.max(accuracy, currentDifference);
        }
        return accuracy;
    }

    public Points solve(InputData inputData) {
//        Points currentPoints = solveByH(0.0625, 33, inputData);
        Double a = inputData.getA();
        Double b = inputData.getB();
        Double h = (b - a) / 2;
        int n = (int) Math.round((b - a) / h);
        Points previousPoints = solveByH(h, n, inputData);
        h /= 2;
        n = (int) Math.round((b - a) / h);
        Points currentPoints = solveByH(h, n, inputData); //todo заменить на h
        while (getAccuracy(previousPoints, currentPoints) > inputData.getEps()) {
            previousPoints = currentPoints;
            h /= 2;
            n = (int) Math.round((b - a) / h);
            currentPoints = solveByH(h, n, inputData);
        }
        return currentPoints;
    }
}
