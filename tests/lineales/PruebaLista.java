/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;

import lineales.dinamicas.Lista;
import lineales.dinamicas.*;

/**
 *
 * @author gonza
 */
public class PruebaLista {

    public static void main(String[] args) {

        Lista L1 = new Lista();
        Lista L2 = new Lista();
        Lista L3 = new Lista();

        L1.insertar(2, 1);
        L1.insertar(2, 2);
        L1.insertar(2, 3);
        L1.insertar(2, 4);
        L1.insertar(2, 5);
        L1.insertar(2, 6);
        L1.insertar(2, 7);

        System.out.println(L1.toString());
    }

    public static Lista concatenar(Lista L1, Lista L2) {

        Lista L3 = L1.clone();
        int i = 1;
        int ultiPos = L1.longitud() + 1;
        while (i <= L2.longitud()) {
            L3.insertar(L2.recuperar(i), ultiPos);
            ultiPos++;
            i++;
        }

        return L3;
    }

    public static boolean comprobar(Lista L1) {

        /**
         * Casos: Lista vacia , Lista [00], Lista [010], Lista
         */
        int longitud = L1.longitud();
        Lista clon = L1.clone();
        boolean aprobado = true;

        if (clon.esVacia()) {
            aprobado = false;

        } else {
            int i = 1;
            int contador = 0;
            Pila pilaAux = new Pila();
            Cola colaAux = new Cola();

            while ((int) clon.recuperar(1) != 0 && i <= longitud) {
                pilaAux.apilar(clon.recuperar(1));
                colaAux.poner(clon.recuperar(1));
                clon.eliminar(1);
                i++;
            }

            if (clon.eliminar(1)) {
                contador++;
                longitud = longitud - i;
                i = 1;

                while (aprobado && (int) clon.recuperar(1) != 0 && i <= longitud) {
                    if ((int) colaAux.obtenerFrente() != (int) clon.recuperar(1)) {
                        aprobado = false;
                    } else {
                        i++;
                        colaAux.sacar();
                        clon.eliminar(1);
                    }
                }

                if (clon.eliminar(1)) {
                    contador++;
                    longitud = longitud - i;
                    i = 1;

                    while (aprobado && i <= longitud) {
                        if ((int) pilaAux.obtenerTope() != (int) clon.recuperar(1)) {
                            aprobado = false;
                        } else {
                            i++;
                            pilaAux.desapilar();
                            clon.eliminar(1);
                        }

                    }

                } else {
                    aprobado = false;
                }

            } else {
                aprobado = false;
            }

            if (!pilaAux.esVacia() && !colaAux.esVacia()) {
                aprobado = false;
            }

        }

        return aprobado;
    }

    public static Lista invertir(Lista L1) {
        Lista clon = L1.clone();
        Lista nueva = new Lista();
        int longitud = L1.longitud();
        int i = 1;

        while (i <= longitud) {
            nueva.insertar(clon.recuperar(1), 1);
            clon.eliminar(1);
            i++;
        }

        return nueva;
    }

    public static Lista intercalar(Lista L1, Lista L2) {

        int longitud1 = L1.longitud();
        int longitud2 = L2.longitud();
        int largo = longitud1 + longitud2;
        int i = 1;

        Lista intercalada = new Lista();
        Lista clonL1 = L1.clone();
        Lista clonL2 = L2.clone();

        while (i <= largo) {

            if (clonL1.esVacia()) {
                intercalada.insertar(clonL2.recuperar(1), i);
                clonL2.eliminar(1);
                i++;
            } else if (clonL2.esVacia()) {
                intercalada.insertar(clonL1.recuperar(1), i);
                clonL1.eliminar(1);
                i++;
            } else {
                intercalada.insertar(clonL1.recuperar(1), i);
                i++;
                intercalada.insertar(clonL2.recuperar(1), i);
                i++;
                clonL1.eliminar(1);
                clonL2.eliminar(1);
            }

        }

        return intercalada;
    }

    //iterativo
    public static int contar(Lista L1, Object elem) {
        Lista clon = L1.clone();
        int longitud = L1.longitud();
        int i = 1;
        int contador = 0;

        while (i <= longitud) {
            if (clon.recuperar(1).equals(elem)) {
                contador++;
            }
            clon.eliminar(1);
            i++;
        }
        return contador;
    }

    //recursivo
    public static int contadorRecursivo(Lista L1, Object elem) {
        int contador;
        if (L1.esVacia()) {
            contador = 0;
        } else {
            if (L1.recuperar(1).equals(elem)) {
                L1.eliminar(1);
                contador = contadorRecursivo(L1, elem) + 1;
            } else {
                L1.eliminar(1);
                contador = contadorRecursivo(L1, elem);
            }
        }

        return contador;
    }

    public static boolean capicua(Lista L1) {

        boolean capicua = true;

        Lista clon = L1.clone();

        
            int ini = 1, fin = clon.longitud();
            while (ini < fin && capicua){
                if(!clon.recuperar(ini).equals(clon.recuperar(fin))){
                    capicua = false;
                }else{
                ini++;
                fin--;
                }
            }

        

        return capicua;
    }
    
    public static boolean generarLista(Lista L1){
        
        boolean aprobada = false;
        Lista clon = L1.clone();
        
        int longitud = L1.longitud();
        
        return aprobada;
    }
}
