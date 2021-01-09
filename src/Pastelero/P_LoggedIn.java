package Pastelero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.xml.transform.Result;

import Inicio.ConexionBD;

public class P_LoggedIn extends ConexionBD
{
    private int ID;
    private String firstName;
    private String lastName;
    private String notification;

    public P_LoggedIn()
    {
    }

    //Interfaz visual del pastelero
    public void start()
    {
        int opcion;
        Scanner op = new Scanner(System.in);

        do
        {
            System.out.println("Inicio de sesion exitoso!");
            System.out.println("******************************************");
            System.out.println("Bienvenido de vuelta "+this.firstName+" "+this.lastName);
            System.out.println("Tiene "+this.notification+" pedidos pendientes");
            System.out.println("1. Registrar producto");
            System.out.println("2. Ver pedidos pendientes");
            System.out.println("Para cerrar sesion presione cualquier otra tecla");
            System.out.println("******************************************");

            System.out.println("Introduzca el numero de la opcion que prefiera:");
            opcion = op.nextInt();

            Consulta obj = new Consulta();

            switch(opcion)
            {
                case 1:                    
                    
                    break;    
                case 2:

                    break;
            }
        }while(opcion < 3 & opcion > 0);
    }

    //Validar pastelero
    public boolean P_validate(int id, String name)
    {
        PreparedStatement pst = null;
        ResultSet rst = null;

        try
        {
            String ask = "SELECT * FROM pastelero";
            pst = Conectar().prepareStatement(ask);
            rst = pst.executeQuery();

            while(rst.next())
            {
                if(rst.getInt(1) == id & rst.getString(2).equals(name))
                {
                    this.ID = rst.getInt(1);
                    this.firstName = rst.getString(2);
                    this.lastName = rst.getString(3);
                    
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error en la comunicacion -> " + e);
        }

        return false;
    }

    //Metodo para registrar un producto
    public void registrarProducto()
    {

    }

    //Metodo que cambia un pedido de estado
    public void terminarPendiente()
    {

    }

}
