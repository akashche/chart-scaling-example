package com.redhat.akashche.charts;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;

import java.awt.geom.Point2D;

/**
 * User: alexkasko
 * Date: 7/29/15
 */
public class ScalablePlot extends CategoryPlot {

    public ScalablePlot(CategoryDataset dataset, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryItemRenderer renderer) {
        super(dataset, domainAxis, rangeAxis, renderer);
    }

    @Override
    public boolean isDomainZoomable() {
        return true;
    }

    @Override
    public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {
        System.out.println(lowerPercent);
        System.out.println(upperPercent);
    }

    @Override
    public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source) {
        // noop
    }
}
