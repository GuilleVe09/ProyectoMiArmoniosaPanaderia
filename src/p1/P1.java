/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author guill
 */
public class P1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String usuario = "root";
        String clave = "";
        String url = "jdbc:mysql://localhost:3306/mi_armoniosa_panaderia";
        Connection con;
        Statement stmt;
        Statement stmt2;
        ResultSet rs;
        ResultSet rs2;
        int cuentaD;
        String descripcion;
        float valor;
        int pasteleroID;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
            Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
