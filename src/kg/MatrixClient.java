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
        try {
            MatrixServiceLocator locator = new MatrixServiceLocator();
            Matrix_PortType service = locator.getMatrix();

            int[][] b = new int[][]{{1, 2}, {2, 3}};
            int[][] a = new int[][]{{5, 2}, {2, 3}};
            int[][] response = service.multiply(a, b);
            System.out.print(Arrays.deepToString(response));
        } catch (javax.xml.rpc.ServiceException ex) {
            ex.printStackTrace();
        } catch (java.rmi.RemoteException ex) {
            ex.printStackTrace();
        }
    }
}