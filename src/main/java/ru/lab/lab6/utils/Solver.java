package ru.lab.lab6.utils;

import ru.lab.lab6.entities.InputData;
import ru.lab.lab6.entities.Points;
import ru.lab.lab6.functions.Function;
import ru.lab.lab6.solutions.Solution;
import ru.lab.lab6.solutions.SolutionAdams;
import ru.lab.lab6.solutions.SolutionMiln;
import ru.lab.lab6.solutions.SolutionRungeKutt4;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private final Painter painter;
    private List<Solution> solutions;

    private final double PAINTER_STEP = 0.1;

    public Solver() {
        painter = new Painter();
        solutions = new ArrayList<>();
        solutions.add(new SolutionRungeKutt4());
        solutions.add(new SolutionMiln());
        solutions.add(new SolutionAdams());
    }

    public void solve(InputData inputData) {
        painter.addPrimitive(getRealCurve(inputData), "Реальные значения");
        for (Solution solution : solutions) {
            Points points = solution.solve(inputData);
            printTable(points, solution.getName());
            painter.addPrimitive(points, solution.getName());
        }

        painter.openGraph();
    }

    public void printTable(Points points, String name) {
        System.out.println("Таблица метода " + name + ":");
        List<Double> pointsX = points.getPointsX();
        for (Double x : pointsX) {
            System.out.printf("%9.5f", x);
        }
        System.out.println();
        List<Double> pointsY = points.getPointsY();
        for (Double y : pointsY) {
            System.out.printf("%9.5f", y);
        }
        System.out.println();
    }

    public Points getRealCurve(InputData inputData) {
        Function function = inputData.getFunction();
        Points points = new Points();
        int n = (int) Math.round((inputData.getB() - inputData.getA()) / PAINTER_STEP);
        Double currentX = inputData.getA();
        for (int i = 0; i <= n; ++i) {
            points.addPoint(currentX, function.calcReal(currentX));
            currentX += PAINTER_STEP;
        }
        return points;
    }

}
