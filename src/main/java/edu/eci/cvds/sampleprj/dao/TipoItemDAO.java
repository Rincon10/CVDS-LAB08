package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.TipoItem;

import java.util.List;

/**
 * @author Iván Camilo Rincón Saavedra
 * @author Leonardo Galeano
 * @version 10/1/2020
 */
public interface TipoItemDAO {

    public void save(TipoItem ti) throws PersistenceException;

    public TipoItem load(int id) throws PersistenceException;

    public List<TipoItem> loadTiposItems() throws PersistenceException;
}
