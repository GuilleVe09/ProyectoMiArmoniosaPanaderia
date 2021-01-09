/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.util.Scanner;

/**
 *
 * @author guill
 */
public class Transferencia {

    public Transferencia() {
    }
    
    public void realizarTransferencia()
    {
        int cuentaD;
        String descripcion;
        float valor;
        Scanner sn;
        
        sn = new Scanner(System.in);
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
        
        
    }
    
}
