package Pastelero;

import java.util.Scanner;

public class P_LoggedIn
{
    private String firstName;
    private String lastName;
    private String notification;

    public P_LoggedIn()
    {
    }

    //Interfaz visual del pastelero
    public void P_start()
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

    //Metodo para registrar un producto
    public void registrarProducto()
    {

    }

    //Metodo que cambia un pedido de estado
    public void terminarPendiente()
    {

    }

}
