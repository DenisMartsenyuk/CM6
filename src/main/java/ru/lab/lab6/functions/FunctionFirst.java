package ru.lab.lab6.functions;

public class FunctionFirst implements Function {
    @Override
    public String getName() {
        return "y'=4x - 2y";
    }

    @Override
    public Double calc(Double x, Double y) {
        return 4 * x - 2 * y;
    }

    @Override
    public Double calcReal(Double x) {
        return 2 * Math.exp(-2 * x) + 2 * x - 1 ;
    }
}
