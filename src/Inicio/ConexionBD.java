package Inicio;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author guill
 */
// Clase de conexion entre base de datos y netbeans
public class ConexionBD 
{
    public static String usuario = "root";
    //Cambiar clave dependiendo de quien lo este usando
    public static String clave = "emelec";
    public static String url = "jdbc:mysql://localhost:3306/mi_armoniosa_panaderia";
    Connection con;
    Statement stmt;
    
    public void conexion(){
        con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, usuario, clave);
            if (con != null){
                System.out.println("Se hizo la conexion exitosa");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Connection conector(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Se hizo la conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }return con;
    }

    //Este metodo realiza la conenxion directamente
    //Pero si necesitas otro tipo, crea un objeto tipo ConexionDB y usa las variables estaticas
    //Lo hice asi para no tener la misma variable en dos partes diferentes
    public Connection Conectar() throws SQLException
    {
        ConexionBD cbd = new ConexionBD();
        Connection conec = DriverManager.getConnection(cbd.url, cbd.usuario, cbd.clave);
        return conec;
    }
    
}
