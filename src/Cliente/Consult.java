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
}
