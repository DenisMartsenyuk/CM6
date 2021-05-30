package ru.lab.lab6.utils;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import ru.lab.lab6.entities.Points;

public class Painter {
    private final XYChart chart;

    public Painter() {
        chart = new XYChartBuilder().width(600).height(400).title("Area Chart").xAxisTitle("X").yAxisTitle("Y").build();
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setMarkerSize(5);
    }

    public void addPrimitive(Points points, String name) {
        if (points.getSize() > 0) {
            chart.addSeries(name, points.getPointsX(), points.getPointsY());
        }
    }

    public void openGraph() {
        new SwingWrapper(chart).displayChart();
    }
}
