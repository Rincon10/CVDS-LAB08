package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.List;

import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemMapper {
    
    
    public List<TipoItem> getTiposItems();
    
    public TipoItem getTipoItem(int id);
    
    public void agregarTipoItem(@Param("ti") TipoItem ti);

}
