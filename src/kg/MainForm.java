package kg;

import nurolopher.MatrixServiceLocator;
import nurolopher.Matrix_PortType;

import javax.swing.*;
import javax.xml.rpc.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JLabel lblTimer;
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
                        System.out.println("Random matrices has been generated and written to files!");
                    } catch (InterruptedException e1) {
                        System.err.println("Interrupted exception");
                    }
                }
            }
        });
        btnMultiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MatrixServiceLocator locator = new MatrixServiceLocator();
                int[][] a = new int[matrixSize][matrixSize];
                int[][] b = new int[matrixSize][matrixSize];
                try {
                    Matrix_PortType service = locator.getMatrix();
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
                    int[][] c = service.multiply(a, b);
                    System.out.println(Arrays.deepToString(c));
                } catch (ServiceException e1) {
                    System.err.println("Service Exception");
                } catch (IOException e1) {
                    System.err.println("Catch Exception");
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
