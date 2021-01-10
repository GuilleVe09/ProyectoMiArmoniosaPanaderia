package Cliente;

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
import Pastelero.Consulta;


public class Consult extends LoggedIn
{
    public Consult()
    {
    }

    //Este metodo muestra los pedidos del cliente
    public void realizarConsulta(String id)
    {
        try
        {    
            Connection con;
            Statement stmt;
            ResultSet rs;

            ConexionBD cdb = new ConexionBD();
        
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
                con = DriverManager.getConnection(cdb.url,cdb.usuario,cdb.clave);
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT monto,formaPago,tipo FROM pedido WHERE cedulaCliente = "+id);
                rs.next();
                String leftAlignFormat = " %-5d |%-15s | %-15s %n";
                String leftAlignFormat1 = " %-5s |%-15s | %-15s %n";
                System.out.format(leftAlignFormat1,"Monto","Forma de pago","Estado");
                do
                {
                    System.out.format(leftAlignFormat,rs.getInt("monto"),rs.getString("formaPago"),rs.getString("tipo"));
                }while(rs.next());
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void consultarCalificacion(String id)
    {
        try
        {    
            Connection con;
            Statement stmt;
            ResultSet rs;

            ConexionBD cdb = new ConexionBD();
        
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
                con = DriverManager.getConnection(cdb.url,cdb.usuario,cdb.clave);
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM calificacion WHERE cedulaCliente = "+id);
                rs.next();
                String leftAlignFormat = " %-5d |%-15s | %-15s | %-15s | %-15s | %-15s | %-15s %n";
                System.out.format("########################Informacion de calificacion############################%n");
                //String leftAlignFormat1 = " %-5d |%-15s | %-15s | %-15s | %-15s | %-15s | %-15s %n";
                //System.out.format(leftAlignFormat1,"id","valoracion General","Satisfaccion","Recomendacion","Recomendacion de cambio","Comentario","Cedula de cliente");
                do
                {
                    System.out.format(leftAlignFormat,rs.getInt("id"),rs.getString("valoracionGeneral"),rs.getString("satisfaccion"),rs.getString("recomendacion"), rs.getString("recomendacionCambio"),rs.getString("comentario"),rs.getString("cedulaCliente"));
                }while(rs.next());
                System.out.format("##############################################################%n");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
