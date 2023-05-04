/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;
import jerarquicas.dinamicas.ArbolBin;
import lineales.dinamicas.Lista;

/**
 *
 * @author gonza
 */
public class PruebaBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArbolBin A1 = new ArbolBin();
        ArbolBin A2;
        A1.insertar(10, 1, 'I');
        A1.insertar(9, 10, 'I');
        A1.insertar(15, 10, 'D');
        A1.insertar(7, 9, 'I');
        A1.insertar(3, 9, 'D');
        A1.insertar(2, 3, 'I');
        A1.insertar(12, 15, 'I');
        A1.insertar(20, 15, 'D');
        
        System.out.println(A1.obtenerAncestros(20).toString());
    }
    
}
