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
import java.util.Calendar;


public class Consult extends LoggedIn
{
    /*
    Formatos
    d -> entero
    s -> texto
    f -> decimal
    n -> no especificado
    t -> time
    */

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
                rs = stmt.executeQuery("SELECT numero,monto,formaPago,tipo FROM pedido WHERE cedulaCliente = "+id);
                rs.next();
                String leftAlignFormat = " %-15d |%-15d |%-15s | %-15s %n";
                String leftAlignFormat1 = " %-15s |%-15s |%-15s | %-15s %n";
                System.out.format(leftAlignFormat1,"Numero","Monto","Forma de pago","Estado");
                do
                {
                    System.out.format(leftAlignFormat,rs.getInt("numero"),rs.getInt("monto"),rs.getString("formaPago"),rs.getString("tipo"));
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
    
    public void califacar(String pedido,String calificacion,String satisfaccion,String recomendacion,String recomendacionC,String comentario){
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
                String consulta="Select idPastelero,cedulaCliente from Pedido where numero="+pedido;
                try {
                con = DriverManager.getConnection(cdb.url,cdb.usuario,cdb.clave);
                stmt = con.createStatement();
            
                rs=stmt.executeQuery(consulta);
                rs.next();
                int idpastelero=rs.getInt("idPastelero");
                String clienteC=rs.getString("cedulaCliente");
                
                Calendar fecha = Calendar.getInstance();
                int año = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH);
                int dia = fecha.get(Calendar.DAY_OF_MONTH);
                String fechai= año+"-"+mes+"-"+dia;
                
                PreparedStatement pps= con.prepareStatement("Insert Into Calificacion(id,valoracionGeneral,satisfaccion,recomendacion,recomendacionCambio,comentario,cedulaCliente) values(?,?,?,?,?,?,?)");
                pps.setInt(1, 0);
                pps.setString(2, calificacion);
                pps.setString(3, satisfaccion);
                pps.setString(4, recomendacion);
                pps.setString(5, recomendacionC);
                pps.setString(6, comentario);
                pps.setString(7, clienteC);
                pps.executeUpdate();
                System.out.println("Calificado correctamente");
                
                consulta="Select id from Calificacion where valoracionGeneral=\""+calificacion+"\""+"and satisfaccion=\""+satisfaccion+"\""+"and recomendacion=\""+recomendacion+"\" and recomendacionCambio=\""+recomendacionC+"\" and comentario=\""+comentario+"\" and cedulaCliente=\""+clienteC+"\"";
                rs=stmt.executeQuery(consulta);
                rs.next();
                int id = rs.getInt("id");
                pps=con.prepareStatement("Insert Into Relacion_Pastelero_Calificacion(idPastelero,idCalificacion,fechaCalificacion) values(?,?,?)");
                pps.setInt(1,idpastelero);
                pps.setInt(2, id);
                pps.setString(3, fechai );
                pps.executeUpdate();
                } catch (SQLException ex) {
                Logger.getLogger(Consult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Este metodo permite al usuario cancelar una orden
    public void cancelarPedido(int numero)
    {
        PreparedStatement prest = null;
        ResultSet rst = null;

        try
        {
            String texto = "UPDATE pedido SET tipo = ? WHERE numero = ?";
            prest = Conectar().prepareStatement(texto);
            prest.setString(1, "Cancelado");
            prest.setInt(2, numero);

            prest.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println("Error en la comunicacion -> " + e);
        }
    }
    
}
