package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {

    /**
     * Metodo encargado de consultar los items de la base de datos
     * @return  List<Item> items consultados en la base de datos
     */
    public List<Item> consultarItems();

    /**
     * Metodo encargado de consultar un item especifico
     * @param id, id el item a consultar
     * @return Item item consultado en la base de datos
     */
    public Item consultarItem(
            @Param("idItem") int id);

    /**
     * Metodo encargado de insertar items
     * @param it, tipo Item a guardar
     */
    public void insertarItem(
            @Param("item") Item it);

    public void actualizarTarifa(@Param("iditem") int id, @Param("tarifa") long tarifa);
}
