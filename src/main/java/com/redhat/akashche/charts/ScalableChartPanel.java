package com.redhat.akashche.charts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.*;

import static com.redhat.akashche.charts.ChartBuilder.toColor;

/**
 * User: alexkasko
 * Date: 7/29/15
 */
public class ScalableChartPanel extends ChartPanel {
    public ScalableChartPanel(JFreeChart chart) {
        super(chart, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MINIMUM_DRAW_WIDTH, DEFAULT_MINIMUM_DRAW_HEIGHT,
                DEFAULT_MAXIMUM_DRAW_WIDTH, DEFAULT_MAXIMUM_DRAW_HEIGHT, DEFAULT_BUFFER_USED,
                false, false, false, false, false, false);
        this.setDomainZoomable(true);
        this.setRangeZoomable(false);
//        this.setZoomOutlinePaint(Color.blue);
        this.setZoomFillPaint(toColor("#55ff9800"));
    }
}
