package kg;

import nurolopher.MatrixServiceLocator;
import nurolopher.Matrix_PortType;

import javax.swing.*;
import javax.xml.rpc.ServiceException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MainForm extends JFrame {
    private JTextField txtMatrixSize;
    private JPanel panel1;
    private JButton okButton;
    private JTextField txtMaxValue;
    private JButton btnMultiply;
    private JLabel lblNotification;
    private JTextField txtNumServers;
    private JTextArea txtArea;
    private int matrixSize;

    public MainForm() {
        super("Matrix Multiplication");
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Utilities.isInteger(txtMatrixSize.getText()) && Utilities.isInteger(txtMaxValue.getText())) {
                    matrixSize = Integer.parseInt(txtMatrixSize.getText());
                    try {
                        Thread matrixGeneratorThread = new Thread(new MatrixGeneratorRunnable(Integer.parseInt(txtMatrixSize.getText()), Integer.parseInt(txtMaxValue.getText())));
                        matrixGeneratorThread.start();
                        matrixGeneratorThread.join();
                        btnMultiply.setEnabled(true);
                        lblNotification.setText("A and B matrices has been written to files!");
                        System.out.println("Random matrices has been generated and written to files!");
                    } catch (InterruptedException e1) {
                        System.err.println("Interrupted exception");
                    }
                }
            }
        });
        btnMultiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int[][] a = new int[matrixSize][matrixSize];
                int[][] b = new int[matrixSize][matrixSize];
                try {
                    List<String> listA = Files.readAllLines(Paths.get("matrix_a.txt"), Charset.forName("UTF-8"));
                    List<String> listB = Files.readAllLines(Paths.get("matrix_b.txt"), Charset.forName("UTF-8"));
                    int index = 0;
                    for (String line : listA) {
                        a[index++] = Utilities.stringArrayToIntArray(line.split(" "));
                    }
                    index = 0;
                    for (String line : listB) {
                        b[index++] = Utilities.stringArrayToIntArray(line.split(" "));
                    }
                    long startTime = System.currentTimeMillis();
                    int[][] c = Utilities.parallelMult(a, b, Integer.parseInt(txtNumServers.getText()));
                    long endTime = System.currentTimeMillis();
                    txtArea.append(txtMatrixSize.getText() + "," + txtMaxValue.getText() + "," + txtNumServers.getText() + "," + (endTime - startTime) + "\n");
                    PrintWriter outputWriter = new PrintWriter("matrix_c.txt", "UTF-8");
                    for (index = 0; index < matrixSize; index++) {
                        outputWriter.println(Arrays.toString(c[index]));
                    }
                    outputWriter.close();
                    lblNotification.setText("Matrix multiplication completed!");
                } catch (IOException e1) {
                    System.err.println(e1.getMessage());
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
