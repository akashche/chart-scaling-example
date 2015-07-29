package com.redhat.akashche.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * User: alexkasko
 * Date: 7/29/15
 */
public class ChartBuilder {

    public JFreeChart createChart(double[][] data) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        double min = 0;
        double max = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                double val = data[i][j];
                if (val > max) max = val;
                if (val < min) min = val;
                ds.addValue(val, Long.toString(i), Long.toString(j));
            }
        }

        ScalablePlot plot = new ScalablePlot(ds, new CategoryAxis(), new NumberAxis(), new StackedBarRenderer());
        plot.setOrientation(PlotOrientation.VERTICAL);
        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        ChartFactory.getChartTheme().apply(chart);

        plot.setBackgroundPaint(toColor("#FFFFFFFF"));
        plot.setBackgroundImageAlpha(0.0f);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(toColor("#FFAAAAAA"));
        plot.getRangeAxis().setRange(new Range(min, max * data.length));
        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getRangeAxis().setLabel("");
        colorAxis(plot.getRangeAxis());
        colorAxis(plot.getDomainAxis());
//                plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI * 0.12d));
        plot.getDomainAxis().setLowerMargin(0.01d);
        plot.getDomainAxis().setUpperMargin(0.01d);
        plot.getDomainAxis().setLabel("");
        BarRenderer3D barrenderer = new StackedBarRenderer3D(16.0d, 12.0d);
        barrenderer.setSeriesPaint(0, toColor("#BB669900"));
        barrenderer.setSeriesPaint(1, toColor("#BBFF8800"));
        barrenderer.setWallPaint(toColor("#FFEEEEEE"));
        barrenderer.setBaseItemLabelsVisible(false);
        barrenderer.setShadowVisible(false);
        barrenderer.setItemMargin(0.0d);
        plot.setRenderer(barrenderer);
        plot.setOutlineVisible(false);
        return chart;
    }

    private static void colorAxis(Axis ax) {
        ax.setAxisLinePaint(toColor("#FFAAAAAA"));
        ax.setTickMarkPaint(toColor("#FFAAAAAA"));
        ax.setTickLabelPaint(toColor("#FF222222"));
    }

    static Color toColor(String rgba) {
        String unprefixed = rgba.startsWith("#") ? rgba.substring(1) : rgba;
        if (!(6 == unprefixed.length() || 8 == unprefixed.length()))
            throw new RuntimeException("Invalid color: [" + rgba + "]");
        int hex = (int) Long.parseLong(unprefixed, 16);
        if (6 == unprefixed.length()) {
            return new Color(hex);
        }
        return new Color(hex, true);
    }
}
