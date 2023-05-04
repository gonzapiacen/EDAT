/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author gonza
 */
public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola() {
        this.arreglo = new Object[Cola.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    //metodo poner
    public boolean poner(Object nuevoElem) {
        boolean exito = false;
        int nuevoFin = (this.fin + 1) % Cola.TAMANIO;

        if (this.arreglo[nuevoFin] == null) {
            this.arreglo[this.fin] = nuevoElem;
            this.fin = nuevoFin;
            exito = true;
        }

        return exito;
    }

    //metodo sacar
    public boolean sacar() {

        boolean exito = false;

        if (!this.esVacia()) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % Cola.TAMANIO;
            exito = true;
        }

        return exito;
    }

    //metodo obtenerFrente
    public Object obtenerFrente() {
        Object frenteActual = null;

        if (!this.esVacia()) {
            frenteActual = this.arreglo[this.frente];
        }

        return frenteActual;
    }

    //metodo esVacia
    public boolean esVacia() {

        boolean vacia = false;

        if (this.frente == this.fin) {
            vacia = true;
        }

        return vacia;
    }

    //metodo vaciar
    public void vaciar() {

        this.arreglo = new Object[Cola.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    //metodo clon
    @Override
    public Cola clone() {

        Cola arregloClon = new Cola();
        arregloClon.frente = this.frente;
        arregloClon.fin = this.fin;
        arregloClon.arreglo = this.arreglo.clone();

        return arregloClon;

    }

    //metodo toString(testing)
    @Override
    public String toString() {
        String texto;

        if (this.esVacia()) {
            texto = "Cola vacia";
        } else {
            int i = this.frente;
            texto = "";

            while (i != this.fin) {
                texto += arreglo[i];

                if (arreglo[(i + 1) % Cola.TAMANIO] != null) {
                    texto += ", ";
                }
                i = (i + 1) % Cola.TAMANIO;
            }

        }

        return texto;
    }
}
