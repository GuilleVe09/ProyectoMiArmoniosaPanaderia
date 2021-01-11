package Inicio;

import Cliente.LoggedIn;
import Pastelero.Consulta;
import Pastelero.P_LoggedIn;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

public class Voki
{
    public Voki()
    {
    }

    public void inicio() throws SQLException
    {
        ConexionBD cdb = new ConexionBD();
        Connection con;
        Statement stmt;
        //Statement stmt2;
        ResultSet rs;
        //ResultSet rs2;
        int cuentaD;
        String descripcion;
        float valor;
        int pasteleroID;
        Scanner sn = new Scanner(System.in);
        Scanner nu = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        LoggedIn li;
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        try {
            con = DriverManager.getConnection(url, usuario, clave);
            stmt = con.createStatement();
            //stmt.executeUpdate(url);
            rs = stmt.executeQuery("SELECT * FROM cliente");
            rs.next();
            do {                
                System.out.println(rs.getString("cedula") + " Nombre: " + rs.getString("nombre"));
            } while (rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        
        do{
            System.out.println("******************************************");
            System.out.println("Bienvenido a MI Armoniosa Panaderia");
            System.out.println("1. Cliente");
            System.out.println("2. Panadero");
            System.out.println("3. Salir");
            System.out.println("******************************************");
            //try{
                
            System.out.println("Introduzca el numero de la opcion que prefiera:");
            opcion = sn.nextInt();

                switch(opcion)
                {
                    //Cliente
                    case 1:
                        int x = 0;
                        do{
                            System.out.println("Menu Cliente");
                            System.out.println("1.Registrarse");
                            System.out.println("2.Iniciar Sesion");
                            System.out.println("3.Volver \n");
                            
                            System.out.println("Escoja una opcion");
                            x = sn.nextInt();
                            
                            switch(x){
                                case 1:
                                    String cedula;
                                    String nombre;
                                    String apellido;
                                    String email;
                                    String pFav;
                                    String direc;
                                    
                                    System.out.println("******************************************");
                                    System.out.println("Ingrese sus datos \n");
                                    
                                    cedula=sn.nextLine();
                                    System.out.print("Cedula: ");
                                    cedula=sn.nextLine();
                                    //cedula = sn.next().charAt(10);
                                    System.out.println("nombre:");
                                    nombre = sn.nextLine();
                                    //nombre = sn.next().charAt(50);
                                    System.out.println("apellido:");
                                    apellido = sn.nextLine();
                                    //apellido = sn.next().charAt(50);
                                    System.out.println("email:");
                                    email = sn.nextLine();
                                    //email = sn.next().charAt(20);
                                    System.out.println("Producto Favorito:");
                                    pFav = sn.nextLine();
                                    //pFav = sn.next().charAt(20);
                                    System.out.println("Direccion:");
                                    direc = sn.nextLine();
                                    //direc = sn.next().charAt(50);
                                    
                                
                                    try {
                                        con = DriverManager.getConnection(cdb.url, cdb.usuario, cdb.clave);
                                        PreparedStatement pps = con.prepareStatement("INSERT INTO cliente(cedula,nombre,apellido,email,productoFavorito,direccion) VALUES(?,?,?,?,?,?)");
                                        pps.setString(1, cedula);
                                        pps.setString(2, nombre);
                                        pps.setString(3, apellido);
                                        pps.setString(4, email);
                                        pps.setString(5, pFav);
                                        pps.setString(6, direc);
                                        pps.executeUpdate();
                                        System.out.println("Datos guardados");
                                    } catch (SQLException ex) {
                                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                
                                    
                                    
                                    System.out.println("******************************************");
                                    
                                    break;

                                    
                                case 2:
                                    String id;
                                    String name;

                                    System.out.println("******************************************");
                                    System.out.println("Ingrese los datos de registro");

                                    
                                    id = sn.nextLine();
                                    System.out.println("Ingrese su cedula de identidad:");
                                    id = sn.nextLine();
                                    System.out.println("Ingrese su nombre:");
                                    name = sn.nextLine();
                                    

                                    LoggedIn log = new LoggedIn();

                                    if(log.validate(id, name))
                                    //if(log.validate("0908070605", "Erwin"))
                                    {
                                        log.start();
                                    }
                                    else
                                    {
                                        System.out.println("Nombre o numero de cedula incorrectos");
                                    }


                                    break;
                                    
                          
                             
                                default:
                                    
                                    System.out.println("Volver atras");
                                
                            }
                        
                        }while(x < 3);
                        break;

                    //Panadero
                    case 2:
                    do{
                        System.out.println("Menu Panadero");
                        System.out.println("1.Registrarse");
                        System.out.println("2.Iniciar Sesion");
                        System.out.println("3.Volver \n");
                        
                        System.out.println("Escoja una opcion");
                        x = sn.nextInt();
                        
                        switch(x)
                        {
                            //Registrar Panadero
                            case 1:
                                String nombreP;
                                String apellidoP;
                                String pEstP;
                                String direcP;
                                
                                System.out.println("******************************************");
                                System.out.println("Ingrese sus datos \n");
                                
                                nombreP = sn.nextLine();
                                //cedulaP = sn.next().charAt(10);
                                System.out.println("nombre:");
                                nombreP = sn.nextLine();
                                //nombreP = sn.next().charAt(50);
                                System.out.println("apellido:");
                                apellidoP = sn.nextLine();
                                //apellidoP = sn.next().charAt(50);
                                System.out.println("Producto Estrella:");
                                pEstP = sn.nextLine();
                                //pEstP = sn.next().charAt(20);
                                System.out.println("Direccion:");
                                direcP = sn.nextLine();
                                //direcP = sn.next().charAt(50);
                                
                            
                                try
                                {
                                    con = DriverManager.getConnection(cdb.url, cdb.usuario, cdb.clave);
                                    PreparedStatement pps = con.prepareStatement("INSERT INTO pastelero(id,nombre,apellido,productoEstrella,ubicacion) VALUES(?,?,?,?,?)");
                                    pps.setInt(1, 0);
                                    pps.setString(2, nombreP);
                                    pps.setString(3, apellidoP);
                                    pps.setString(4, pEstP);
                                    pps.setString(5, direcP);
                                    pps.executeUpdate();
                                    System.out.println("Datos guardados");
                                }
                                catch (SQLException ex)
                                {
                                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            
                                
                                
                                System.out.println("******************************************");
                                
                                break;

                            //Inicar sesion Panadero    
                            case 2:
                                String id;
                                String name;

                                System.out.println("******************************************");
                                System.out.println("Ingrese los datos de registro");

                                
                                id = sn.nextLine();
                                System.out.println("Ingrese su ID:");
                                id = sn.nextLine();
                                System.out.println("Ingrese su nombre:");
                                name = sn.nextLine();
                                
                                P_LoggedIn pLog = new P_LoggedIn();

                                int nid = Integer.parseInt(id);

                                if(pLog.P_validate(nid, name))
                                //if(log.validate("203", "Enzo"))
                                {
                                    pLog.start();
                                }
                                else
                                {
                                    System.out.println("Nombre o numero de cedula incorrectos");
                                }


                                break;
                                
                      
                         
                            default:
                                
                                System.out.println("Volver atras");
                            
                        }
                    
                    }while(x < 3);
                    break;


                    case 3:

                        salir = true;

                        break;
                    default:
                        System.out.println("Las opciones son entre 1 y 3");
                }
            
            /*}catch(InputMismatchException e){
                System.out.println("Debe introducir un numero");
                sn.next();
            }*/
            
            
        }while(opcion < 3);
        
        System.out.println("Fin del menu");
        

        
        
        /*
        Scanner sn = new Scanner(System.in);
        System.out.println("******************************************");
        System.out.println("Indicar sus datos para realizar el pago");
        
        System.out.println("Ingrese el numero de cuenta de destino: ");
        cuentaD = sn.nextInt();
        
        System.out.println("Ingrese el valor a pagar: ");
        valor = sn.nextFloat();
        sn.nextLine();
        
        System.out.println("Ingrese la descripcion de su transferencia: ");
        descripcion = sn.nextLine();
        
        
        System.out.println("***************************************");
        System.out.println("Datos de la transferencia: ");
        System.out.println(cuentaD);
        System.out.println(valor);
        System.out.println(descripcion);
        System.out.println("***************************************");
        
        
        
        try {
            System.out.println("***************************************");
            System.out.println("Ingrese el id del pastelero a calificar");
            pasteleroID = sn.nextInt();
            
            con = DriverManager.getConnection(url, usuario, clave);
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            stmt.executeUpdate("INSERT INTO relacion_pastelero_calificacion VALUES(105,1011,'2020-2-5')");
            stmt2.executeUpdate("INSERT INTO calificacion VALUES(1011, 2, 'Muy insatisfecho', 'Si', null, 'No estuvo bueno', '0923456921')");
            rs = stmt.executeQuery("SELECT * FROM relacion_pastelero_calificacion");
            rs2 = stmt2.executeQuery("SELECT * FROM calificacion");
            rs2.next();
            
            do {                
                System.out.println("id" + rs2.getString("id") + " Valoracion General: " + rs2.getString("valoracionGeneral"));
            } while (rs2.next());
        } catch (SQLException ex) {
            Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
        }
            do {                
                //System.out.println("id" + rs.getString("id") + " Valoracion General: " + rs.getString("valoracionGeneral"));
            } while (rs.next());
        } catch (SQLException ex2) static {
            Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
        
        
        /*
        Scanner sn = new Scanner(System.in);
        System.out.println("******************************************");
        System.out.println("Indicar sus datos para realizar el pago");
        
        System.out.println("Ingrese el numero de cuenta de destino: ");
        cuentaD = sn.nextInt();
        
        System.out.println("Ingrese el valor a pagar: ");
        valor = sn.nextFloat();
        sn.nextLine();
        
        System.out.println("Ingrese la descripcion de su transferencia: ");
        descripcion = sn.nextLine();
        
        
        System.out.println("***************************************");
        System.out.println("Datos de la transferencia: ");
        System.out.println(cuentaD);
        System.out.println(valor);
        System.out.println(descripcion);
        System.out.println("***************************************");
        
        
        
        try {
            System.out.println("***************************************");
            System.out.println("Ingrese el id del pastelero a calificar");
            pasteleroID = sn.nextInt();
            
            con = DriverManager.getConnection(url, usuario, clave);
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            stmt.executeUpdate("INSERT INTO relacion_pastelero_calificacion VALUES(105,1011,'2020-2-5')");
            stmt2.executeUpdate("INSERT INTO calificacion VALUES(1011, 2, 'Muy insatisfecho', 'Si', null, 'No estuvo bueno', '0923456921')");
            rs = stmt.executeQuery("SELECT * FROM relacion_pastelero_calificacion");
            rs2 = stmt2.executeQuery("SELECT * FROM calificacion");
            rs2.next();
            
            do {                
                System.out.println("id" + rs2.getString("id") + " Valoracion General: " + rs2.getString("valoracionGeneral"));
            } while (rs2.next());
        } catch (SQLException ex) {
            Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
        }
            do {                
                //System.out.println("id" + rs.getString("id") + " Valoracion General: " + rs.getString("valoracionGeneral"));
            } while (rs.next());
        } catch (SQLException ex2) static {
            Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
        */   
    }
}
