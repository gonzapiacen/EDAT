/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

/**
 *
 * @author gonza
 */
public class MixLineales {

    public static void main(String[] args) {

        Cola c1 = new Cola();
        c1.poner('A');
        c1.poner('B');
        c1.poner('$');
        c1.poner('C');
        c1.poner('$');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');

        Cola nueva = generarOtraCola(c1);
        System.out.println(nueva.toString());
    }

    public static Cola generarOtraCola(Cola c1) {
        Cola clon = c1.clone();
        Cola nueva = new Cola();
        Pila aux = new Pila();

        while (!clon.esVacia()) {
            while (!clon.esVacia() && (char) clon.obtenerFrente() != '$') {
                aux.apilar(clon.obtenerFrente());
                nueva.poner(clon.obtenerFrente());
                clon.sacar();
            }
            while (!aux.esVacia()) {
                nueva.poner(aux.obtenerTope());
                aux.desapilar();
            }
            if (!clon.esVacia()) {
                nueva.poner('$');
            }

            clon.sacar();
        }

        return nueva;
    }

}
