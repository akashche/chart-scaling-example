package com.redhat.akashche.charts;

import java.util.Random;

/**
 * User: alexkasko
 * Date: 7/29/15
 */
public class DataGen {
    private static final int SIZE = 10000;

    double[][] createDataset() {
        double[] green = new double[SIZE];
        double[] orange = new double[SIZE];
        Random random = new Random(42);
        for (int i = 0; i < SIZE; i++) {
            double add = ((double) i) / SIZE;
            green[i] = random.nextDouble() + add;
            orange[i] = random.nextDouble() + add;
        }
        return new double[][]{green, orange};
    }
}
