package com.redhat.akashche.charts;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * User: alexkasko
 * Date: 7/29/15
 */
public class ScalingTest {

    @Test
    public void test() {
        showAndWait(new PanelBuilder().createMainFrame());
    }


    public static void showAndWait(final JFrame mf) {
        try {
            final Thread[] edtThreadHolder = new Thread[1];
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    mf.addWindowListener(new CloseListener());
                    mf.setVisible(true);
                    edtThreadHolder[0] = Thread.currentThread();
                }
            });
            // join on EDT thread here for test-only purposes
            edtThreadHolder[0].join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class CloseListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            for (Frame fr : Frame.getFrames()) {
                fr.dispose();
            }
        }
    }
}
