package ru.lab.lab6.solutions;

import ru.lab.lab6.entities.InputData;
import ru.lab.lab6.entities.Points;
import ru.lab.lab6.functions.Function;

import java.util.List;

public class SolutionAdams extends Solution {
    @Override
    public Points solveByH(Double h , int n, InputData inputData) {
        Function function = inputData.getFunction();
        Points points = getFourPoints(h, inputData);
        Double currentX = inputData.getA() + 4 * h;
        for (int i = 4; i <= n; ++i) {
            Double predictor = getPredictor(points, function, i, h);
            points.addPoint(currentX, predictor);
            Double corrector = getCorrector(points, function, i, h);
            while (Math.abs(predictor - corrector) / 29.0 > inputData.getEps()) {
                predictor = corrector;
                points.setPoint(i, currentX, predictor);
                corrector = getCorrector(points, function, i, h);
            }
            points.setPoint(i, currentX, corrector);
            currentX += h;
        }
        return points;
    }

    @Override
    public Double getOrder() {
        return 4.0;
    }

    @Override
    public String getName() {
        return "Адамса";
    }

    private Points getFourPoints(Double h, InputData inputData) {
        Double a = inputData.getA();
        Double b = inputData.getB();
        Solution solution = new SolutionRungeKutt4();
        return solution.solveByH(h, Math.min(3, (int) Math.round((b - a) / h)), inputData);
    }

    private Double getPredictor(Points points, Function function, int index, Double h) {
        List<Double> pointsY = points.getPointsY();
        List<Double> pointsX = points.getPointsX();
        Double f_4 = function.calc(pointsX.get(index - 4), pointsY.get(index - 4));
        Double f_3 = function.calc(pointsX.get(index - 3), pointsY.get(index - 3));
        Double f_2 = function.calc(pointsX.get(index - 2), pointsY.get(index - 2));
        Double f_1 = function.calc(pointsX.get(index - 1), pointsY.get(index - 1));
        return pointsY.get(index - 1) + h / 12.0 * (23 * f_1 - 16 * f_2 + 5 * f_3);
    }

    private Double getCorrector(Points points, Function function, int index, Double h) {
        List<Double> pointsY = points.getPointsY();
        List<Double> pointsX = points.getPointsX();
        Double f_3 = function.calc(pointsX.get(index - 3), pointsY.get(index - 3));
        Double f_2 = function.calc(pointsX.get(index - 2), pointsY.get(index - 2));
        Double f_1 = function.calc(pointsX.get(index - 1), pointsY.get(index - 1));
        Double f_p = function.calc(pointsX.get(index), pointsY.get(index));
        return pointsY.get(index - 1) + h / 12.0 * (5 * f_p + 8 * f_1 - f_2);
    }
}
