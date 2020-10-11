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
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url= "jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba"; //"jdbc:mysql://HOST:3306/BD"
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            /*
            int suCodigoECI = 2160176;
            registrarNuevoProducto(con, suCodigoECI, "Leonardo.", 99999999);
            suCodigoECI = 2159820;
            registrarNuevoProducto(con, suCodigoECI, "Ivan Camilo Rincon", 99999999);
            con.commit();
            */
            evidencia(con,2159820,2160176);
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear preparedStatement
        //Asignar parámetros
        //usar 'execute'
        PreparedStatement nuevoProducto;

        String insert = "INSERT INTO ORD_PRODUCTOS " +
                "VALUES (?,?,?);";
        nuevoProducto = con.prepareStatement(insert);
        nuevoProducto.setInt(1,codigo);
        nuevoProducto.setString(2,nombre);
        nuevoProducto.setInt(3,precio);
        nuevoProducto.executeUpdate();
        con.commit();
        
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return List<String>, lista con el nombre de todos los productos del pedido codigoPedido
     * @throws SQLException, si ocurre algun problema de SQL
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido) throws SQLException {
        List<String> np=new LinkedList<>();
        
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultados del ResultSet
        //Llenar la lista y retornarla
        PreparedStatement productosPedido;
        String select = "SELECT Pr.nombre " +
                        "FROM ORD_PRODUCTOS AS Pr " +
                        "INNER JOIN ORD_DETALLE_PEDIDO AS Dp " +
                        "ON Dp.producto_fk = Pr.codigo " +
                        "WHERE Dp.pedido_fk = ? ;";


        productosPedido = con.prepareStatement( select );
        productosPedido.setInt( 1, codigoPedido);
        ResultSet resultSet = productosPedido.executeQuery();
        while(resultSet.next()){
            np.add(resultSet.getString(1));
        }
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con la conexión JDBC
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     * @throws SQLException, si ocurre algun problema de SQL
     */
    public static int valorTotalPedido(Connection con, int codigoPedido) throws SQLException {
        
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultado del ResultSet
        int  total =0;
        PreparedStatement cost = null;

        String select = "SELECT SUM(Po.precio*Op.cantidad) " +
                "FROM ORD_PRODUCTOS Po " +
                "INNER JOIN  ORD_DETALLE_PEDIDO Op " +
                " ON Po.codigo = Op.producto_fk " +
                "WHERE Op.pedido_fk = ? ;";
        cost = con.prepareStatement( select );
        cost.setInt(1, codigoPedido);
        ResultSet resultSet = cost.executeQuery();
        while ( resultSet.next() ){
            total+= resultSet.getInt(1);
        }
        return total;
    }

    public static void evidencia(Connection con, int codigo1, int codigo2) throws SQLException {

        PreparedStatement productosPedido;
        String select = "SELECT Pr.nombre " +
                "FROM ORD_PRODUCTOS AS Pr " +
                "WHERE Pr.codigo IN (?,?);";

        productosPedido = con.prepareStatement(select);
        productosPedido.setInt(1, codigo1);
        productosPedido.setInt(2, codigo2);
        ResultSet resultSet = productosPedido.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }
    
    
}