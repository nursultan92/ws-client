package kg;

import nurolopher.MatrixServiceLocator;
import nurolopher.Matrix_PortType;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class LineMultiplier implements Callable<int[][]> {
    int[][] A;
    int[][] B;
    int start;
    int end;
    public int[][] C;
    String endpointAddress;


    public LineMultiplier(int[][] a, int[][] b, int s, int e, String endpointAddress) {
        A = a;
        B = b;
        C = new int[a.length][b.length];
        start = s;
        end = e;
        this.endpointAddress = endpointAddress;
    }

    public int[][] call() throws RemoteException, ServiceException {

        MatrixServiceLocator locator = new MatrixServiceLocator();
        locator.setEndpointAddress("Matrix", endpointAddress);
        Matrix_PortType service = null;
        try {
            service = locator.getMatrix();
        } catch (ServiceException e) {
            System.out.println("Remote exception:" + e.getMessage());
        }

        return service.multiply(A, B, start, end);
    }
}
