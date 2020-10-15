package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import org.mybatis.guice.transactional.Transactional;
import java.util.List;

/**
 * @author Iván Camilo Rincón Saavedra
 * @author Leonardo Galeano
 * @version 10/1/2020
 */
public class MyBATISItemDAO  implements ItemDAO{

    @Inject
    private ItemMapper itemMapper;

    @Transactional
    @Override
    public void save(Item it) throws PersistenceException{
        try{
            itemMapper.insertarItem(it);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+it.toString(),e);
        }

    }

    @Override
    public Item load(int id) throws PersistenceException {
        try{
            return itemMapper.consultarItem(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }
    }

    @Override
    public List<Item> load() throws PersistenceException {
        try{
            return itemMapper.consultarItems();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los items de la base de datos",e);
        }
    }

    @Transactional
    @Override
    public void actualizarTarifa(int id, long tarifa) throws PersistenceException {
        try {
            itemMapper.actualizarTarifa(id,tarifa);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al cambiar tarifa del item con id" + id,e);
        }
    }

}