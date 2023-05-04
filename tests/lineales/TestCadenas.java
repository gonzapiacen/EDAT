/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;
import lineales.dinamicas.*;
//import lineales.estaticas.*;

/**
 *
 * @author gonza
 */
public class TestCadenas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Cola c1 = new Cola();
        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');
        
        System.out.println(generar(c1).toString());
        
        
    }
    
    public static Cola generar(Cola c1){
        Cola clon = c1.clone();
        Cola nueva = new Cola();
        Pila aux = new Pila();
        Pila aux2 = new Pila();

        while (!clon.esVacia()) {
            while (!clon.esVacia() && (char) clon.obtenerFrente() != '#') {
                aux.apilar(clon.obtenerFrente());
                nueva.poner(clon.obtenerFrente());
                clon.sacar();
            }
            while (!aux.esVacia()) {
                nueva.poner(aux.obtenerTope());
                aux2.apilar(aux.obtenerTope());
                aux.desapilar();
            }
            while(!aux2.esVacia()){
                nueva.poner(aux2.obtenerTope());
                aux2.desapilar();
            }
            if (!clon.esVacia()) {
                nueva.poner('#');
            }

            clon.sacar();
        }

        return nueva;
    }
    
}
