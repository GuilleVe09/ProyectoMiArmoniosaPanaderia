package Pastelero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.xml.transform.Result;

import Inicio.ConexionBD;
import java.sql.SQLException;

public class P_LoggedIn extends ConexionBD
{
    private int ID;
    private String firstName;
    private String lastName;
    private int notification;

    public P_LoggedIn()
    {
    }

    //Interfaz visual del pastelero
    public void start()
    {
        Consulta cns = new Consulta();
        int opcion;
        Scanner op = new Scanner(System.in);

        do
        {
            Consulta consul = new Consulta();
            this.notification = consul.getNumber(this.ID).size();

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
                    obj.consultarPendientes(this.ID);
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
    public void terminarPendiente(int numero) throws SQLException
    {
        PreparedStatement prest = null;

        String texto = "UPDATE pedido SET tipo = ? WHERE idPastelero = ? AND numero = ?";
        prest = Conectar().prepareStatement(texto);
        prest.setString(1, "Realizado");
        prest.setInt(2, this.ID);
        prest.setInt(3, numero);
    }

    //Metodo que cuenta los pedidos pendientes
    public void setNotification()
    {

    }

}
