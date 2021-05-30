package ru.lab.lab6.solutions;

import ru.lab.lab6.entities.InputData;
import ru.lab.lab6.entities.Points;
import ru.lab.lab6.functions.Function;

public class SolutionRungeKutt4 extends Solution {

    @Override
    public Points solveByH(Double h, InputData inputData) {
        Double a = inputData.getA();
        Double b = inputData.getB();
        int n = (int) Math.round((b - a) / h);
        Function function = inputData.getFunction();
        Points points = new Points();
        Double currentX = a;
        points.addPoint(currentX, inputData.getY0());
        currentX += h;
        int counter = 0;
        for (int i = 1; i <= n; ++i) {
            Double previousX = points.getPointsX().get(counter);
            Double previousY = points.getPointsY().get(counter);
            Double k1 = h * function.calc(previousX, previousY);
            Double k2 = h * function.calc(previousX + h / 2, previousY + k1 / 2);
            Double k3 = h * function.calc(previousX + h / 2, previousY + k2 / 2);
            Double k4 = h * function.calc(previousX + h, previousY + k3);
            Double currentY = previousY + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
            points.addPoint(currentX, currentY);
            currentX += h;
            counter++;
        }
        return points;
    }

    @Override
    public Double getOrder() {
        return 4.0;
    }

    @Override
    public String getName() {
        return "Рунге-Кутта 4";
    }
}
