package com.redhat.akashche.charts;

import org.jfree.chart.JFreeChart;

import javax.swing.*;

/**
 * User: alexkasko
 * Date: 7/29/15
 */
public class PanelBuilder {

    public JPanel createPanel() {
        double[][] data = new DataGen().createDataset();
        double[][] aggregated = new DataAggregator().aggregate(data, 0, 1);
        JFreeChart chart = new ChartBuilder().createChart(aggregated);
        return new ScalableChartPanel(chart);
    }
}
