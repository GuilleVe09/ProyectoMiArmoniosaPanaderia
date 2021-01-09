package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoggedIn extends ConexionBD
{ 
    //Constructor
    public LoggedIn()
    {
    }

    //Interfaz de inicio de sesion
    public void start()
    {
        System.out.println("Inicio de sesion exitoso");
    }

    //Metodo para validar si los datos coinciden
    public boolean validate(String id, String name, String url, String user, String password)
    {
        PreparedStatement pst = null;
        ResultSet rst = null;

        try
        {
            Connection con = DriverManager.getConnection(url, user, password);
            String ask = "SELECT * FROM cliente";
            pst = con.prepareStatement(ask);
            rst = pst.executeQuery();
            
            while(rst.next())
            {
                if(rst.getString(1).equals(id) & rst.getString(2).equals(name))
                {
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("No hay coincidencias -> " + e);
        }

        return false;
    }

}
