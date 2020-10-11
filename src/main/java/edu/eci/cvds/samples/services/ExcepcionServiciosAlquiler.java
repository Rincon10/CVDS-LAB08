package edu.eci.cvds.samples.services;

public class ExcepcionServiciosAlquiler extends Exception{
    public static final  String NO_ITEM = "No Existe un item con ese id en la base de datos.";
    public static final  String NO_CLIENTE = "No Existe un cliente con ese id en la base de datos.";
    public static final  String NO_TIPOITEM = "No Existe un tipo item con ese id en la base de datos.";
    public static final  String NO_ALQUILERITEM = "No esta en alquiler el item con id: ";
    public static final  String FECHA_LIMITE_INVALIDA = "La fecha de entrega es menor al la fecha en el que se rento el item.";
    public static final  String DIAS_INVALIDOS = "Los dias ingresados deben ser mayor o igual a 0";
    public static final  String TARIFA_INVALIDA = "La tarifa no puede ser negativa o igual a 0.";
    public ExcepcionServiciosAlquiler(String message){
        super(message);
    }

    public ExcepcionServiciosAlquiler(String message, Exception e){
        super(message,e);
    }
}
