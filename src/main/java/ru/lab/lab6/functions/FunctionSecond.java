package ru.lab.lab6.functions;

public class FunctionSecond implements Function {
    @Override
    public String getName() {
        return "y' = x^2 - 2y";
    }

    @Override
    public Double calc(Double x, Double y) {
        return x * x - 2 * y;
    }

    @Override
    public Double calcReal(Double x) {
        return 3.0 / 4 * Math.exp(-2 * x) + x * x / 2 - x / 2 + 1.0 / 4;
    }
}
