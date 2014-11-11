package kg;

import nurolopher.MatrixServiceLocator;
import nurolopher.Matrix_PortType;

import javax.swing.*;
import java.util.Arrays;

public class MatrixClient {
    public static void main(String[] argv) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainForm form = new MainForm();
                form.setVisible(true);
            }
        });
    }
}