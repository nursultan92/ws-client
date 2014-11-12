/**
 * Matrix_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nurolopher;

public interface Matrix_PortType extends java.rmi.Remote {
    public int[][] multiply(int[][] a, int[][] b, int start, int end) throws java.rmi.RemoteException;
}
