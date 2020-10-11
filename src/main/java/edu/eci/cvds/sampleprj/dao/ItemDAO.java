package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;

import java.util.List;

/**
 * @author Iván Camilo Rincón Saavedra
 * @author Leonardo Galeano
 * @version 10/1/2020
 */
public interface ItemDAO {

    /**
     * Metodo encargado de guardar items
     * @param it, tipo Item a guardar
     * @throws PersistenceException
     */
    public void save(Item it) throws PersistenceException;

    /**
     * Metodo encargado de consultar un item especifico
     * @param id, id el item a consultar
     * @return Item item consultado en la base de datos
     * @throws PersistenceException
     */
    public Item load(int id) throws PersistenceException;

    /**
     * Metodo encargado de mostrar todos los items de la base de datos
     * @return List<Item>, retorna todos los items de la base de datos
     * @throws PersistenceException
     */
    public List<Item> load()  throws PersistenceException;

    public void actualizarTarifa(int id, long tarifa) throws PersistenceException;
}
