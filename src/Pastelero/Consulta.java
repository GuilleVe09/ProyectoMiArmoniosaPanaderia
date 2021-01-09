package Pastelero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Cliente.LoggedIn;
import Inicio.ConexionBD;

/**
 *
 * @author andre
 */
public class Consulta
{
    public Consulta()
    {
    }

    public void pasteleroDisponible()
    {
        try {    
                Connection con;
                Statement stmt;
                ResultSet rs;

                ConexionBD cdb = new ConexionBD();
            
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
                con = DriverManager.getConnection(cdb.url,cdb.usuario,cdb.clave);
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
    
    //Con este metodo se muestran los productos que tenga disponible el pastelero que el usuario escoja
    public void pasteleroEscogido(int iDPastelero)
    {
        try {    
                Connection con;
                Statement stmt;
                ResultSet rs;

                ConexionBD cdb = new ConexionBD();
            
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
                con = DriverManager.getConnection(cdb.url,cdb.usuario,cdb.clave);
                stmt = con.createStatement();
                rs = stmt.executeQuery("Select nombre,precioU,precioM from relacion_pastelero_producto r join producto p on r.nombreProducto = p.nombre where idPastelero = "+iDPastelero);
                rs.next();
                String leftAlignFormat = " %-5s |%-15f | %-15f %n";
                //String leftAlignFormat1 = " %-5s |%-15s | %-15s %n";
                System.out.format("########################Informacion de productos############################%n");
                do
                {
                    System.out.format(leftAlignFormat,rs.getString("nombre"),rs.getFloat("precioU"),rs.getFloat("precioM"));
                }while(rs.next());
                System.out.format("##############################################################%n");
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }
    
}
