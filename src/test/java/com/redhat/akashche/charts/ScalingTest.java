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
        showAndWait(new PanelBuilder().createPanel());
    }


    public static void showAndWait(final Container panel) {
        try {
            final Thread[] edtThreadHolder = new Thread[1];
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JFrame mf = createMainFrame(panel);
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

    private static JFrame createMainFrame(Container content) {
        JFrame jf = new JFrame();
        jf.addWindowListener(new CloseListener());
        jf.setContentPane(content);
        jf.pack();
        jf.setSize(1024, 600);
        jf.setLocationRelativeTo(null);
        return jf;
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
