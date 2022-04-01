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

    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar(Object nuevoElem) {

        boolean exito;

        if (this.tope + 1 >= this.TAMANIO) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }

        return exito;
    }

    public boolean desapilar() {

        boolean exito;

        if (this.tope < 0) {
            exito = false;
        } else {
            this.tope--;
            exito = true;
        }

        return exito;
    }

    public Object obtenerTope() {

        Object elem = null;

        if (this.tope >= 0) {
            elem = this.arreglo[tope];
        }

        return elem;
    }

    public boolean esVacia() {

        boolean vacio;

        if (this.tope < 0) {
            vacio = true;
        } else {
            vacio = false;
        }

        return vacio;
    }

    public void vaciar() {
         
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
        //for (int i = tope; i >= 0; i--) {
        //    this.arreglo[i] = null;
        //}
    }
    
    //Metodo Clon//
    public Pila clone() {

        Pila arregloClon = new Pila();
        arregloClon.tope = this.tope;
        arregloClon.arreglo = this.arreglo.clone();
        return arregloClon;

    }

    public String toString() {

        String arregloString = null;

        if (tope >= 0) {
            for (int i = 0; i <= tope; i++) {
                // \n = salto de linea
              arregloString = arregloString + this.arreglo[i];
            }
        }
        else{
            arregloString = "Pila Vacia";
        }
        
        return arregloString;
    }
}
