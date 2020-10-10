package edu.eci.cvds.view;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Iván Camilo Rincón Saavedra
 * @author Leonardo Galeano
 * @version 10/5/2020
 */
@ManagedBean(name = "alquiler")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean {
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private List<Cliente>clientes;
    private List<ItemRentado> itemsNoDevueltos;
    private Cliente selectedCliente;

    public void consultarClientes(){
        try {
            clientes = serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            excepcionServiciosAlquiler.printStackTrace();
        }
    }

    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email ){
        try {
            serviciosAlquiler.registrarCliente( new Cliente(nombre,documento,telefono,direccion,email) );
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            excepcionServiciosAlquiler.printStackTrace();
        }
    }

    public void cosultarItemsNoRentados(){
        try {
            itemsNoDevueltos =  serviciosAlquiler.consultarCliente( selectedCliente.getDocumento() ).getRentados();
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            excepcionServiciosAlquiler.printStackTrace();
        }

    }

    public void reset(){
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
}
