/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();



        //***************************************************************************** LAB07
        /**
        //Crear el mapper y usarlo: 
        ClienteMapper cm =sqlss.getMapper(ClienteMapper.class);
        //cm...
        System.out.println("--------------Consultando clientes -----------------------------------------------------------\n");
        System.out.println(cm.consultarClientes());
        System.out.println("-------------Insertando a -700 el ItemRentado numero 0, con fechas especificas-------------------------------------------------------------\n");
        //cm.agregarItemRentadoACliente(-700,4,Date.valueOf("2020-09-25"),Date.valueOf("2020-09-26"));//new SimpleDateFormat("yyyy/MM/dd").parse("2020/09/25")
        System.out.println("-------------------------Consultando al cliente -700 ------------------------------------------\n");
        System.out.println(cm.consultarCliente(-700));


        System.out.println("-------------------------Realizando operaciones con item-----------------------------------\n");
        System.out.println("-------------------------Consultando items-----------------------------------\n");
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        System.out.println(im.consultarItems());
        System.out.println("-------------------------Buscando el item de id 1 --------------------------------------\n");
        System.out.println(im.consultarItem(1));

        System.out.println("-------------------------Insertando un item con id 12345--------------------------------------------\n");
        /**
        Item(TipoItem tipo, int id, String nombre, String descripcion, Date fechaLanzamiento, long tarifaxDia, String formatoRenta, String genero)
        im.insertarItem( new Item( new TipoItem(2,"Accion"),12345,"funar o ser funado CR","descripcion",Date.valueOf("2020-09-25"),2313,"formato renta","accion" ));

        System.out.println("---------------------Consultando el item añadido------------------------------------------\n");
        System.out.println(im.consultarItem(12345));
        */


        //***************************************************************************** Probando MapperCLiente
        ClienteMapper cm =sqlss.getMapper(ClienteMapper.class);
        System.out.println("---------------------");
        System.out.println(cm.consultarClientes());
        System.out.println("---------------------");
        System.out.println(cm.consultarCliente(1234).getNombre());
        //cm.agregarItemRentadoACliente(-700,4,Date.valueOf("2020-09-25"),Date.valueOf("2020-09-26"));
        //cm.agregarCliente( new Cliente("Camilo Rincon",3146879,"8527415963", "direccion","email2",true, new ArrayList<ItemRentado>()  ));
        System.out.println("---------------------");
        System.out.println(cm.consultarCliente(85274153));
         System.out.println("----------------------");
         System.out.println(cm.consultarClientesVetados());
         System.out.println("----------------------");

        //***************************************************************************** Probando Item Rentado
        ItemRentadoMapper irm = sqlss.getMapper(ItemRentadoMapper.class);
        System.out.println("----------------------");
        System.out.println(irm.consultarItemsRentados(1478521)); // items rentados por el cliente 1478521
        System.out.println("----------------------");
        System.out.println(irm.consultarItemRentados());
        System.out.println("----------------------");

        //***************************************************************************** Probando ItemMapper
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        System.out.println("----------------------");
        System.out.println( im.consultarItems());
        System.out.println("----------------------");
        //im.insertarItem( new Item( new TipoItem(2,"Accion"),12345,"funar o ser funado CR","descripcion",Date.valueOf("2020-09-25"),2313,"formato renta","accion" ));
        System.out.println(im.consultarItem(12345));
         System.out.println("----------------------");

        //***************************************************************************** Probando Tipo Item
        System.out.println("----------------------");
        TipoItemMapper tim = sqlss.getMapper(TipoItemMapper.class);
        System.out.println(tim.getTiposItems());
        //tim.agregarTipoItem(new TipoItem(5,"papucho"));
        System.out.println("----------------------");
        System.out.println( tim.getTipoItem(1));

        sqlss.commit();
        sqlss.close();
    }


}
