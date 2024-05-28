
package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    static String driver="com.mysql.jdbc.Driver";
    static String url="jdbc:mysql://localhost:3306/bd_ventas";
    static String usuario="root";
    static String password="";
    
    protected Connection conn = null;
    
    public Conexion(){
        
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,usuario,password);
            
            if(conn != null){
                System.out.println("Conexion OK "+conn);
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
    
    public Connection conectar(){
        return conn;
    }
    
    public void desconectar(){
        System.out.println("Cerrando la BD " + conn);
        try{
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
