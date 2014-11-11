package kg;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import sun.management.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MainForm extends JFrame {
    private JTextField txtMatrixSize;
    private JPanel panel1;
    private JButton okButton;
    private JTextField txtMaxValue;

    public MainForm() {
        super("Matrix Multiplication");
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Utilities.isInteger(txtMatrixSize.getText()) && Utilities.isInteger(txtMaxValue.getText())) {
                    try {
                        Thread matrixGeneratorThread = new Thread(new MatrixGeneratorRunnable(Integer.parseInt(txtMatrixSize.getText()),Integer.parseInt(txtMaxValue.getText())));
                        matrixGeneratorThread.start();
                        matrixGeneratorThread.join();
                        System.out.println("Random matrices has been generated and written to files!");
                    } catch (InterruptedException e1) {
                        System.err.println("Interrupted exception");
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
