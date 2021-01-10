package Pastelero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.xml.transform.Result;

import Inicio.ConexionBD;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Statement;
import static java.time.LocalDate.now;
import java.util.Calendar;
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
            System.out.println("3. Consultar productos propios");
            System.out.println("Para cerrar sesion presione cualquier otra tecla");
            System.out.println("******************************************");

            System.out.println("Introduzca el numero de la opcion que prefiera:");
            opcion = op.nextInt();

            Consulta obj = new Consulta();

            switch(opcion)
            {
                case 1:                    
                    registrarProducto();
                    
                    break;    
                case 2:
                    

                    obj.consultarPendientes(this.ID);

                    break;
                case 3:
                    consultarProductosPropios(this.ID);
                    
                    break;
            }
        }while(opcion < 4 & opcion > 0);
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
        String usuario = "root";
        //Cambiar clave dependiendo de quien lo este usando
        String clave = "emelec";
        String url = "jdbc:mysql://localhost:3306/mi_armoniosa_panaderia";
        ConexionBD cdb = new ConexionBD();
        String nombre;
        int cantidad;
        String bosquejo;
        String tipo;
        String saborMasa;
        String descripcion;
        float precioUni;
        float precioMa;
        String tipoEmp;
        String tipoCon;
        String alergenos;
        int categ;
        int carac;
        Connection con;
        String fecha;
        String nombreP;
        Scanner sn = new Scanner(System.in);
        
        System.out.println("******************************************");
        System.out.println("Ingrese los datos de su producto");
                                    
        try{
            nombre =sn.nextLine();
            System.out.print("Nombre: ");
            nombre =sn.nextLine();

            System.out.println("Cantidad:");
            cantidad = sn.nextInt();
            sn.nextLine();

            System.out.println("Bosquejo:");
            bosquejo = sn.nextLine();

            System.out.println("Tipo:");
            tipo = sn.nextLine();

            System.out.println("Sabor de masa:");
            saborMasa = sn.nextLine();

            System.out.println("Descripcion:");
            descripcion = sn.nextLine();

            System.out.println("Precio Unitario:");
            precioUni = sn.nextFloat();

            System.out.println("Precio al por mayor:");
            precioMa = sn.nextFloat();
            sn.nextLine();
            System.out.println("Tipo de empaquetado:");
            tipoEmp = sn.nextLine();

            System.out.println("Tipo de conservado:");
            tipoCon = sn.nextLine();

            System.out.println("Alergenos:");
            alergenos = sn.nextLine();

            System.out.println("Categoria:");
            categ = sn.nextInt();

            System.out.println("Caracteristica:");
            carac = sn.nextInt();
            
        
                                    
                                
        //try {
            con = DriverManager.getConnection(cdb.url, cdb.usuario, cdb.clave);
            PreparedStatement pps = con.prepareStatement("INSERT INTO producto(nombre,cantidad,bosquejo,tipo,saborMasa,descripcion,precioU,precioM,tipoEmpaquetado,tipoConservado,alergenos,categoria,caracteristica) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, nombre);
            pps.setInt(2, cantidad);
            pps.setString(3, bosquejo);
            pps.setString(4, tipo);
            pps.setString(5, saborMasa);
            pps.setString(6, descripcion);
            pps.setFloat(7, precioUni);
            pps.setFloat(8, precioMa);
            pps.setString(9, tipoEmp);
            pps.setString(10, tipoCon);
            pps.setString(11, alergenos);
            pps.setInt(12, categ);
            pps.setInt(13, carac);
            pps.executeUpdate();
            System.out.println("Datos guardados");
        } catch (SQLException ex) {
            Logger.getLogger(P_LoggedIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Calendar fecha1 = Calendar.getInstance();
            int año = fecha1.get(Calendar.YEAR);
            int mes = fecha1.get(Calendar.MONTH);
            int dia = fecha1.get(Calendar.DAY_OF_MONTH);
            String fechaHoy = año + "-" + mes + "-" + dia;
            sn.nextLine();
            System.out.println("Inserte nombre de Producto escogido:");
            nombreP = sn.nextLine();
            con = DriverManager.getConnection(cdb.url, cdb.usuario, cdb.clave);
            PreparedStatement pps = con.prepareStatement("INSERT INTO relacion_pastelero_producto(idPastelero,nombreProducto,fechaPublicado) VALUES(?,?,?)");
            pps.setInt(1, this.ID);
            pps.setString(2, nombreP);
            pps.setString(3, fechaHoy);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(P_LoggedIn.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    
    public void consultarProductosPropios(int ID)
    {
        try {
            Connection con;
            Statement stmt;
            ResultSet rs;

            ConexionBD cdb = new ConexionBD();
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection(cdb.url,cdb.usuario,cdb.clave);
            stmt= con.createStatement();
            rs=stmt.executeQuery("Select nombre,cantidad,bosquejo,tipo,saborMasa,descripcion,precioU,precioM,tipoEmpaquetado,tipoConservado,alergenos,categoria,caracteristica from relacion_pastelero_producto r join producto p on r.nombreProducto = p.nombre where idPastelero = "+ID);
            rs.next();
            String leftAlignFormat = "# %-5s |%-15d | %-15s | %-15s | %-15s | %-15s | %-15f | %-15f | %-15s | %-15s | %-15s | %-15d | %-15d #%n";
            System.out.format("########################Productos############################%n");
            do{
                System.out.format(leftAlignFormat,rs.getString("nombre"),rs.getInt("cantidad"),rs.getString("bosquejo"),rs.getString("tipo"),rs.getString("saborMasa"),rs.getString("descripcion"),rs.getFloat("precioU"),rs.getFloat("precioM"),rs.getString("tipoEmpaquetado"),rs.getString("tipoConservado"),rs.getString("alergenos"),rs.getInt("categoria"),rs.getInt("caracteristica"));
            }while(rs.next());
            System.out.format("##############################################################%n");
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
