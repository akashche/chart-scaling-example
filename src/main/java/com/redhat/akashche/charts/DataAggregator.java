package com.redhat.akashche.charts;

/**
 * User: alexkasko
 * Date: 7/29/15
 */
public class DataAggregator {
    private static final int DOMAIN_MAX = 24;
    // mode sum
    // mode mean

    double[][] aggregate(double[][] data, float fromPercent, float toPercent) {
        // todo: real time based length
        int min = Math.round(data[0].length * fromPercent);
        int max = Math.round(data[0].length * toPercent);

        int interval = (max - min) / DOMAIN_MAX;
        if (0 == interval) interval = 1;
        int len = Math.min(max - min, DOMAIN_MAX);
        double[][] res = new double[data.length][];
        for (int i = 0; i < data.length; i++) {
            double[] arr = new double[len];
            int l = 0;
            // note: may omit some end intervals due to rounding
            for (int j = min; j + interval < max && l < len; j += interval) {
                double val = 0;
                for (int k = 0; k < interval && j + k < max; k++) {
                    val += data[i][j + k];
                }
                arr[l++] = val;
            }
            res[i] = arr;
        }
        return res;
    }
}
