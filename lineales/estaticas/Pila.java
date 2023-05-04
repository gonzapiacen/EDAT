/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author gonzalo.piacentini
 */
public class Pila {

    //atributos de clase
    private Object[] array;
    private int tope;
    private static final int TAMANIO = 10;
    
    //metodo constructor
    public Pila() {
        this.array = new Object[TAMANIO];
        this.tope = -1;
    }

    //metodo apilar
    public boolean apilar(Object nuevoElem) {

        boolean exito;

        if (this.tope + 1 >= TAMANIO) {
            exito = false;
        } else {
            this.tope++;
            this.array[tope] = nuevoElem;
            exito = true;
        }

        return exito;
    }

    //metodo desapilar
    public boolean desapilar() {

        boolean exito;

        if (this.tope < 0) {
            exito = false;
        } else {
            this.array[this.tope] = null;
            this.tope--;
            exito = true;
        }

        return exito;
    }

    //metodo obtenerTope
    public Object obtenerTope() {

        Object elem = null;

        if (!esVacia()) {
            elem = this.array[this.tope];
        }

        return elem;
    }

    //metodo es esVacia
    public boolean esVacia() {
        return this.tope == -1;
    }

    //metodo vaciar
    public void vaciar() {

        if (!esVacia()) {
            this.array = new Object[TAMANIO];
            this.tope = -1;
        }
    }

    //metodo Clon
    @Override
    public Pila clone() {
        //crea una nueva pila y le asigna el tope
        Pila arregloClon = new Pila();
        arregloClon.tope = this.tope;

        //Uso un metodo propio de java llamado clone para clonar
        arregloClon.array = this.array.clone();

        return arregloClon;
    }

    //metodo toString
    @Override
    public String toString() {

        String arregloString;

        if (!esVacia()) {
            
                arregloString = "[ ";
            for (int i = 0; i <= tope; i++) {
                arregloString = arregloString + this.array[i] + " ";

            }
            arregloString = arregloString + "]";
        } else {
            arregloString = "Pila Vacia";
        }

        return arregloString;
    }
}
