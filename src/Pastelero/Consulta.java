/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pastelero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class Consulta {
    public void pasteleroDisponible(){
        try {    
                String usuario = "root";
                String clave = "";
                String url = "jdbc:mysql://localhost:3306/Mi_armoniosa_Panaderia";
                Connection con;
                Statement stmt;
                ResultSet rs;
            
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
                con = DriverManager.getConnection(url,usuario,clave);
                stmt= con.createStatement();
                    rs=stmt.executeQuery("Select id,nombre,apellido,ubicacion from Pastelero where exists( select * from Producto)");
                    rs.next();
                    String leftAlignFormat = "# %-5d |%-15s | %-15s | %-15s #%n";
                    System.out.format("########################PASTELEROS############################%n");
                    do{
                        System.out.format(leftAlignFormat,rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("ubicacion"));
                    }while(rs.next());
                    System.out.format("##############################################################%n");
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    
}
