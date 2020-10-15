package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import edu.eci.cvds.samples.entities.Cliente;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente( @Param("idcli") long id);
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(
            @Param("idcli") long id,
            @Param("idit") int idit,
            @Param("fechaini") Date fechainicio,
            @Param("fechafin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return List<Cliente>, lista de todos los clientes en la base de datos
     */
    public List<Cliente> consultarClientes();

    /**
     * Metodo que se encarga de agregar un cliente
     * @param cli
     */
    public void agregarCliente(@Param("cli") Cliente cli);

    /**
     * Metodo que dice que clientes fueron vetados
     * @return List<Cliente>
     */
    public List<Cliente> consultarClientesVetados();

    public void vetar(long docu, boolean estado);
    
}
