package com.redhat.akashche.charts;

/**
 * User: alexkasko
 * Date: 8/11/15
 */
public interface ScalableParent {

    void zoomin(float lower, float upper);

    void reset();
}
