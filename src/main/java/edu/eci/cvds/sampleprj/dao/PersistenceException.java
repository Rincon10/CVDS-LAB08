package edu.eci.cvds.sampleprj.dao;

/**
 * @author Iván Camilo Rincón Saavedra
 * @author Leonardo Galeano
 * @version 10/1/2020
 */
public class PersistenceException  extends Exception{
    public PersistenceException(String message){
        super(message);
    }

    public PersistenceException( String message,Exception exception){
        super(message,exception);
    }
}
