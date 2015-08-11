package com.redhat.akashche.charts;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: alexkasko
 * Date: 7/29/15
 */
public class PanelBuilder {

    public JFrame createMainFrame() {
        double[][] data = new DataGen().createDataset();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        Parent parent = new Parent(data, panel);
//        parent.zoomin(0.1f, 0.13f);
        parent.reset();
        JFrame jf = new JFrame();
        jf.setContentPane(panel);
        jf.setJMenuBar(createMenu(parent));
        jf.pack();
        jf.setSize(1024, 600);
        jf.setLocationRelativeTo(null);
        return jf;
    }

    private JMenuBar createMenu(Parent parent) {
        JMenu menu = new JMenu("Chart");
        JMenuItem reset = new JMenuItem("Reset");
        reset.addActionListener(new ResetListener(parent));
        menu.add(reset);
        JMenuBar res = new JMenuBar();
        res.add(menu);
        return res;
    }

    private static class Parent implements ScalableParent {
        private final double[][] data;
        private final JPanel panel;
        private float prevLower = 0f;
        private float prevUpper = 1f;

        private Parent(double[][] data, JPanel panel) {
            this.data = data;
            this.panel = panel;
        }

        @Override
        // todo: use SwingWorker
        public synchronized void zoomin(float lower, float upper) {
            float prevInterval = prevUpper - prevLower;
            float curLower = prevLower +  prevInterval * lower;
            float curUpper = prevUpper - prevInterval * (1 - upper);
            prevLower = curLower;
            prevUpper = curUpper;
            rescale(curLower, curUpper);
        }

        @Override
        // todo: use SwingWorker
        public synchronized void reset() {
            prevLower = 0f;
            prevUpper = 1f;
            rescale(0f, 1f);
        }

        private void rescale(float lower, float upper) {
            JPanel cp = createChartPanel(this, data, lower, upper);
            // data corner case, should be fixed in aggregator
            if (null != cp) {
                panel.removeAll();
                panel.revalidate();
                panel.add(cp);
                panel.repaint();
            }
        }

        private JPanel createChartPanel(Parent parent, double[][] data, float from, float to) {
            double[][] aggregated = new DataAggregator().aggregate(data, from, to);
            // data corner case, should be fixed in aggregator
            if (null == aggregated) return null;
            JFreeChart chart = new ChartBuilder().createChart(parent, aggregated);
            return new ScalableChartPanel(chart);
        }
    }

    private static class ResetListener implements ActionListener {
        private final Parent parent;

        private ResetListener(Parent parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.reset();
        }
    }

}
