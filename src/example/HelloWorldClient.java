package example;

import com.sun.jndi.rmi.registry.RemoteReference;
import nurolopher.MatrixServiceLocator;
import nurolopher.Matrix_PortType;

import java.util.Arrays;

public class HelloWorldClient {
  public static void main(String[] argv) {
      try {
          MatrixServiceLocator locator = new MatrixServiceLocator();
          Matrix_PortType service = locator.getMatrix();
          // If authorization is required
          //((MatrixSoapBindingStub)service).setUsername("user3");
          //((MatrixSoapBindingStub)service).setPassword("pass3");
          // invoke business method
          int[][] b = new int[][] {{1,2},{2,3}};
          int[][] a = new int[][] {{5,2},{2,3}};
          int[][] response = service.multiply(a,b);
          System.out.print(Arrays.deepToString(response));
      } catch (javax.xml.rpc.ServiceException ex) {
          ex.printStackTrace();
      } catch (java.rmi.RemoteException ex) {
          ex.printStackTrace();
      }
  }
}