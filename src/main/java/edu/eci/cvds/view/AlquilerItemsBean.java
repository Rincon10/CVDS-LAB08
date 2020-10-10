package edu.eci.cvds.view;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;


/**
 * @author Iván Camilo Rincón Saavedra
 * @author Leonardo Galeano
 * @version 10/5/2020
 */
@ManagedBean(name = "alquiler")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean {
    private long id=0;
    private List<Cliente>clientes;
    private List<ItemRentado> itemsNoDevueltos;
    private Cliente selectedCliente;

    @Inject
    private ServiciosAlquiler serviciosAlquiler;



    public void consultarClientes(){
        try {
            clientes = (id == 0)?serviciosAlquiler.consultarClientes():Arrays.asList(serviciosAlquiler.consultarCliente(id));
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            reset();
        }
    }

    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email ){
        try {
            serviciosAlquiler.registrarCliente( new Cliente(nombre,documento,telefono,direccion,email) );
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            excepcionServiciosAlquiler.printStackTrace();
        }
    }

    public void consultarItemsNoRentados(){
        try {
            System.out.println("-----------------------------------");
            System.out.println(selectedCliente);
            System.out.println(serviciosAlquiler);

            itemsNoDevueltos =  serviciosAlquiler.consultarItemsCliente( selectedCliente.getDocumento());
            System.out.println(itemsNoDevueltos);
            System.out.println("devueltos");
            System.out.println("-----------------------------------");
        }
        catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            excepcionServiciosAlquiler.printStackTrace();
        }
    }
    public void resetItems(){itemsNoDevueltos=null;}
    public void reset(){
        id=0;
        clientes = null;
    }
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<ItemRentado> getItemsNoDevueltos() {
        return itemsNoDevueltos;
    }

    public void setItemsNoDevueltos(List<ItemRentado> itemsNoDevueltos) {
        this.itemsNoDevueltos = itemsNoDevueltos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
