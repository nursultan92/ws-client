/**
 * MatrixService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nurolopher;

public interface MatrixService extends javax.xml.rpc.Service {
    public java.lang.String getMatrixAddress();

    public nurolopher.Matrix_PortType getMatrix() throws javax.xml.rpc.ServiceException;

    public nurolopher.Matrix_PortType getMatrix(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
