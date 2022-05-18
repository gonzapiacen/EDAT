/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;
/**
 *
 * @author gonza
 */
public class TestGenericoFrontera {
static String sOk = "OK", sError = "ERROR";
    
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        Lista lista = new Lista();
        System.out.println("Ambos vacíos espera OK: " + ((arbol.sonFrontera(lista) ? sOk : sError)));
        lista.insertar(1, 1);
        System.out.println("Árbol vacío espera OK: " + ((arbol.sonFrontera(lista) ? sError : sOk)));
        lista.eliminar(1);
        arbol.insertar(1, 1);
        arbol.insertar(2, 1);
        arbol.insertar(3, 1);
        arbol.insertar(7, 3);
        arbol.insertar(8, 3);
        System.out.println("Lista vacía espera OK: " + ((arbol.sonFrontera(lista) ? sOk : sError)));
        lista.insertar(2, 1);
        lista.insertar(7, 2);
        lista.insertar(8, 3);
        System.out.println("Coinciden todos los elementos espera OK: " + ((arbol.sonFrontera(lista) ? sOk : sError)));
        lista.eliminar(3);
        System.out.println("Menos un elemento en lista espera OK: " + ((arbol.sonFrontera(lista) ? sOk : sError)));
        lista.insertar(9, 3);
        System.out.println("Elemento no perteneciente en lista espera OK: " + ((arbol.sonFrontera(lista) ? sError : sOk)));
    }
}
